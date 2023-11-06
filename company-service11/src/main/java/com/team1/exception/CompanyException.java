package com.team1.exception;

import lombok.Getter;

@Getter
public class CompanyException extends RuntimeException {

    private final ErrorType errorType;

    public CompanyException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public CompanyException(ErrorType errorType, String customMessage){
        super(customMessage);
        this.errorType=errorType;
    }

}
