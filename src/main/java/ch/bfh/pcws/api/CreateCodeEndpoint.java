package ch.bfh.pcws.api;


import ch.bfh.pcws.image.CreateImageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateCodeEndpoint {

    private final CreateImageService createImageService;

    @Autowired
    public CreateCodeEndpoint(
            CreateImageService createImageService
    ) {
        this.createImageService = createImageService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/createCode", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "createCode")
    public CodeResponse createCode(@RequestBody CodeRequest codeRequest) {
        return createImageService.handle(codeRequest);
    }
}
