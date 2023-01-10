package TDT.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    public void joinAccept() {
        this.status = MemberStatus.member;
    }

    public static TeamMember of(Member member, Team team) {
        return TeamMember.builder()
                .member(member)
                .team(team)
                .isLeader(true)
                .status(MemberStatus.leader)
                .build();
    }

    public static TeamMember joinRequest(Member member, Team team) {
        return TeamMember.builder()
                .member(member)
                .team(team)
                .isLeader(false)
                .status(MemberStatus.guest)
                .build();
    }

    public static Member toMember(TeamMember teamMember) {
        return Member.builder().name(teamMember.getMember().getName()).nickname(teamMember.getMember().getNickname())
                .picture(teamMember.getMember().getPicture()).email(teamMember.getMember().getEmail()).build();
    }
}
