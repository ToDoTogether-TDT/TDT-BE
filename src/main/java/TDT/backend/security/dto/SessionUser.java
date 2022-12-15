package TDT.backend.security.dto;

import TDT.backend.entity.Member;
import lombok.Getter;

import java.io.Serializable;


//session 정보
@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    //사진
    //private String picture;
    public SessionUser(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
