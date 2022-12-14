//package TDT.backend.entity;
//
//import TDT.backend.dto.schedule.ScheduleRequestDto;
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Builder
//@RequiredArgsConstructor
//public class TeamSchedule {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "schedule_id")
//    private Schedule schedule;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "team_id")
//    private Team team;
//
//
//
//
//    public static TeamSchedule of(Team team, ScheduleRequestDto dto) {
//        return TeamSchedule.builder()
//
//                .team(team).
//                build();
//    }
//
//}
