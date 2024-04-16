package com.example.demo.core.utils.exceptions.types;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BusinessProblemDetails extends ProblemDetails{
    public BusinessProblemDetails (String details) {
        setDetail(details);
        setTitle("Business Rule Violation");
        setType("Business Exception");

    }
}
