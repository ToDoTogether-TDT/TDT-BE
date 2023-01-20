package TDT.backend.entity;

import TDT.backend.dto.team.StudyRequestDto;
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
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;
    @Column(name = "writer")
    private String name;
    private String title;
    private String introduction;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Schedule> schedules;
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Comment> comments;


    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<TeamMember> teamMembers = new ArrayList<>();

    @Builder
    public Team(String title, String introduction, Category category, String name) {
        this.title = title;
        this.introduction = introduction;
        this.category = category;
        this.name = name;
    }

    public static Team fromStudyRequestDto(StudyRequestDto dto, Member member) {
        return Team.builder()
                .title(dto.getTitle())
                .introduction(dto.getIntroduction())
                .category(dto.getCategory())
                .name(member.getNickname())
                .build();

    }


}
