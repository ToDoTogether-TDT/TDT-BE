package TDT.backend.entity;

import TDT.backend.dto.message.MessageRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String picture;
    private String email;
    private String name;

    @OneToMany(mappedBy = "member")
    private List<TeamMember> teamMembers = new ArrayList<>();


    /**
     *  프로필 만들때 넣어야하는 부분
     */
    private String nickname;

    private String introduction;
    @Enumerated(EnumType.STRING)
    private Category category;

    @Builder
    public Member(String name, String email, String picture, String nickname) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.nickname = nickname;
    }

    public void profileUpdate (String nickname, String introduction, Category category) {
        this.nickname = nickname;
        this.introduction = introduction;
        this.category = category;
    }
}
