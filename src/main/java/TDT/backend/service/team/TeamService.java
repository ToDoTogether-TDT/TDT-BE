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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;
    private final MemberScheduleRepository memberScheduleRepository;

    @Transactional(readOnly = true)
    public Page<StudyListResponseDto> getAllStudy(String category, Pageable pageable) {
        return teamRepository.findAllByCategoryAndPageable(category, pageable);
    }

    @Transactional(readOnly = true)
    public Page<StudyListResponseDto> getAllKindOfStudy(Pageable pageable) {
        return teamRepository.findAllByPageable(pageable);
    }


    public Long addTeam(final StudyRequestDto params) {
        Member member = memberRepository.findByNickname(
                params.getWriter()).orElseThrow(() -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS));
        Team team = Team.fromStudyRequestDto(params);
        TeamMember teamMember = TeamMember.of(member, team);
        teamMemberRepository.save(teamMember);
        return teamRepository.save(team).getId();
    }

    public void joinTeam(StudyJoinReqDto params) {
        Member member = memberRepository.findById(params.getMemberId())
                .orElseThrow(() -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS));
        Team team = teamRepository.findById(params.getStudyId())
                .orElseThrow(() -> new BusinessException(ExceptionCode.TEAM_NOT_EXISTS));
        teamMemberRepository.save(TeamMember.join(member, team));
    }

    @Transactional(readOnly = true)
    public StudyResponseDto getStudy(Long studyId) {

        TeamMember leader = teamMemberRepository.findLeaderByTeamId(studyId);

        List<ScheduleDto> schedules = scheduleRepository.findScheduleByStudyId(studyId);

        List<MemberDto> members = memberScheduleRepository.findMembersByStudyId(studyId);


        schedules.forEach(scheduleDto -> scheduleDto.getLists().stream().forEach(scheduleCheckedDto -> {
                    for (MemberDto member : members) {
                        if (member.getScheduleId().equals(scheduleCheckedDto.getScheduleId()))
                            scheduleCheckedDto.getCheckedMembers().add(member.toMemberDto());
                    }}));



        StudyResponseDto response = StudyResponseDto.builder()
                .studyId(studyId)
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
    /**
     * Todo 권한주기
     *
     */

//    public Boolean updateStudy(Long studyId, Long id) {
//        /**
//         *
//         *
//         */
//    }
}
