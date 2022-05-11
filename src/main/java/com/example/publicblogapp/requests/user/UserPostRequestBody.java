package com.example.publicblogapp.requests.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPostRequestBody {

    private String userName;
    private String email;
    private String passWord;
}
