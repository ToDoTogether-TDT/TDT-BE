package TDT.backend.service;

import TDT.backend.dto.InsertMemberReq;
import TDT.backend.dto.member.ProfileReqDto;
import TDT.backend.entity.Member;
import TDT.backend.exception.BusinessException;
import TDT.backend.exception.ExceptionCode;
import TDT.backend.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void addMember(InsertMemberReq req) {
        if(!validateDuplicateMember(req.getEmail())) {
            memberRepository.save(req.toEntity());
        }
    }

    public void addProfile(ProfileReqDto dto) {
        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS)
        );

    }

    private boolean validateDuplicateMember(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    public Member findOne(String email) {
        return memberRepository.findByEmail(email).orElseThrow(
                () -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS));
    }
}
