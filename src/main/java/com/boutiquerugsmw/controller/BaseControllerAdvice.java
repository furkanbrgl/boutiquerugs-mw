package com.boutiquerugsmw.controller;


import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseControllerAdvice {

    private static final Logger logger = Logger.getLogger(BaseControllerAdvice.class);

/*
    @Autowired
    private MailUtil mailUtil;

    public void setMailUtil(MailUtil mailUtil) {
        this.mailUtil = mailUtil;
    }
*/

    @ExceptionHandler(Exception.class)
    public @ResponseBody ResponseEntity <String> handleException(Exception e) {

        logger.error("QaMiddleware Exception Handler : " + e.getMessage(), e);
        return new ResponseEntity <String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}