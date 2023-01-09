package com.today_diary.admin.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String phoneNumber;
    private String sex;
    private int age;

    @Builder
    public UserUpdateRequestDto(String phoneNumber, String sex, int age){
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.age = age;
    }


}