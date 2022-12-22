package TDT.backend.service.team;

import TDT.backend.dto.team.StudyListResponseDto;
import TDT.backend.dto.team.StudyRequestDto;
import TDT.backend.dto.team.StudyResponseDto;
import TDT.backend.entity.Member;
import TDT.backend.entity.Team;
import TDT.backend.entity.TeamMember;
import TDT.backend.exception.BusinessException;
import TDT.backend.exception.ExceptionCode;
import TDT.backend.repository.member.MemberRepository;
import TDT.backend.repository.team.TeamRepository;
import TDT.backend.repository.teamMember.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final MemberRepository memberRepository;

    public Page<StudyListResponseDto> getAllStudy(String category, Pageable pageable) {
        return teamRepository.findAllByCategoryAndPageable(category, pageable);
    }

    @Transactional
    public Long addTeam(final StudyRequestDto params) {
        Member member = memberRepository.findByNickname(
                params.getWriter()).orElseThrow(() -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS));
        Team team = Team.fromStudyRequestDto(params);
        TeamMember teamMember = TeamMember.of(member,team);
        teamMemberRepository.save(teamMember);
        return teamRepository.save(team).getId();
    }

    public StudyResponseDto getStudy(String category, Integer studyId) {
        return teamRepository.findByIdAndCategory(studyId, category);
    }

    public boolean deleteStudy(Long studyId, Long memberId) {

        Team team = teamRepository.findById(studyId).orElseThrow(
                () -> new BusinessException(ExceptionCode.TEAM_NOT_EXISTS));

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS));

        if (member.getNickname().equals(team.getName())) {
            TeamMember teamMember = teamMemberRepository.findByTeamId(studyId).orElseThrow(
                    () -> new BusinessException(ExceptionCode.TEAM_NOT_EXISTS));

            teamMemberRepository.delete(teamMember);
            teamRepository.delete(team);
        } else {
            new BusinessException(ExceptionCode.UNAUTHORIZED_ERROR);
        }

        return true;
    }
}
