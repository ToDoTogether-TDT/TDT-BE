package TDT.backend.service;

import TDT.backend.dto.InsertMemberReq;
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

    private boolean validateDuplicateMember(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }
}
