package TDT.backend.service;

import TDT.backend.common.auth.jwt.JwtTokenProvider;
import TDT.backend.dto.InsertMemberReq;
import TDT.backend.dto.InsertMemberResponse;
import TDT.backend.dto.member.ProfileReqDto;
import TDT.backend.entity.Member;
import TDT.backend.exception.BusinessException;
import TDT.backend.exception.ExceptionCode;
import TDT.backend.repository.member.MemberRepository;
import TDT.backend.repository.post.PostRepository;
import TDT.backend.repository.teamMember.TeamMemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final TeamMemberRepository teamMemberRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    private final JwtTokenProvider jwtTokenProvider;
    public InsertMemberResponse addMember(InsertMemberReq req) throws JsonProcessingException {
        boolean isExist = validateDuplicateMember(req.getEmail());
        if (isExist) {
            jwtTokenProvider.reissueAtk(InsertMemberResponse.of(req.toEntity()));
//            jwtTokenProvider.createTokensByLogin(InsertMemberResponse.of(memberRepository.findByEmail(req.getEmail()).get()));
            return InsertMemberResponse.of(memberRepository.findByEmail(req.getEmail()).get());
        } else {
            Member member = memberRepository.save(req.toEntity());
//            jwtTokenProvider.createTokensByLogin(InsertMemberResponse.of(member));
            return InsertMemberResponse.of(member);

        }
    }

    public void addProfile(ProfileReqDto dto) {
        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS)
        );
    }

    @Transactional(readOnly = false)
    public void deleteMember(Long memberId) {
        teamMemberRepository.deleteByMemberId(memberId);
        postRepository.deleteByMemberId(memberId);
        memberRepository.deleteById(memberId);
    }

    private boolean validateDuplicateMember(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    public Member findOne(String email) {
        return memberRepository.findByEmail(email).orElseThrow(
                () -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS));
    }


}
