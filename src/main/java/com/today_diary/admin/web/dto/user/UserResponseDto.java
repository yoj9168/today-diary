package com.today_diary.admin.web.dto.user;

import com.today_diary.admin.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private String name;
    private String email;
    private String phoneNumber;

    public UserResponseDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }
}
