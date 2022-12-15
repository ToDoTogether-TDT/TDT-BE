package TDT.backend.controller;

import TDT.backend.security.dto.SessionMember;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    @GetMapping("/login")
    public ResponseEntity<?> login(SessionMember sessionMember) {
        ModelAndView mv = new ModelAndView();

    }
}
