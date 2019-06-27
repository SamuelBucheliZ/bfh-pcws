package ch.bfh.pcws.image;

import ch.bfh.pcws.account.Account;
import ch.bfh.pcws.api.AddressData;
import ch.bfh.pcws.api.CodeRequest;
import ch.bfh.pcws.api.CodeResponse;
import ch.bfh.pcws.api.ServiceType;
import ch.bfh.pcws.code.CodeService;
import ch.bfh.pcws.log.DataLogService;
import ch.bfh.pcws.account.AccountService;
import org.krysalis.barcode4j.BarcodeDimension;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapBuilder;
import org.krysalis.barcode4j.output.java2d.Java2DCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Optional;

public class CreateImageService {

    private final AccountService accountService;
    private final CodeService codeService;
    private final DataLogService dataLogService;
    private final BufferedImage paketBlitzLogo;

    public CreateImageService(
            AccountService accountService,
            CodeService codeService,
            DataLogService dataLogService) {
        this.accountService = accountService;
        this.codeService = codeService;
        this.dataLogService = dataLogService;
        this.paketBlitzLogo = loadLogo();
    }

    private static BufferedImage loadLogo() {
        URL logoUrl = CreateImageService.class.getResource("/logo/paketblitz.png");
        try {
            return ImageIO.read(logoUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CodeResponse handle(CodeRequest codeRequest) {

        ZonedDateTime start = ZonedDateTime.now();

        String accountName = codeRequest.getAccountName();
        Optional<Account> foundAccount = accountService.getAccount(accountName);
        if (!foundAccount.isPresent()) {
            return CodeResponse.fromError("Account does not exist");
        }

        Account account = foundAccount.get();
        ServiceType requestedServiceType = codeRequest.getServiceType();

        if (!account.getSupportedServiceTypes().contains(requestedServiceType)) {
            return CodeResponse.fromError("Account does not have permission to use requested service");
        }

        int accountId = account.getAccountId();
        String barcode = createBarcode(accountId, requestedServiceType);

        CodeResponse code = createCode(barcode, codeRequest.getAddressData());

        ZonedDateTime end = ZonedDateTime.now();

        dataLogService.log(start, end, accountName, barcode);

        return code;
    }

    private String createBarcode(int accountId, ServiceType requestedServiceType) {
        int barcodeNumber = codeService.getBarcodeNumber(accountId);
        return String.format("%s%08d%09d", requestedServiceType.getPrefix(), accountId, barcodeNumber);
    }


    private CodeResponse createCode(String barcode, AddressData addressData) {
        try {
            String code = createCode(addressData, barcode);
            return CodeResponse.fromCode(code);
        } catch (Exception e) {
            return CodeResponse.fromError("Could not generate barcode");
        }
    }

    private String createCode(AddressData addressData, String barcode) {
        int orientation = 0;
        int resolution = 300;

        Code128Bean code128BeanX = new Code128Bean();

        BarcodeDimension dim = new BarcodeDimension(100, 54);

        int bmw = UnitConv.mm2px(dim.getWidthPlusQuiet(orientation), resolution);
        int bmh = UnitConv.mm2px(dim.getHeightPlusQuiet(orientation), resolution);
        BufferedImage bi = new BufferedImage(bmw, bmh, BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g2d = bi.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setBackground(Color.white);
        g2d.setColor(Color.black);
        g2d.clearRect(0, 0, bi.getWidth(), bi.getHeight());
        //Set up coordinate system: Barcode4J calculates in millimeters internally
        g2d.scale(bi.getWidth() / dim.getWidthPlusQuiet(orientation),
                bi.getHeight() / dim.getHeightPlusQuiet(orientation));

        g2d.translate(5, 2);
        Java2DCanvasProvider canvas = new Java2DCanvasProvider(g2d, orientation);
        code128BeanX.generateBarcode(canvas, barcode);

        g2d.setFont(g2d.getFont().deriveFont(5.0f));
        g2d.drawString(addressData.getName(), 0, 28);
        g2d.drawString(addressData.getAddress(), 0, 34);
        g2d.drawString(addressData.getZipCode() + " " + addressData.getTown(), 0, 40);

        float[] scales = { 1f, 1f, 1f, 0.5f };
        float[] offsets = new float[4];
        RescaleOp rop = new RescaleOp(scales, offsets, null);
        g2d.drawImage(paketBlitzLogo, 80, 0, 11, 18, null);

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            BitmapBuilder.saveImage(bi, byteArrayOutputStream, "image/x-png", resolution);
            return transformToBase64String(byteArrayOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private String transformToBase64String(ByteArrayOutputStream byteArrayOutputStream) {
        byte[] encoded = Base64.getEncoder().encode(byteArrayOutputStream.toByteArray());
        return new String(encoded);
    }

}
