package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.controller.rest_service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ExcHandler {
//    @ControllerAdvice
//    public class AwesomeExceptionHandler extends ResponseEntityExceptionHandler {
//
//        @ExceptionHandler(ThereIsNoSuchUserException.class)
//        protected ResponseEntity<AwesomeException> handleThereIsNoSuchUserException() {
//            return new ResponseEntity<>(new AwesomeException("There is no such user"), HttpStatus.NOT_FOUND);
//        }
//
//        @Data
//        @AllArgsConstructor
//        private static class AwesomeException {
//            private String message;
//        }
//
//
//    }
}

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such user")//ovverite message
class MyExc extends RuntimeException{
    public MyExc(String message) {
        super(message);
    }
}