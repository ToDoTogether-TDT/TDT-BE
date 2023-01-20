package TDT.backend.service;

import TDT.backend.dto.member.InsertMemberReq;
import TDT.backend.dto.member.InsertMemberResponse;
import TDT.backend.dto.member.ProfileReqDto;
import TDT.backend.entity.Category;
import TDT.backend.entity.Member;
import TDT.backend.exception.BusinessException;
import TDT.backend.exception.ExceptionCode;
import TDT.backend.repository.member.MemberRepository;
import TDT.backend.repository.post.PostRepository;
import TDT.backend.repository.teamMember.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final TeamMemberRepository teamMemberRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public InsertMemberResponse addMember(InsertMemberReq req) {
        boolean isExist = validateDuplicateMember(req.getEmail());
        if (isExist) {
            InsertMemberResponse response = InsertMemberResponse.of(memberRepository.findByEmail(req.getEmail()).get());
            return response;
        } else {
            Member member = memberRepository.save(req.toEntity());
            return InsertMemberResponse.of(member);
        }
    }

    public void updateProfile(ProfileReqDto dto, Member member) {
        member.profileUpdate(dto.getNickname(), dto.getIntroduction(), Category.valueOf(dto.getCategory()));
        memberRepository.save(member);
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
