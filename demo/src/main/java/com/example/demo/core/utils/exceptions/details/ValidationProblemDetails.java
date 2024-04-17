package com.example.demo.core.utils.exceptions.details;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ValidationProblemDetails extends ProblemDetails{
    List<String> errors;

    public ValidationProblemDetails(List<String> errors) {
        setTitle("Validation Violation");
        setDetail("One or more validation error(s)!");
        setType("Validation Exception");
        this.errors = errors;
    }
}