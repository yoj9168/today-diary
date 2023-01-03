package com.today_diary.admin.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    POST_USERS_CONFIRM_PASSWORD(false, 2000, "비밀번호와 비밀번호 확인이 다릅니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;
    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
