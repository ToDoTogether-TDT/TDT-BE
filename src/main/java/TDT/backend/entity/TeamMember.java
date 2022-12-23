package TDT.backend.entity;

import TDT.backend.dto.team.StudyRequestDto;
import lombok.*;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    private Boolean isLeader;
    @OneToMany(mappedBy = "teamMember", cascade = CascadeType.ALL)
    private List<MemberSchedule> memberSchedules;

    public static TeamMember of(Member member, Team team) {
        return TeamMember.builder()
                .member(member)
                .team(team)
                .isLeader(true)
                .build();
    }

    public static TeamMember join(Member member, Team team) {
        return TeamMember.builder()
                .member(member)
                .team(team)
                .isLeader(false)
                .build();
    }

}
