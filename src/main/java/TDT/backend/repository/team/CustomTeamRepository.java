package TDT.backend.repository.team;

import TDT.backend.dto.team.StudyListRes;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface CustomTeamRepository {

    Page<StudyListRes> findAllByCategoryAndPageable(String category, Pageable pageable);
}
