package TDT.backend.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "team_member_id")
    private TeamMember teamMember;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
    private Boolean isDoneTodo;

    @Builder
    public MemberSchedule(TeamMember teamMember, Schedule schedule, Boolean isDoneTodo) {
        this.teamMember = teamMember;
        this.schedule = schedule;
        this.isDoneTodo = isDoneTodo;
    }
}
