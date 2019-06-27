package ch.bfh.pcws.code.mock;

import ch.bfh.pcws.code.CodeService;

import java.util.HashMap;
import java.util.Map;

public class MockCodeServiceImpl implements CodeService {

    private Map<Integer, Integer> barcodeNumbers = new HashMap<>();

    @Override
    public int getBarcodeNumber(int accountId) {
        int barcodeNumber = barcodeNumbers.getOrDefault(accountId, 0);
        int nextBarcodeNumber = barcodeNumber + 1;
        barcodeNumbers.put(accountId, nextBarcodeNumber);
        return barcodeNumber;
    }
}
