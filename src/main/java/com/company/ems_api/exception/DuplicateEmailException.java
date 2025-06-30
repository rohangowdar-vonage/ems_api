package com.company.ems_api.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;


public class DuplicateEmailException extends RuntimeException
{
    public DuplicateEmailException(String message)
    {
        super(message);
    }
}
