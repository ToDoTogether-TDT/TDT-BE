package TDT.backend.entity;

import TDT.backend.dto.schedule.ScheduleRequestDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class TeamSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Schedule schedule;
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;




    public static TeamSchedule of(Team team, ScheduleRequestDto dto) {
        return TeamSchedule.builder()
                .team(team).
                build();
    }

}
