package TDT.backend.repository.teamMember;

import TDT.backend.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {


    Optional<TeamMember> findByTeamId(Long teamId);

    List<TeamMember> findAllByTeamId(Long teamId);

//    List<TeamMember> findAllByIsLeaderAndMemberId(Long memberId);


    @Query("select tm from TeamMember tm where tm.member.id = :memberId and tm.team.id = :teamId")
    Optional<TeamMember> findByMemberIdAndTeamId(@Param("memberId") Long memberId, @Param("teamId") Long teamId);


    @Query("select tm.isLeader from TeamMember tm where tm.member.id = :memberId and tm.team.id = :teamId")
    boolean isLeader(@Param("memberId") Long memberId, @Param("teamId") Long teamId);

    @Modifying
    @Query("delete from TeamMember tm where tm.member.id = :memberId")
    void deleteByMemberId(@Param("memberId") Long memberId);

    @Query("select tm from TeamMember tm where tm.team.id = :studyId and tm.isLeader = true")
    TeamMember findLeaderByTeamId(@Param("studyId") Long studyId);
}
