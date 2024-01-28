package com.example.be_adm_double_shop.dto;

import lombok.Getter;
import lombok.Setter;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String code;
    private String message;

    public ValidationException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ValidationException(Exception e, String message) {
        super(e);
        this.message = message;
    }

    public ValidationException(Exception e, String code, String message) {
        super(e);
        this.code = code;
        this.message = message;
    }

    public ValidationException withErrorCode(String code) {
        this.code = code;
        return this;
    }

    public ValidationException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return this.message;
    }


    @Override
    public String toString() {
        return this.message;
    }

}
