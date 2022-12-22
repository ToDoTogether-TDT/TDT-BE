package TDT.backend.repository.team;

import TDT.backend.dto.team.StudyListResponseDto;
import TDT.backend.dto.team.StudyResponseDto;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface CustomTeamRepository {

    StudyResponseDto findByIdAndCategory(Integer studyId, String category);
    Page<StudyListResponseDto> findAllByCategoryAndPageable(String category, Pageable pageable);
}
