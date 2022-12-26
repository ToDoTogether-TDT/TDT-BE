package TDT.backend.controller;

import TDT.backend.dto.member.ProfileReqDto;
import TDT.backend.dto.member.ProfileResDto;
import TDT.backend.entity.Member;
import TDT.backend.repository.member.MemberRepository;
import TDT.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {

    private final MemberService memberService;

    @PatchMapping("/edit")
    public ResponseEntity<?> addProfile(@RequestBody ProfileReqDto dto) {
        memberService.addProfile(dto);
        //Command Query Separation
        Member findMember = memberService.findOne(dto.getEmail());
        return ResponseEntity.ok(new ProfileResDto(findMember));
    }

}
