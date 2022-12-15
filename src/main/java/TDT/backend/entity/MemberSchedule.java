package TDT.backend.entity;

import javax.persistence.*;

@Entity
public class MemberSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Schedule schedule;
}
