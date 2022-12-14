package TDT.backend.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    private String title;
    private LocalDateTime endAt;
    @Enumerated(value = EnumType.STRING)
    private ScheduleStatus status;

    @Builder
    public Schedule(Team team, String title, LocalDateTime endAt) {
        this.team = team;
        this.title = title;
        this.endAt = endAt;
        this.status = ScheduleStatus.ONGOING;
    }

    public void edit(String title, LocalDateTime endAt) {
        this.title = title;
        this.endAt = endAt;
    }
}
