package pl.edu.pb.wi.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.edu.pb.wi.productservice.dtos.ExceptionDto;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleResourceNotFoundException(ResourceNotFoundException ex) {

        ExceptionDto errorResponse = new ExceptionDto(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                "The requested resource does not exist or has been removed.");

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
