package TDT.backend.repository.team;

import TDT.backend.dto.team.StudyJoinReqMemberDto;
import TDT.backend.dto.team.StudyListResponseDto;
import TDT.backend.dto.team.StudyResponseDto;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomTeamRepository {

    StudyResponseDto findByIdAndCategory(Long studyId, String category);
    Page<StudyListResponseDto> findAllByCategoryAndPageable(String category, Pageable pageable);

    Page<StudyListResponseDto> findAllByPageable(Pageable pageable);
    List<StudyJoinReqMemberDto> findStudyJoinReqMembers(Long studyId);
}
