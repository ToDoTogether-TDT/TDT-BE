package TDT.backend.controller;

import TDT.backend.dto.InsertMemberReq;
import TDT.backend.dto.member.ProfileReqDto;
import TDT.backend.dto.member.ProfileResDto;
import TDT.backend.entity.Member;
import TDT.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class LoginController {

    private final MemberService memberService;
    @PostMapping
    public ResponseEntity<?> login(@RequestBody InsertMemberReq req) {
        log.info(req.getName());
        log.info(req.getNickname());
        memberService.addMember(req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> signOutMember(@RequestParam("memberId") Long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/edit")
    public ResponseEntity<?> addProfile(@RequestBody ProfileReqDto dto) {
        memberService.addProfile(dto);
        //Command Query Separation
        Member findMember = memberService.findOne(dto.getEmail());
        return ResponseEntity.ok(new ProfileResDto(findMember));

    }

}
