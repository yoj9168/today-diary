package com.today_diary.admin.web;

import com.today_diary.admin.domain.user.User;
import com.today_diary.admin.domain.user.UserRepository;
import com.today_diary.admin.web.dto.user.UserSaveRequestDto;
import com.today_diary.admin.web.dto.user.UserUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository repository;

    @After
    public void tearDown() throws Exception{
        repository.deleteAll();
    }

    @Test
    public void 회원가입() throws Exception{
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String name = "유오준";
        String email = "yoj9168@gmail.com";
        String password = "test1234";
        String passwordConfirm = "test1234";
        String phoneNumber = "010-4059-9168";
        UserSaveRequestDto dto = UserSaveRequestDto.builder()
                .name(name)
                .email(email)
                .password(password)
                .passwordConfirm(passwordConfirm)
                .phoneNumber(phoneNumber)
                .build();
        String url = "http://localhost:"+port+"/user";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, dto, Long.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<User> all = repository.findAll();

        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getEmail()).isEqualTo(email);
        assertThat(Boolean.TRUE).isEqualTo(encoder.matches(password, all.get(0).getPassword()));
        assertThat(all.get(0).getPhoneNumber()).isEqualTo(phoneNumber);

    }
    @Test
    public void 회원정보_수정() throws Exception{
        User user = repository.save(User.builder()
                .name("name")
                .email("email@naver.com")
                .password("password")
                .phoneNumber("010-1234-5678")
                .age(24)
                .sex("M")
                .build());

        Long updateId = user.getId();
        String sex = "F";
        int age = 32;
        String phoneNumber = "010-4567-5415";

        UserUpdateRequestDto dto = UserUpdateRequestDto.builder()
                .age(age)
                .sex(sex)
                .phoneNumber(phoneNumber)
                .build();

        String url = "http://localhost:"+port+"/user/"+updateId;
        HttpEntity<UserUpdateRequestDto> requestEntity = new HttpEntity<>(dto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<User> all = repository.findAll();

        assertThat(all.get(0).getSex()).isEqualTo(sex);
        assertThat(all.get(0).getAge()).isEqualTo(age);
        assertThat(all.get(0).getPhoneNumber()).isEqualTo(phoneNumber);

    }
}
