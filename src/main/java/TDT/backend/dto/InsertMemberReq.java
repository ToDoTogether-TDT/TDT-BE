package TDT.backend.dto;

import TDT.backend.entity.Image;
import TDT.backend.entity.Member;
import TDT.backend.entity.Role;
import lombok.Data;

@Data
public class InsertMemberReq {
    private String name;
    private String email;
    private String picture;
    private Role role;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .image(Image.builder().url(picture).build())
                .role(Role.GUEST)
                .build();
    }

}
