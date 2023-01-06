package com.today_diary.admin.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    POST_USERS_CONFIRM_PASSWORD(false, 2000, "비밀번호와 비밀번호 확인이 다릅니다."),
    POST_USERS_NAME_DUPLICATE(false, 2001, "이미 사용된 닉네임입니다. 다른 이메일을 사용하세요"),
    POST_USERS_EMAIL_DUPLICATE(false, 2002, "이미 사용된 이메일입니다. 다른 이메일을 사용하세요");

    private final boolean isSuccess;
    private final int code;
    private final String message;
    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
