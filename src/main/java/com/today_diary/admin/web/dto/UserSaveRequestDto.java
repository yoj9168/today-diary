package com.today_diary.admin.web.dto;

import com.today_diary.admin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String name;
    private String email;
    private String password;
    private String phoneNum;

    @Builder
    public UserSaveRequestDto(String name, String email, String password, String phoneNum){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
    }
    public User toEntity() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .phoneNum(phoneNum)
                .build();
    }
}
