package com.example.publicblogapp.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TokenNotFoundException extends RuntimeException{

    public TokenNotFoundException(String message)
    {
        super(message);
    }
}
