package com.example.code_of_arms_hackaton_case_2.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@Setter
public class AuthenticationRequest {
    private String login;
    private String password;
}
