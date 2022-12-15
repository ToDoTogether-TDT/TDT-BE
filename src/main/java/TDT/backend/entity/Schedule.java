package TDT.backend.entity;

import lombok.AccessLevel;
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
    private Team team;
    private String title;
    private String contents;
    private LocalDateTime endAt;
    private ScheduleStatus status;
}
