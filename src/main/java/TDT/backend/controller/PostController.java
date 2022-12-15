package TDT.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @GetMapping("/test")
    public ResponseEntity test() {
        return new ResponseEntity<>("test", HttpStatus.OK);
    }
}
