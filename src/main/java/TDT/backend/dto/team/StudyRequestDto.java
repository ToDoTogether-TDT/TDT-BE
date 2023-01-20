package TDT.backend.dto.team;

import TDT.backend.entity.Category;
import TDT.backend.entity.StudyTypes;
import TDT.backend.entity.Team;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyRequestDto {
    private String title;
    private String introduction;
    private Category category;

    private String content;


    public Team toEntity() {
        return Team.builder()
                .title(title)
                .introduction(introduction)
                .category(category)
                .build();

    }
}

