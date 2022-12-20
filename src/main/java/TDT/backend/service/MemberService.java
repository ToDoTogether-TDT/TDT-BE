package TDT.backend.service;

import TDT.backend.dto.InsertMemberReq;
import TDT.backend.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean addMember(InsertMemberReq req) {
//        validateDuplicateMember(req.getEmail());
        memberRepository.save(req.toEntity());
        return true;
    }


    private void validateDuplicateMember(String email) {
        memberRepository.findByEmail(email).orElseThrow(
                //이미 있는 회원
                () -> new IllegalStateException(email)
        );


    }
}
