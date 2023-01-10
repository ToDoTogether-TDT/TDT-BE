package TDT.backend.dto.member;

import TDT.backend.entity.Member;
import lombok.Getter;

@Getter
public class InsertMemberResponse {

    private final Long memberId;
    private final String email;
    private final String nickname;

    private InsertMemberResponse(Long memberId, String email, String nickname) {
        this.memberId = memberId;
        this.email = email;
        this.nickname = nickname;
    }

    public static InsertMemberResponse of(Member member) {
        return new InsertMemberResponse(
                member.getId(),
                member.getEmail(),
                member.getNickname());
    }
}