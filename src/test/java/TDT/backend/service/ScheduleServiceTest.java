package TDT.backend.service;

import TDT.backend.dto.schedule.ScheduleRequestDto;
import TDT.backend.dto.schedule.ScheduleResForMember;
import TDT.backend.dto.schedule.ScheduleResForTeam;
import TDT.backend.entity.*;
import TDT.backend.exception.BusinessException;
import TDT.backend.repository.member.MemberRepository;
import TDT.backend.repository.memberSchedule.MemberScheduleRepository;
import TDT.backend.repository.schedule.ScheduleRepository;
import TDT.backend.repository.team.TeamRepository;
import TDT.backend.repository.teamMember.TeamMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    @Autowired
    private MemberScheduleRepository memberScheduleRepository;

    private Team team1;
    private Team team2;
    private TeamMember teamMember1;
    private TeamMember teamMember2;

    @BeforeEach
    void clean() {
        scheduleRepository.deleteAll();
        memberRepository.deleteAll();;
        teamRepository.deleteAll();
        teamMemberRepository.deleteAll();
        Member member = new Member("name", "email@naver.com", Role.USER, "picture");
        member.profileUpdate("nickname", "introduction", Category.EXAM);
        memberRepository.save(member);
        team1 = new Team("title1", "introduction1", Category.EXAM, StudyTypes.online, "nickname");
        team2 = new Team("title2", "introduction1", Category.EMPLOYMENT, StudyTypes.offline, "nickname");
        teamRepository.save(team1);
        teamRepository.save(team2);
        teamMember1 = TeamMember.of(member, team1);
        teamMember2 = TeamMember.of(member, team2);
        teamMemberRepository.save(teamMember1);
        teamMemberRepository.save(teamMember2);
    }


    @Test
    @DisplayName("스케줄 추가")
    public void addScheduleTest() throws Exception {
        //given
        ScheduleRequestDto requestDto = ScheduleRequestDto.builder()
                .memberId(1L).studyId(1L)
                .title("title").contents("contents")
                .status(ScheduleStatus.ONGOING).endAt(LocalDateTime.MAX)
                .build();

        //when
        scheduleService.addSchedule(requestDto);

        //then
        assertEquals(1L, scheduleRepository.count());
        Schedule schedule = scheduleRepository.findAll().get(0);
        assertEquals("title", schedule.getTitle());
        assertEquals(ScheduleStatus.ONGOING, schedule.getStatus());
    }

    @Test
    @DisplayName("팀 소속 멤버가 아닌 멤버가 스케줄 추가")
    public void notTeamMemberErrorTest() throws Exception {
        //given
        Member member = new Member("test", "test@naver.com", Role.USER, "testPicture");
        memberRepository.save(member);

        ScheduleRequestDto requestDto = ScheduleRequestDto.builder()
                .memberId(2L).studyId(1L)
                .title("title").contents("contents")
                .status(ScheduleStatus.ONGOING).endAt(LocalDateTime.MAX)
                .build();

        //then
        assertThrows(BusinessException.class, () -> scheduleService.addSchedule(requestDto));
    }

    @Test
    @DisplayName("팀 리더가 아닌 멤버가 스케줄 추가")
    public void notLeaderErrorTest() throws Exception {
        //given
        Member member = new Member("test", "test@naver.com", Role.USER, "testPicture");
        memberRepository.save(member);
        TeamMember teamMember = TeamMember.join(member, team1);
        teamMemberRepository.save(teamMember);

        ScheduleRequestDto requestDto = ScheduleRequestDto.builder()
                .memberId(2L).studyId(1L)
                .title("title").contents("contents")
                .status(ScheduleStatus.ONGOING).endAt(LocalDateTime.MAX)
                .build();

        //then
        assertThrows(BusinessException.class, () -> scheduleService.addSchedule(requestDto));
    }

    @Test
    @DisplayName("멤버 별 스케줄")
    public void memberSchedulesTest() throws Exception {
        //given
        Schedule schedule1 = Schedule.builder()
                .team(team1).title("title1").contents("contents1")
                .endAt(LocalDateTime.MAX).status(ScheduleStatus.ONGOING)
                .build();
        Schedule schedule2 = Schedule.builder()
                .team(team2).title("title2").contents("contents2")
                .endAt(LocalDateTime.MAX).status(ScheduleStatus.ONGOING)
                .build();

        Member member = new Member("test", "test@naver.com", Role.USER, "testPicture");
        memberRepository.save(member);
        TeamMember teamMember = TeamMember.join(member, team2);
        teamMemberRepository.save(teamMember);
        scheduleRepository.save(schedule1);
        scheduleRepository.save(schedule2);
        MemberSchedule memberSchedule1 = new MemberSchedule(teamMember, schedule2);
        MemberSchedule memberSchedule2 = new MemberSchedule(teamMember1, schedule1);
        MemberSchedule memberSchedule3 = new MemberSchedule(teamMember2, schedule2);
        memberScheduleRepository.save(memberSchedule1);
        memberScheduleRepository.save(memberSchedule2);
        memberScheduleRepository.save(memberSchedule3);

        //when
        List<ScheduleResForMember> schedules1 = scheduleService.getSchedulesForMember(1L);

        //then
        assertEquals(2, schedules1.size());
        assertEquals("title1", schedules1.get(0).getScheduleTitle());

        //when
        List<ScheduleResForMember> schedules2 = scheduleService.getSchedulesForMember(2L);

        //then
        assertEquals(1, schedules2.size());
        assertEquals("contents2", schedules2.get(0).getScheduleContents());
    }

    /*
    외않됌
     */
//    @Test
//    @DisplayName("팀 별 스케줄")
//    public void teamSchedulesTest() throws Exception {
//        //given
//        Schedule schedule1 = Schedule.builder()
//                .team(team1).title("title1").contents("contents1")
//                .endAt(LocalDateTime.MAX).status(ScheduleStatus.ONGOING)
//                .build();
//        Schedule schedule2 = Schedule.builder()
//                .team(team2).title("title2").contents("contents2")
//                .endAt(LocalDateTime.MAX).status(ScheduleStatus.ONGOING)
//                .build();
//
//        scheduleRepository.save(schedule1);
//        scheduleRepository.save(schedule2);
//
//        //when
//        List<ScheduleResForTeam> schedules1 = scheduleService.getSchedulesForTeam(1L);
//
//        //then
//        assertEquals(1, schedules1.size());
//        assertEquals("title1", schedules1.get(0).getTitle());
//
//        //when
//        List<ScheduleResForTeam> schedules2 = scheduleService.getSchedulesForTeam(2L);
//
//        //then
//        assertEquals(1, schedules2.size());
//        assertEquals("contents2", schedules2.get(0).getContents());
//    }

    @Test
    @DisplayName("스케줄 수정")
    public void editScheduleTest() throws Exception {
        //given
        Schedule schedule1 = Schedule.builder()
                .team(team1).title("title1").contents("contents1")
                .endAt(LocalDateTime.MAX).status(ScheduleStatus.ONGOING)
                .build();

        scheduleRepository.save(schedule1);

        ScheduleRequestDto requestDto = ScheduleRequestDto.builder()
                .memberId(1L).studyId(1L)
                .title("editTitle").contents("editContents")
                .endAt(LocalDateTime.MIN).status(ScheduleStatus.FINISHED)
                .build();

        //when
        scheduleService.editSchedule(1L, requestDto);

        Schedule schedule = scheduleRepository.findAll().get(0);

        //then
        assertEquals("editTitle", schedule.getTitle());
        assertEquals(ScheduleStatus.FINISHED, schedule.getStatus());
    }

    @Test
    @DisplayName("팀 소속 멤버가 아닌 멤버가 스케줄 수정")
    public void notTeamMemberErrorOfEditSchedule() throws Exception {
        //given
        Member member = new Member("test", "test@naver.com", Role.USER, "testPicture");
        memberRepository.save(member);

        Schedule schedule1 = Schedule.builder()
                .team(team1).title("title1").contents("contents1")
                .endAt(LocalDateTime.MAX).status(ScheduleStatus.ONGOING)
                .build();

        scheduleRepository.save(schedule1);

        ScheduleRequestDto requestDto = ScheduleRequestDto.builder()
                .memberId(2L).studyId(1L)
                .title("editTitle").contents("editContents")
                .endAt(LocalDateTime.MIN).status(ScheduleStatus.FINISHED)
                .build();
        //when

        //then
        assertThrows(BusinessException.class, () -> scheduleService.editSchedule(1L, requestDto));
    }

    @Test
    @DisplayName("팀 리더가 아닌 멤버가 스케줄 수정")
    public void notTeamLeaderOfScheduleEdit() throws Exception {
        //given
        Member member = new Member("test", "test@naver.com", Role.USER, "testPicture");
        memberRepository.save(member);
        TeamMember teamMember = TeamMember.join(member, team1);
        teamMemberRepository.save(teamMember);

        Schedule schedule1 = Schedule.builder()
                .team(team1).title("title1").contents("contents1")
                .endAt(LocalDateTime.MAX).status(ScheduleStatus.ONGOING)
                .build();

        scheduleRepository.save(schedule1);

        ScheduleRequestDto requestDto = ScheduleRequestDto.builder()
                .memberId(2L).studyId(1L)
                .title("editTitle").contents("editContents")
                .endAt(LocalDateTime.MIN).status(ScheduleStatus.FINISHED)
                .build();

        //when

        //then
        assertThrows(BusinessException.class, () -> scheduleService.editSchedule(1L, requestDto));
    }

    @Test
    @DisplayName("스케줄 삭제")
    public void deleteScheduleTest() throws Exception {
        //given
        Schedule schedule1 = Schedule.builder()
                .team(team1).title("title1").contents("contents1")
                .endAt(LocalDateTime.MAX).status(ScheduleStatus.ONGOING)
                .build();

        scheduleRepository.save(schedule1);

        //when
        scheduleService.deleteSchedule(1L, 1L, 1L);

        //then
        assertEquals(0L, scheduleRepository.count());
    }
}












