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
    @OneToMany(mappedBy = "team")
    private List<Schedule> schedules;
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Enumerated(value = EnumType.STRING)
    private StudyTypes studyTypes;

    private boolean isLeader;

    @OneToMany(mappedBy = "team")
    private List<TeamMember> teamMembers = new ArrayList<>();

    @Builder
    public Team(String title, String introduction, Category category, StudyTypes studyTypes,
                boolean isLeader,  String name) {
        this.title = title;
        this.introduction = introduction;
        this.category = category;
        this.studyTypes = studyTypes;
        this.name = name;
        this.isLeader = isLeader;
    }

    public static Team fromStudyRequestDto(StudyRequestDto dto) {
        return Team.builder()
                .title(dto.getTitle())
                .introduction(dto.getIntroduction())
                .category(dto.getCategory())
                .studyTypes(dto.getStudyTypes())
                .name(dto.getWriter())
                .isLeader(true)
                .build();

    }


}
