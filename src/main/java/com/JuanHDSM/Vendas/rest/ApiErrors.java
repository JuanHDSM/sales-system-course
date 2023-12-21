package com.JuanHDSM.Vendas.rest;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String errorMsg) {
        this.errors = Collections.singletonList(errorMsg);
    }
}
