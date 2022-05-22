package com.example.publicblogapp.requests.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogInRequestBody {

    private String email;
    private String password;
}
