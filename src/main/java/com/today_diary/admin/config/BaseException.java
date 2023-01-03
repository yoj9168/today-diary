package com.today_diary.admin.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class BaseException extends Exception{
    private BaseResponseStatus status;
}
