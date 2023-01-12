package com.today_diary.admin.web.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 5, message = "password should have at least 5 characters")
    private String password;

}
