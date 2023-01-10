package TDT.backend.controller;

import TDT.backend.common.auth.jwt.JwtTokenProvider;
import TDT.backend.dto.auth.jwt.TokenResponse;
import TDT.backend.dto.member.InsertMemberReq;
import TDT.backend.dto.member.InsertMemberResponse;
import TDT.backend.dto.member.ProfileReqDto;
import TDT.backend.dto.member.ProfileResDto;
import TDT.backend.entity.Member;
import TDT.backend.service.MemberService;
import TDT.backend.service.member.MemberDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class LoginController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "로그인", notes = "회원 로그인")
    @PostMapping
    public ResponseEntity<?> login(@RequestBody InsertMemberReq req, HttpServletResponse httpServletResponse) throws JsonProcessingException {

        InsertMemberResponse response = memberService.addMember(req);

        TokenResponse token = jwtTokenProvider.createTokensByLogin(response);

        httpServletResponse.setHeader("Authorization", "Bearer " + token.getAtk());
        httpServletResponse.addHeader("RefreshToken", token.getRtk());

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴")
    @DeleteMapping
    public ResponseEntity<?> signOutMember(@RequestParam("memberId") Long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "프로필 작성", notes = "회원 프로필 작성")
    @PatchMapping("/edit")
    public ResponseEntity<?> addProfile(@RequestBody ProfileReqDto dto) {
        memberService.addProfile(dto);
        //Command Query Separation
        Member findMember = memberService.findOne(dto.getEmail());
        return ResponseEntity.ok(new ProfileResDto(findMember));

    }

    @GetMapping("/reissue")
    public TokenResponse reissueToken(@AuthenticationPrincipal MemberDetails memberDetails,
                                      HttpServletRequest httpServletRequest) throws JsonProcessingException {
        InsertMemberResponse response = InsertMemberResponse.of(memberDetails.getMember());
        String rtk = httpServletRequest.getHeader("RefreshToken");
        return jwtTokenProvider.reissueAtk(response, rtk);
    }

}
