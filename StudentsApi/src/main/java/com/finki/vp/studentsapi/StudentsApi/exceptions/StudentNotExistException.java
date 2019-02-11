package com.finki.vp.studentsapi.StudentsApi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class StudentNotExistException extends RuntimeException {

    public StudentNotExistException(String message) {
        super(message);
    }
}
