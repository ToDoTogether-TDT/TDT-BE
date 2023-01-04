package TDT.backend.service.team;

import TDT.backend.dto.member.MemberDto;
import TDT.backend.dto.schedule.ScheduleDto;
import TDT.backend.dto.team.StudyJoinReqDto;
import TDT.backend.dto.team.StudyListResponseDto;
import TDT.backend.dto.team.StudyRequestDto;
import TDT.backend.dto.team.StudyResponseDto;
import TDT.backend.entity.Member;
import TDT.backend.entity.Team;
import TDT.backend.entity.TeamMember;
import TDT.backend.exception.BusinessException;
import TDT.backend.exception.ExceptionCode;
import TDT.backend.repository.member.MemberRepository;
import TDT.backend.repository.memberSchedule.MemberScheduleRepository;
import TDT.backend.repository.schedule.ScheduleRepository;
import TDT.backend.repository.team.TeamRepository;
import TDT.backend.repository.teamMember.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional(readOnly = true)
    public Page<StudyListResponseDto> getAllStudy(String category, Pageable pageable) {
        return teamRepository.findAllByCategoryAndPageable(category, pageable);
    }


    public Long addTeam(final StudyRequestDto params) {
        Member member = memberRepository.findByNickname(
                params.getWriter()).orElseThrow(() -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS));
        Team team = Team.fromStudyRequestDto(params);
        TeamMember teamMember = TeamMember.of(member,team);
        teamMemberRepository.save(teamMember);
        return teamRepository.save(team).getId();
    }

    public void joinTeam(Long studyId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS));
        Team team = teamRepository.findById(studyId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.TEAM_NOT_EXISTS));

        TeamMember teamMember = TeamMember.join(member, team);
        teamMemberRepository.save(teamMember);
    }

    @Transactional(readOnly = true)
    public StudyResponseDto getStudy(String category, Long studyId) {

        TeamMember leader = teamMemberRepository.findLeaderByTeamId(studyId);

        List<ScheduleDto> schedules = scheduleRepository.findScheduleByStudyId(studyId);

        StudyResponseDto response = StudyResponseDto.builder()
                .writer(leader.getMember().getNickname())
                .title(leader.getTeam().getTitle())
                .introduction(leader.getTeam().getIntroduction())
                .category(leader.getTeam().getCategory())
                .scheduleDto(schedules)
                .build();

        return response;
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

//    public Boolean updateStudy(Long studyId, Long id) {
//        /**
//         *
//         *
//         */
//    }
}
