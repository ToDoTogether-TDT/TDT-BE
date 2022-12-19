package TDT.backend.controller;

import TDT.backend.security.dto.SessionMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class LoginController {

    @PostMapping("/users")
    public ResponseEntity login(@RequestBody String name) {
        log.info("user: {}", name);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/login/google")
    public ModelAndView redirectGoogle(ModelAndView mav, @RequestParam String code) {
        mav.addObject("code", code);
        mav.setViewName("/");
        System.out.println(code);
        return mav;
    }
}
