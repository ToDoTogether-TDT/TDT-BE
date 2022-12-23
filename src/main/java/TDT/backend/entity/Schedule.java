package TDT.backend.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private String contents;
    private LocalDateTime endAt;
    @Enumerated(value = EnumType.STRING)
    private ScheduleStatus status;

    @Builder
    public Schedule(Team team, String title, String contents, LocalDateTime endAt, ScheduleStatus status) {
        this.team = team;
        this.title = title;
        this.contents = contents;
        this.endAt = endAt;
        this.status = status;
    }

    public void edit(String title, String contents, LocalDateTime endAt, ScheduleStatus status) {
        this.title = title;
        this.contents = contents;
        this.endAt = endAt;
        this.status = status;
    }
}
