package com.example.code_of_arms_hackaton_case_2.api.controllers;


import com.example.code_of_arms_hackaton_case_2.entity.BankUser;
import com.example.code_of_arms_hackaton_case_2.entity.BonusCategory;
import com.example.code_of_arms_hackaton_case_2.repository.BankUserRepository;
import com.example.code_of_arms_hackaton_case_2.security.AuthenticationRequest;
import com.example.code_of_arms_hackaton_case_2.security.JwtGenerator;
import com.example.code_of_arms_hackaton_case_2.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtGenerator jwtGenerator;
    private final BankUserRepository bankUserRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid AuthenticationRequest registrationRequest) {
        String login = registrationRequest.getLogin();
        String password = registrationRequest.getPassword();
        BankUser bankUser = userService.findByLogin(login);
        if (bankUser == null) {
            BankUser user = new BankUser();
            user.setLogin(login);
            user.setPassword(password);
            try {
                userService.saveUser(user);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());
            }
            return ResponseEntity.status(HttpStatus.OK).body("The account was successfully registered!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The account already exists!");
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody @Valid AuthenticationRequest authenticationRequest,
                                       HttpServletResponse httpServletResponse) {
        String login = authenticationRequest.getLogin();
        if (bankUserRepository.findBankUserByLogin(authenticationRequest.getLogin()) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Such account doesn't exist!");
        }
        BankUser bankUser = userService.findByLoginAndPassword(login, authenticationRequest.getPassword());
        if (bankUser != null) {
            String token = jwtGenerator.generateToken(login);
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            httpServletResponse.addCookie(cookie);
            return ResponseEntity.status(HttpStatus.OK).body("Login was successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect password");
        }
    }
    @GetMapping("/users")
    public List<BankUser> users() {
        return bankUserRepository.findAll();
    }

    @PostMapping("/logout")
    public void logout() {}




}
