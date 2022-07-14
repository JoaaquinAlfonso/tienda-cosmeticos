package com.belleza.tiendadecosmeticos.exceptions;

import lombok.Getter;

@Getter
public class RequestBodyException extends RuntimeException {
    private static final long serialVersionUID = 2832423610836937340L;

    public RequestBodyException(String message) {
        super(message);
    }

}
