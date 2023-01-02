package com.today_diary.admin.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getterc
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String phoneNumber;
    private String sex;
    private int age;

    public UserUpdateRequestDto(String phoneNumber, String sex, int age){
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.age = age;
    }
}
