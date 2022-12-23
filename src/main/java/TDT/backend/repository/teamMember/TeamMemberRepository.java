package TDT.backend.repository.teamMember;

import TDT.backend.entity.Member;
import TDT.backend.entity.Team;
import TDT.backend.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long>{


    Optional<TeamMember> findByTeamId(Long teamId);
    Optional<Team> findByTeam(Team team);

    @Query("select tm from TeamMember tm where tm.member.id = :memberId and tm.team.id = :teamId")
    Optional<TeamMember> findByMemberIdAndTeamId(Long memberId, Long teamId);


}
