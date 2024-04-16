package com.example.demo.core.configurations;

import com.example.demo.core.utils.exceptions.details.BusinessExceptions;
import com.example.demo.core.utils.exceptions.types.BusinessProblemDetails;
import com.example.demo.core.utils.exceptions.types.ValidationProblemDetails;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(BusinessExceptions.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessProblemDetails businessExceptionHandle(BusinessExceptions exception){

        BusinessProblemDetails businessProblemDetails = new BusinessProblemDetails(exception.getMessage());

        return businessProblemDetails;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationProblemDetails validationExceptionHandle(MethodArgumentNotValidException exception){
        List<String> errorMessages = new ArrayList<>();

            List<FieldError> errors = exception.getBindingResult().getFieldErrors();

            for (FieldError error:errors){
                errorMessages.add(error.getDefaultMessage());
            }
            ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails(errorMessages);

            return validationProblemDetails;


    }
}
