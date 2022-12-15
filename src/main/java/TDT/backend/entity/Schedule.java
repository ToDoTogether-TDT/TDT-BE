package TDT.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;
    private String title;
    private String contents;
    private LocalDateTime endAt;
    private ScheduleStatus status;
}
