package com.boutiquerugsmw.errors;

import org.springframework.http.HttpStatus;


public enum ErrorType {
    TEST_NOT_FOUND ("Test not found: %s", HttpStatus.NOT_FOUND),
    VALIDATION_ERRORS ("Validation errors", HttpStatus.BAD_REQUEST);

    private String messageErrorTemplate;

    private HttpStatus errorStatus;

    ErrorType(String messageErrorTemplate, HttpStatus status) {
        this.messageErrorTemplate = messageErrorTemplate;
        this.errorStatus = status;
    }

    public String getMessageErrorTemplate() {
        return messageErrorTemplate;
    }

    public HttpStatus getErrorStatus() {
        return errorStatus;
    }

    @Override
    public String toString() {
        return "ErrorType{" +
                "messageErrorTemplate='" + messageErrorTemplate + '\'' +
                ", status=" + errorStatus +
                "} " + super.toString();
    }

}