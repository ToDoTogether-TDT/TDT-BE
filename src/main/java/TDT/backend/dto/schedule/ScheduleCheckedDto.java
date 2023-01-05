package TDT.backend.dto.schedule;

import TDT.backend.dto.member.MemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ScheduleCheckedDto {

    private Long scheduleId;
    private String title;
    private List<MemberDto.MemberDtoRes> checkedMembers = new ArrayList<>();

    @Builder
    public ScheduleCheckedDto(Long scheduleId, String title, List<MemberDto.MemberDtoRes> checkedMembers) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.checkedMembers = checkedMembers;
    }
}
