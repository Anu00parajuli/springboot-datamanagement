package com.eon.springbootdatamanagement.exception;

import com.eon.springbootdatamanagement.component.MessageBundle;
import com.eon.springbootdatamanagement.enums.ResponseStatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class EonException extends Exception {
    private final HttpStatus httpStatus;
    private final ResponseStatusEnum responseStatusEnum;
    private final String debugMessage;
    private final String code;

    public EonException(String debugMessage, HttpStatus httpStatus, ResponseStatusEnum responseStatusEnum, String code) {
        super(MessageBundle.getMessage(code));
        this.httpStatus = httpStatus;
        this.responseStatusEnum = responseStatusEnum;
        this.debugMessage = debugMessage;
        this.code = code;
    }

    public EonException(String code, HttpStatus httpStatus){
        super(MessageBundle.getMessage(code));
        this.code = code;
        this.httpStatus = httpStatus;
        this.responseStatusEnum = ResponseStatusEnum.FAILED;
        this.debugMessage= "";

    }
}
