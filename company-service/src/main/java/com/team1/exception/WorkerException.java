package com.team1.exception;

import lombok.Getter;

@Getter
public class WorkerException extends RuntimeException{

    private final ErrorType errorType;

    public WorkerException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public WorkerException(ErrorType errorType, String customMessage){
        super(customMessage);
        this.errorType=errorType;
    }
}
