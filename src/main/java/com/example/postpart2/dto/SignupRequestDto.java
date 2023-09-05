package com.example.postpart2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    // https://velog.io/@msung99/SpringBoot-Regex-%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%ED%95%84%EB%93%9C-%EC%9C%A0%ED%9A%A8%EC%84%B1-%EA%B2%80%EC%82%AC-y0sq1j96
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "소문자와 숫자를 사용하여 4자리~10자리로 만드세요")
    private String username;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9]{8,15}$", message = "대소문자와 숫자를 이용하여 8자리~15자리로 만들세요")
    private String password;

//    @Email // @Pattern(정규표현식)
//    @Pattern(regexp = "^(.+)@(.+)$")
//    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
//    @Pattern(regexp = "^[A-Za-z0-9+_!#$%&*+/=?`{|}~^.-]+@[A-Za-z0-9+_.-]+$")
//    @NotBlank
//    private String email;
    private boolean admin = false;
    private String adminToken = "";
}