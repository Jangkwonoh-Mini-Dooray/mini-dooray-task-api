package com.nhnacademy.minidooraytaskapi.util;

import com.nhnacademy.minidooraytaskapi.exception.PostDtoException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public interface ValidateParam {
    default void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            List<String> errorMessages = new ArrayList<>();
            for (ObjectError error : errors) {
                errorMessages.add(error.getDefaultMessage());
            }
            throw new PostDtoException(errorMessages);
        }
    }
}
