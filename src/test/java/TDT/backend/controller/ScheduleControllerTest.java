//package TDT.backend.controller;
//
//import TDT.backend.dto.schedule.ScheduleAddReqDto;
//import TDT.backend.entity.*;
//import TDT.backend.repository.member.MemberRepository;
//import TDT.backend.repository.schedule.ScheduleRepository;
//import TDT.backend.repository.team.TeamRepository;
//import TDT.backend.repository.teamMember.TeamMemberRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@SpringBootTest
//class ScheduleControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper mapper;
//    @Autowired
//    private ScheduleRepository scheduleRepository;
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private TeamRepository teamRepository;
//    @Autowired
//    private TeamMemberRepository teamMemberRepository;
//
//    private Team team1;
//    private Team team2;
//
//    @BeforeEach
//    void clean() {
//        scheduleRepository.deleteAll();
//        Member member = new Member("name", "email@naver.com", Role.USER, "picture");
//        member.profileUpdate("nickname", "introduction", Category.EXAM);
//        memberRepository.save(member);
//        team1 = new Team("title1", "introduction1", Category.EXAM, StudyTypes.online, "nickname");
//        team2 = new Team("title2", "introduction1", Category.EMPLOYMENT, StudyTypes.offline, "nickname");
//        teamRepository.save(team1);
//        teamRepository.save(team2);
//        TeamMember teamMember1 = TeamMember.of(member, team1);
//        TeamMember teamMember2 = TeamMember.of(member, team2);
//        teamMemberRepository.save(teamMember1);
//        teamMemberRepository.save(teamMember2);
//    }
//
//    @Test
//    @DisplayName("스케줄 생성 요청시 DB에 값 저장")
//    public void createScheduleTest() throws Exception {
//        //given
//        ScheduleAddReqDto requestDto = ScheduleAddReqDto.builder()
//                .memberId(1L)
//                .studyId(1L)
//                .title("title")
//                .contents("contents")
//                .status(ScheduleStatus.ONGOING)
//                .endAt(LocalDateTime.MAX)
//                .build();
//
//        String content = mapper.writeValueAsString(requestDto);
//
//        //when
//        mockMvc.perform(post("/study/schedule")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
//                )
//                .andExpect(status().isOk())
//                .andDo(print());
//
//        //then
//        assertEquals(1L, scheduleRepository.count());
//
//        Schedule schedule = scheduleRepository.findAll().get(0);
//        assertEquals("title1", schedule.getTitle());
//        assertEquals(ScheduleStatus.ONGOING, schedule.getStatus());
//    }
//
//    @Test
//    @DisplayName("멤버 별 스케줄 조회")
//    public void getSchedulesForMemberTest() throws Exception {
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
//        //expected
//        mockMvc.perform(get("/study/schedule")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("memberId", "1")
//                )
//                .andExpect(status().isOk())
//                .andDo(print());
//
//    }
//
//    @Test
//    @DisplayName("팀 별 스케줄 조회")
//    public void getSchedulesForTeamTest() throws Exception {
//        //given
//        Schedule schedule1 = Schedule.builder()
//                .team(team1).title("title1").contents("contents1")
//                .endAt(LocalDateTime.MAX).status(ScheduleStatus.ONGOING)
//                .build();
//        Schedule schedule2 = Schedule.builder()
//                .team(team1).title("title2").contents("contents2")
//                .endAt(LocalDateTime.MAX).status(ScheduleStatus.ONGOING)
//                .build();
//
//        scheduleRepository.save(schedule1);
//        scheduleRepository.save(schedule2);
//
//        //expected
//        mockMvc.perform(get("/study/schedule")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("studyId", "1")
//                )
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("스케줄 수정")
//    public void editScheduleTest() throws Exception {
//        //given
//        Schedule schedule = Schedule.builder()
//                .team(team1).title("title").contents("contents")
//                .endAt(LocalDateTime.MAX).status(ScheduleStatus.ONGOING)
//                .build();
//        scheduleRepository.save(schedule);
//
//        ScheduleAddReqDto requestDto = ScheduleAddReqDto.builder()
//                .memberId(1L).studyId(1L)
//                .title("editTitle").contents("editContents")
//                .status(ScheduleStatus.FINISHED).endAt(LocalDateTime.MAX)
//                .build();
//
//        String content = mapper.writeValueAsString(requestDto);
//
//        //expected
//        mockMvc.perform(put("/study/schedule/{scheduleId}", schedule.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
//                )
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("스케줄 삭제")
//    public void deleteScheduleTest() throws Exception {
//        //given
//        Schedule schedule = Schedule.builder()
//                .team(team1).title("title").contents("contents")
//                .endAt(LocalDateTime.MAX).status(ScheduleStatus.ONGOING)
//                .build();
//        scheduleRepository.save(schedule);
//
//        //expected
//        mockMvc.perform(delete("/study/schedule/{scheduleId}", schedule.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("memberId", "1")
//                        .param("studyId", "1")
//                )
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//}