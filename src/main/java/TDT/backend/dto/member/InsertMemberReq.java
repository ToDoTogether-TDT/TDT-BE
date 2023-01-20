package TDT.backend.dto.member;

import TDT.backend.entity.Member;
import TDT.backend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InsertMemberReq {
    private String name;
    private String email;
    private String picture;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .nickname("@" + name)
                .build();
    }


}
