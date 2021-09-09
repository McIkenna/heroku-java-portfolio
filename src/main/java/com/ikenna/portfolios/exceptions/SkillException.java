package com.ikenna.portfolios.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SkillException extends RuntimeException{

    public SkillException(String message) {
        super(message);
    }
}
