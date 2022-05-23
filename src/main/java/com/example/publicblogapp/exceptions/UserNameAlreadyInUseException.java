package com.example.publicblogapp.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNameAlreadyInUseException extends RuntimeException{

    public UserNameAlreadyInUseException(String message)
    {
        super(message);
    }
}
