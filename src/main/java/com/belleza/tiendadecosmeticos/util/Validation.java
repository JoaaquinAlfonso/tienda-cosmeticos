package com.belleza.tiendadecosmeticos.util;

import com.belleza.tiendadecosmeticos.exceptions.RequestBodyException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

public class Validation {

    public static void validarParametros(BindingResult result) throws RequestBodyException {
        if (result.hasErrors()) {
            String errorsValidation = collectErrorsToString(result);
            throw new RequestBodyException(errorsValidation);
        }
    }

    private static String collectErrorsToString(BindingResult result) {
        List<ObjectError> objectErrorList = result.getAllErrors().stream().collect(Collectors.toList());
        StringBuilder errorsStringBuilder = new StringBuilder();

        for (ObjectError objectError : objectErrorList) {
            FieldError fieldError = (FieldError) objectError;

            if (!(errorsStringBuilder.indexOf(fieldError.getField()) != -1)) {
                errorsStringBuilder.append(fieldError.getField() + " : " + fieldError.getDefaultMessage() + " | ");
            }
        }
        return errorsStringBuilder.substring(0, errorsStringBuilder.length() - 3);
    }
}
