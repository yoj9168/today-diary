package com.today_diary.admin.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    @NotEmpty
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
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
