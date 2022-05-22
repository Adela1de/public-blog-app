package com.example.publicblogapp.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String message)
    {
        super(message);
    }
}
