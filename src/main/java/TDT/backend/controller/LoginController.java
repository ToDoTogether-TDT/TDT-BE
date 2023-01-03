package TDT.backend.controller;

import TDT.backend.dto.InsertMemberReq;
import TDT.backend.dto.member.ProfileReqDto;
import TDT.backend.dto.member.ProfileResDto;
import TDT.backend.entity.Member;
import TDT.backend.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class LoginController {

    private final MemberService memberService;
    @ApiOperation(value = "로그인",notes = "회원 로그인")
    @PostMapping
    public ResponseEntity<?> login(@RequestBody InsertMemberReq req) {
        log.info(req.getName());
        log.info(req.getNickname());
        memberService.addMember(req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "회원 탈퇴",notes = "회원 탈퇴")
    @DeleteMapping
    public ResponseEntity<?> signOutMember(@RequestParam("memberId") Long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "프로필 작성",notes = "회원 프로필 작성")
    @PatchMapping("/edit")
    public ResponseEntity<?> addProfile(@RequestBody ProfileReqDto dto) {
        memberService.addProfile(dto);
        //Command Query Separation
        Member findMember = memberService.findOne(dto.getEmail());
        return ResponseEntity.ok(new ProfileResDto(findMember));

    }

}
