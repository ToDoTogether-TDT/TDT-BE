package TDT.backend.controller;

import TDT.backend.security.dto.SessionMember;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    @GetMapping("/login")
    public @ResponseBody String  login(String code) {
        ModelAndView mv = new ModelAndView();
        System.out.println(code);
        return code;
    }

    @GetMapping(value = "/login/google")
    public ModelAndView redirectGoogle(ModelAndView mav, @RequestParam String code) {
        mav.addObject("code", code);
        mav.setViewName("/");
        System.out.println(code);
        return mav;
    }
}
