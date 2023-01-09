package com.today_diary.admin.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends Exception{
    private BaseResponseStatus status;
}
