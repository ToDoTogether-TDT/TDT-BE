package TDT.backend.dto.team;

import TDT.backend.dto.comment.CommentRes;
import TDT.backend.dto.member.MemberDto;
import TDT.backend.dto.schedule.ScheduleDto;
import TDT.backend.entity.Category;
import TDT.backend.entity.Member;
import TDT.backend.entity.Team;
import TDT.backend.entity.TeamMember;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class StudyResponseDto {
    private Long studyId;
    private String writer;
    private String title;
    private Category category;
    private String introduction;
    private List<ScheduleDto> todos = new ArrayList<>();
    private List<CommentRes> comments = new ArrayList<>();


    @QueryProjection
    public StudyResponseDto(Team team, Member member) {
        this.writer = team.getName();
        this.title = team.getTitle();
        this.category = team.getCategory();
        this.introduction = team.getIntroduction();
    }

    @Builder
    public StudyResponseDto(Long studyId, String writer, String title, Category category, String introduction,
                            List<ScheduleDto> scheduleDto, List<CommentRes> comments) {
        this.studyId = studyId;
        this.writer = writer;
        this.title = title;
        this.category = category;
        this.introduction = introduction;
        this.todos = scheduleDto;
        this.comments = comments;
    }

    public static StudyResponseDto of(Long studyId, TeamMember writer, List<ScheduleDto> schedules,
                                      List<MemberDto> checkedMembers, List<CommentRes> comments) {

        schedules.forEach(scheduleDto -> scheduleDto.getLists().forEach(scheduleCheckedDto -> {
            for (MemberDto member : checkedMembers) {
                if (member.getScheduleId().equals(scheduleCheckedDto.getScheduleId()))
                    scheduleCheckedDto.getCheckedMembers().add(member.toMemberDto());
            }}));

        return StudyResponseDto.builder()
                .studyId(studyId)
                .writer(writer.getMember().getNickname())
                .title(writer.getTeam().getTitle())
                .introduction(writer.getTeam().getIntroduction())
                .category(writer.getTeam().getCategory())
                .scheduleDto(schedules)
                .comments(comments)
                .build();
    }

    /**
     * 스케줄 추가해야함
     * 댓글 추가
     */
}


