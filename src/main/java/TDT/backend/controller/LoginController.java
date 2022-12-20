package TDT.backend.controller;

import TDT.backend.dto.InsertMemberReq;
import TDT.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    @PostMapping(value = "/users")
    public ResponseEntity<?> login(@RequestBody InsertMemberReq req) {
        memberService.addMember(req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
