package com.today_diary.admin.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    POST_USERS_CONFIRM_PASSWORD(false, 2000, "비밀번호와 비밀번호 확인이 다릅니다."),
    POST_USERS_NAME_DUPLICATE(false, 2001, "이미 사용된 닉네임입니다. 다른 닉네임을 사용하세요"),
    POST_USERS_EMAIL_DUPLICATE(false, 2002, "이미 사용된 이메일입니다. 다른 이메일을 사용하세요"),
    NOT_EXIST_USER(false, 2003, "해당 회원이 존재하지 않습니다."),
    DATABASE_ERROR(false, 2004, "데이터베이스 연결에 실패하였습니다."),
    USERS_PHONENUMBER_INVALID(false, 2005, "핸드폰 번호 형식에 맞지 않습니다. "),
    USERS_EMAIL_INVALID(false, 2006, "이메일 형식에 맞지 않습니다. "),
    FAIL_LOGIN(false, 2007, "로그인 실패");

    private final boolean isSuccess;
    private final int code;
    private final String message;
    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
