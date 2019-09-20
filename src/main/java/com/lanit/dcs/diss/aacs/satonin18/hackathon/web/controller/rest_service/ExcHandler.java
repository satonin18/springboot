package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.controller.rest_service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExcHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(MyExc.class)
//    protected ResponseEntity handleMyExc(final MyExc exc) {
//        return new ResponseEntity(exc.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(MyExc2.class)
//    protected ResponseEntity handleMyExc2(final MyExc2 exc) {
//        return new ResponseEntity(exc.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    protected ResponseEntity handleRuntimeException(final RuntimeException exc) {
//        return new ResponseEntity(exc.getMessage(), HttpStatus.NOT_IMPLEMENTED);
//    }
//
//    @ExceptionHandler(Exception.class)
//    protected ResponseEntity handleException(final Exception exc) {
//        return new ResponseEntity(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}
////@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such user")//ovverite message
//class MyExc extends RuntimeException{
//    public MyExc(String message) {
//        super(message);
//    }
//}
//
////@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "NO CORRECT DATA")//ovverite message
//class MyExc2 extends RuntimeException{
//    public MyExc2(String message) {
//        super(message);
//    }
//}