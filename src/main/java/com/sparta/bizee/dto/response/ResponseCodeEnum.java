package com.sparta.bizee.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseCodeEnum {
    SUCCESS(HttpStatus.OK, "요청이 성공했습니다."),
    CREATED(HttpStatus.CREATED, "리소스가 성공적으로 생성되었습니다."),
    INVALID_INPUT_VALUE(HttpStatus.METHOD_NOT_ALLOWED, "유효하지 않은 입력 값입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.NOT_FOUND, "허용되지 않는 메서드입니다."),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "엔티티를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류입니다."),
    INVALID_TYPE_VALUE(HttpStatus.INTERNAL_SERVER_ERROR, "유효하지 않은 타입 값입니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "접근이 거부되었습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"인증이 필요합니다."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 파라미터 값입니다."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "데이터를 찾을 수 없습니다."),
    TOKEN_ERROR(HttpStatus.UNAUTHORIZED, "잘못된 토큰값입니다."),

    // 좋아요
    DO_NOT_LIKE_MY_COMMENT(HttpStatus.METHOD_NOT_ALLOWED, "자신의 댓글에 좋아요할 수 없습니다."),
    DO_NOT_LIKE_MY_POST(HttpStatus.METHOD_NOT_ALLOWED, "자신의 게시물에 좋아요할 수 없습니다."),
    DUPLICATED_LIKE(HttpStatus.CONFLICT, "좋아요가 중복되었습니다."),

    // 일정, 댓글
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."),
    NOTHING_COMMENT(HttpStatus.OK, "댓글이 존재하지 않습니다."),
    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다."),
    NOTHING_SCHEDULE(HttpStatus.OK, "일정이 존재하지 않습니다."),

    // 파일
    INVALID_INPUT_FILE(HttpStatus.BAD_REQUEST, "이미지 파일이 아닙니다."),

    // 사용자 정보
    SUCCESS_LOGIN(HttpStatus.OK, "로그인 성공"),
    SUCCESS_LOGOUT(HttpStatus.OK,"로그아웃 성공"),
    SUCCESS_SIGN_UP(HttpStatus.OK,"회원가입에 성공하였습니다."),
    WITHDRAW_SUCCESS_MESSAGE(HttpStatus.OK,"회원탈퇴에 성공했습니다."),
    REFRESH_TOKEN_SUCCESS_MESSAGE(HttpStatus.OK,"토큰 재발급 성공했습니다."),
    SUCCESS_UPDATE_PASSWORD(HttpStatus.OK,"비밀번호 변경이 정상적으로 처리되었습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    FAIL_LOGIN(HttpStatus.UNAUTHORIZED, "로그인 실패"),
    FAIL_LOGOUT(HttpStatus.UNAUTHORIZED, "로그아웃 실패"),
    PW_MISMATCH(HttpStatus.NOT_FOUND, "잘못된 비밀번호입니다."),
    DUPLICATED_USER(HttpStatus.CONFLICT, "중복된 사용자입니다."),
    USER_NOT_MATCH(HttpStatus.NOT_FOUND, "사용자가 일치하지 않습니다."),
    PATTERN_NOT_MATCH(HttpStatus.NOT_FOUND, "비밀번호 패턴이 일치하지 않습니다."),
    FAIL_SIGN_UP(HttpStatus.INTERNAL_SERVER_ERROR, "회원가입에 실패하였습니다."),
    WITHDRAW_USER(HttpStatus.NOT_FOUND, "이미 탈퇴한 회원입니다."),
    DISABLED_USER(HttpStatus.UNAUTHORIZED, "비활성화된 계정입니다."),
    LOCKED_USER(HttpStatus.UNAUTHORIZED, "잠긴 계정입니다."),
    REFRESH_TOKEN_MISMATCH(HttpStatus.NOT_FOUND, "잘못된 리프레시 토큰값입니다."),
    WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    DUPLICATED_PASSWORD(HttpStatus.BAD_REQUEST, "현재 비밀번호와 같은 비밀번호로 수정할 수 없습니다."),
    DUPLICATED_PASSWORD_THREE_TIMES(HttpStatus.BAD_REQUEST, "새 비밀번호는 최근 이용한 비밀번호와 다르게 설정해야합니다."),
    NOT_ADMIN(HttpStatus.NOT_FOUND, "잘못된 관리자 토큰값입니다."),
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "중복된 이메일입니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getStatusCode() {
        return httpStatus.value();
    }

    public String getStatus() {
        return httpStatus.toString();
    }

    public String getCodeName() {
        return httpStatus.name();
    }

}