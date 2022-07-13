package com.example.code_of_arms_hackaton_case_2.security;

import com.example.code_of_arms_hackaton_case_2.entity.BankUser;
import com.example.code_of_arms_hackaton_case_2.repository.BankUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final BankUserRepository bankUserRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BankUser bankUser = bankUserRepository.findBankUserByLogin(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(bankUser);
    }
}