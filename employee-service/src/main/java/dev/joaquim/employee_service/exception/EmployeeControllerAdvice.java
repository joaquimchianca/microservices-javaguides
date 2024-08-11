package dev.joaquim.employee_service.exception;

import dev.joaquim.employee_service.dto.ErrorDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class EmployeeControllerAdvice {

    // exceção para entidade não encontrada
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleEntityNotFoundException(
            EntityNotFoundException ex,
            HttpServletRequest request)
    {
        ErrorDto error = new ErrorDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Entidade não encontrada.",
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                .body(error);
    }

    // formato de json errado
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ) {
        ErrorDto error = new ErrorDto(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                "JSON mal formatado.", request.getRequestURI());
        return ResponseEntity.status(400).body(error);
    }

    // json formatado, mas valores errados
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDto> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex,
            HttpServletRequest request
    ) {
        ErrorDto error = new ErrorDto(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                "JSON com nome do campo inválido.", request.getRequestURI());
        return ResponseEntity.status(400).body(error);
    }

    // método com URI inválida
    // HttpRequestMethodNotSupportedException
    // 405
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorDto> handleHttpRequestMethodNotSupportedException(
            HttpServletRequest request
    ) {
        ErrorDto error = new ErrorDto(LocalDateTime.now(), HttpStatus.METHOD_NOT_ALLOWED.value(),
                "URI inválida.", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).body(error);
    }
}
