package com.example.publicblogapp.requests.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequestBody {

    private String userName;
    private String email;
    private String password;
}
