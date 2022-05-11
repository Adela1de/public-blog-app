package com.example.publicblogapp.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {

    private Long timeStamp;
    private String message;
    private Integer status;
}
