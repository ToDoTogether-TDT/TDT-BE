package TDT.backend.dto.team;

import TDT.backend.entity.Category;
import TDT.backend.entity.Member;
import TDT.backend.entity.Team;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *    "writer" : "스터디장",
 *               "title" : "스터디 주제",
 *               "category" :"스터디 카테고리" ,
 *               "introuduction" : "스터디 소개",
 *               "creatAt": "스터디 만들어진 날짜",
 *               "members" : [
 *     {"image":"멤버 사진"},
 *     {"nickname":"멤버 닉네임"},
 *     {"memberIntroduction":"멤버 자기소개"}
 *
 *
 */
@Getter
@NoArgsConstructor
public class StudyListRes {

    private String writer;
    private String title;
    private Category category;
    private String introduction;
    private MemberDto memberDto;

    //private Member member;
    @QueryProjection
    public StudyListRes(Team team, Member member) {
        this.writer = team.getName();
        this.title = team.getTitle();
        this.category = team.getCategory();
        this.introduction = team.getIntroduction();
        this.memberDto = new MemberDto(member.getNickname(), member.getPicture(), member.getIntroduction());
    }

    @Getter
    @AllArgsConstructor
    static class MemberDto{
        private String nickname;
        private String image;
        private String introduction;
    }
}
