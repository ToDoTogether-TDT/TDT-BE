package TDT.backend.controller;

import TDT.backend.dao.FCMTokenDao;
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
    private final FCMTokenDao fcmService;
    @PostMapping(value = "/users")
    public ResponseEntity<?> login(@RequestBody InsertMemberReq req) {
        memberService.addMember(req);
        log.info(req.getToken());
        fcmService.saveToken(req);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/logout")
    public void logout(@RequestBody InsertMemberReq req) {
        fcmService.deleteToken(req);
    }
}
