package com.example._250912_sprsecu_practice_oauth2resource.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRequest {
    private String username;
    private String password;
    private String scope;
}
