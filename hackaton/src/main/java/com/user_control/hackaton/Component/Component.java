package com.user_control.hackaton.Component;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@org.springframework.stereotype.Component
public class Component {
    @Bean
    public PasswordEncoder getPassword(){
        return new BCryptPasswordEncoder(8);
    }
}
