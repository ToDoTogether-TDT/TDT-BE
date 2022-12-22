package TDT.backend.service.team;

import TDT.backend.dto.team.StudyRequestDto;
import TDT.backend.entity.Category;
import TDT.backend.entity.StudyTypes;
import TDT.backend.entity.Team;
import TDT.backend.repository.team.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class TeamServiceTest {


    @Autowired
    private  TeamService teamService;

    @Autowired
    private TeamRepository teamRepository;



    @Test
    void save() {

        Team team = Team.builder().title("안녕하세용")
                        .category(Category.HOBBY).introduction("ㅎ2ㅎ2")
                        .studyTypes(StudyTypes.online).build();


        teamRepository.save(team);
    }
}