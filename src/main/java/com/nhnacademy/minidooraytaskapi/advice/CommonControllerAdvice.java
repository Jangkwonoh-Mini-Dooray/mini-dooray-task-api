package com.nhnacademy.minidooraytaskapi.advice;

import com.nhnacademy.minidooraytaskapi.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CommonControllerAdvice {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }

    @ExceptionHandler({NotFoundMilestoneException.class, NotFoundProjectAuthorityException.class, NotFoundProjectException.class,
            NotFoundProjectMemberException.class, NotFoundProjectStatusException.class, NotFoundTaskException.class})
    public ResponseEntity<ErrorMessage> notFoundException(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
