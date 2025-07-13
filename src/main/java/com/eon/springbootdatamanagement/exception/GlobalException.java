package com.eon.springbootdatamanagement.exception;

import com.eon.springbootdatamanagement.component.MessageBundle;
import com.eon.springbootdatamanagement.enums.ApiStatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class GlobalException extends Exception {
    private final HttpStatus httpStatus;
    private final ApiStatusEnum apiStatusEnum;
    private final String debugMessage;
    private final String code;

    public GlobalException(String debugMessage, HttpStatus httpStatus, ApiStatusEnum apiStatusEnum, String code) {
        super(MessageBundle.getMessage(code));
        this.httpStatus = httpStatus;
        this.apiStatusEnum = apiStatusEnum;
        this.debugMessage = debugMessage;
        this.code = code;
    }

    public GlobalException(String code, HttpStatus httpStatus){
        super(MessageBundle.getMessage(code));
        this.code = code;
        this.httpStatus = httpStatus;
        this.apiStatusEnum = ApiStatusEnum.FAILED;
        this.debugMessage= "";

    }
}
