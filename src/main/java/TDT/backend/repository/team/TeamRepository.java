package TDT.backend.repository.team;

import TDT.backend.dto.team.StudyResponseDto;
import TDT.backend.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long>, CustomTeamRepository {


//    Page<StudyListResponseDto> findAllByCategoryAndPageable(String category, Pageable pageable);
}
