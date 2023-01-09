package com.today_diary.admin.web.dto.user;


import lombok.Builder;
import lombok.Getter;

@Getter
public class UserLogInRequestDto {
    private String email;
    private String password;

    @Builder
    public UserLogInRequestDto(String email, String password){
        this.email = email;
        this.password = password;
    }
}
