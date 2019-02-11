package com.finki.vp.studentsapi.StudentsApi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StudentMissingParametarException extends RuntimeException {

    public StudentMissingParametarException(String message) {
        super(message);
    }
}
