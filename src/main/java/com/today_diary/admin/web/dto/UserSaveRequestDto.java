package com.today_diary.admin.web.dto;

import com.today_diary.admin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    @NotEmpty
    private String name;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 5, message = "password should have at least 5 characters")
    private String password;
    @NotEmpty
    private String phoneNumber;
    private String sex;
    private int age;

    @Builder
    public UserSaveRequestDto(String name, String email, String password, String phoneNumber, String sex, int age){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.age = age;
    }
    public User toEntity() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .age(age)
                .sex(sex)
                .build();
    }
}
