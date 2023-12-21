package com.JuanHDSM.Vendas.rest.controllers;

import com.JuanHDSM.Vendas.exception.BusinessRulesException;
import com.JuanHDSM.Vendas.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AplicationControllerAdvice {

    @ExceptionHandler(BusinessRulesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handlersBussinessException(BusinessRulesException ex) {
        String errorMsg = ex.getMessage();
        return new ApiErrors(errorMsg);
    }
}
