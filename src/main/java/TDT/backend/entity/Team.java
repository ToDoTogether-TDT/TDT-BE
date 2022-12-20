package TDT.backend.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;
    private String name;
    private String introduction;
    @OneToMany(mappedBy = "team")
    private List<Schedule> schedules;
    @OneToMany(mappedBy = "team")
    private List<TeamMember> teamMembers;
    @Enumerated(value = EnumType.STRING)
    private Category category;

    private boolean isLeader;
}
