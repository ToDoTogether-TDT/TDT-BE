package TDT.backend.dto.member;

import TDT.backend.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileResDto {

    private Long memberId;
    private String nickname;


    public ProfileResDto(Member member) {
        this.memberId = member.getId();
        this.nickname = member.getNickname();

    }
}
