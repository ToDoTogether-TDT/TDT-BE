package TDT.backend.dto.message;

import TDT.backend.entity.Member;
import TDT.backend.entity.Message;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageResponseDto {
    private String contents;
    private MemberDto memberDto;

    @QueryProjection
    public MessageResponseDto(Message message, Member member) {
        this.contents = message.getContent();
        this.memberDto = new MemberDto(member.getNickname(), member.getPicture(), member.getIntroduction());
    }


    @Getter
    @AllArgsConstructor
    static class MemberDto {
        private String nickname;
        private String image;
        private String introduction;
    }

}
