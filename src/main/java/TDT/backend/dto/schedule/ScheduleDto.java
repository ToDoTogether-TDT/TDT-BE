package TDT.backend.dto.schedule;

import TDT.backend.dto.member.MemberDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ScheduleDto {
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDateTime date;
    private List<ScheduleCheckedDto> lists = new ArrayList<>();

    @Builder
    public ScheduleDto(LocalDateTime date, List<ScheduleCheckedDto> lists) {
        this.date = date;
        this.lists = lists;
    }
}
