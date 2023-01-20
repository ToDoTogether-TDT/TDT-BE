package TDT.backend.dto.notice;

import TDT.backend.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeMemberResponseDto {

    private Long memberId;
    private String nickname;
    private String picture;


    @Builder
    public NoticeMemberResponseDto(Member member) {
        this.memberId = member.getId();
        this.nickname = member.getNickname();
        this.picture = member.getPicture();
    }


}