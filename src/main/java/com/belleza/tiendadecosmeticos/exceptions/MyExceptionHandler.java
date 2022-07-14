package com.belleza.tiendadecosmeticos.exceptions;


import com.belleza.tiendadecosmeticos.dto.ResponseInfoDTO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ResponseInfoDTO> entityNotFoundException(EntityNotFoundException entityNotFoundException, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseInfoDTO(entityNotFoundException.getMessage(),
                request.getServletPath(),
                HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler({DataBaseException.class})
    public ResponseEntity<ResponseInfoDTO> dataBaseException(DataBaseException dataBaseException, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseInfoDTO(dataBaseException.getMessage(),
                request.getServletPath(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<ResponseInfoDTO> emptyResultDataAccesException(EmptyResultDataAccessException emptyResultDataAccessException, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseInfoDTO(emptyResultDataAccessException.getMessage(),
                request.getServletPath(),
                HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler({RequestBodyException.class})
    public ResponseEntity<ResponseInfoDTO> requestBodyException(RequestBodyException requestBodyException, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfoDTO(requestBodyException.getMessage(),
                request.getServletPath(),
                HttpStatus.BAD_REQUEST.value()));
    }


}
