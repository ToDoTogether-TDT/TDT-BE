package TDT.backend.dto.team;

import TDT.backend.entity.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyRequestDto {
    private String title;
    private String introduction;
    private String writer;
    private Category category;
    private StudyTypes studyTypes;



    public Team toEntity() {
        return Team.builder()
                   .title(title)
                   .introduction(introduction)
                   .category(category)
                   .studyTypes(studyTypes)
                   .name(writer)
                   .isLeader(true).build();

    }
}

