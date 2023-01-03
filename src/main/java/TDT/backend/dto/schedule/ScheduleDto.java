package TDT.backend.dto.schedule;

import TDT.backend.dto.member.MemberDto;
import TDT.backend.dto.team.StudyResponseDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ScheduleDto {
    private Long scheduleId;
    private String title;
    private List<MemberDto> doneTodoMembers = new ArrayList<>();

    @QueryProjection
    public ScheduleDto(Long scheduleId, String title, List<MemberDto> memberDto) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.doneTodoMembers = memberDto;
    }
}
