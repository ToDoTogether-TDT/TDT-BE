package TDT.backend.controller;

import TDT.backend.dto.InsertMemberReq;
import TDT.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    @PostMapping(value = "/users")
    public ResponseEntity<Boolean> login(@RequestBody InsertMemberReq req) {
        return ResponseEntity.ok(memberService.addMember(req));
    }

    @GetMapping(value = "/login/google")
    public ModelAndView redirectGoogle(ModelAndView mav, @RequestParam String code) {
        mav.addObject("code", code);
        mav.setViewName("/");
        System.out.println(code);
        return mav;
    }
}
