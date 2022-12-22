package TDT.backend.repository.teamMember;

import TDT.backend.entity.Member;
import TDT.backend.entity.Team;
import TDT.backend.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long>{


    Optional<TeamMember> findByTeamId(Long teamId);
    Optional<Team> findByTeam(Team team);
}
