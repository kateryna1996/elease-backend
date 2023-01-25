package nl.klev.eleasebackend.controllers;

import nl.klev.eleasebackend.exceptions.BadRequestException;
import nl.klev.eleasebackend.exceptions.RecordNotFoundException;
import nl.klev.eleasebackend.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception(UserNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> exception(BadRequestException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }

}

