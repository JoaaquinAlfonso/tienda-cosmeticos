package com.belleza.tiendadecosmeticos.exceptions;

public class DataBaseException extends RuntimeException {
    private static final long serialVersionUID = 1942423610285637340L;

    public DataBaseException(String message) {
        super(message);
    }

}
