package com.user_control.hackaton.DTO;

import lombok.Data;
@Data
public class Login {
    private Integer id;

    public Login(Integer id) {
        this.id = id;
    }

    public Login() {
    }
}