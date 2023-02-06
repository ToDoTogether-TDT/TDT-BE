package TDT.backend.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
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
    private Date endAt;
    @Enumerated(value = EnumType.STRING)
    private ScheduleStatus status;
    @OneToMany(mappedBy = "schedule")
    private List<MemberSchedule> memberSchedule;
    private String uuid;

    @Builder
    public Schedule(Team team, String title, Date endAt, String uuid) {
        this.team = team;
        this.title = title;
        this.endAt = endAt;
        this.status = ScheduleStatus.ONGOING;
        this.uuid = uuid;
    }

    public void edit(String title, Date endAt) {
        this.title = title;
        this.endAt = endAt;
    }
}
