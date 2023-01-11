package TDT.backend.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/healthCheck")
    public ResponseEntity healthCheck() {

        return new ResponseEntity("성공", HttpStatus.OK);
    }
}
