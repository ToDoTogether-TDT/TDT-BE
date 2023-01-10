package TDT.backend.dto.notice;

import TDT.backend.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeMemberResponseDto {


    private String nickname;
    private String introduction;
    private String picture;


    @Builder
    public NoticeMemberResponseDto(Member member) {
        this.nickname = member.getNickname();
        this.introduction = member.getIntroduction();
        this.picture = member.getPicture();
    }


}