package com.JuanHDSM.Vendas.rest.controllers;

import com.JuanHDSM.Vendas.exception.BusinessRulesException;
import com.JuanHDSM.Vendas.exception.OrderNotFoundException;
import com.JuanHDSM.Vendas.rest.ApiErrors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessRulesException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handlersBusinessException(BusinessRulesException ex) {
        String errorMsg = ex.getMessage();
        return new ApiErrors(errorMsg);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ApiErrors handlersOrderNotFoundException(OrderNotFoundException ex) {
        String errorMsg = ex.getMessage();
        return new ApiErrors(errorMsg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handlerMethodNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

        return new ApiErrors(errors);
    }
}
