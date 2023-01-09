package com.today_diary.admin.controller;

import com.today_diary.admin.config.BaseResponse;
import com.today_diary.admin.config.BaseResponseStatus;
import com.today_diary.admin.service.AuthService;
import com.today_diary.admin.web.dto.auth.LoginRequest;
import com.today_diary.admin.web.dto.auth.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.today_diary.admin.config.BaseResponseStatus.FAIL_LOGIN;
import static com.today_diary.admin.config.BaseResponseStatus.SUCCESS;
import static com.today_diary.admin.utility.ValidationRegex.*;
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public BaseResponse login(@RequestBody LoginRequest dto){
        if(dto.getEmail().equals("") || !isRegexEmail(dto.getEmail())){
            return new BaseResponse(BaseResponseStatus.USERS_EMAIL_INVALID);
        }
        LoginResponse response = authService.login(dto);
        if(response.getUserId() < 0L){
            return new BaseResponse(FAIL_LOGIN);
        }
        return new BaseResponse(SUCCESS);
    }
}
