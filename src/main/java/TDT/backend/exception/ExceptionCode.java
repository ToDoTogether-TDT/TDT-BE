package TDT.backend.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_NOT_EXISTS(404, "존재하지 않는 회원입니다."),
    POST_NOT_EXISTS(404, "존재하지 않는 글입니다."),
    TEAM_NOT_EXISTS(404, "존재하지 않는 팀입니다."),
    COMMENT_NOT_EXISTS(404, "존재하지 않는 댓글입니다."),
    SCHEDULE_NOT_EXISTS(404, "존재하지 않는 스케줄입니다."),
    NOT_TEAM_MEMBERS(404, "해당 스터디의 팀원이 아닙니다."),
    UNAUTHORIZED_ERROR(401, "권한이 없습니다");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
