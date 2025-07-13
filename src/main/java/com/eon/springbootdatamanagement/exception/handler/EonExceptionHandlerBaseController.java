package com.eon.springbootdatamanagement.exception.handler;

import com.eon.springbootdatamanagement.enums.ResponseStatusEnum;
import com.eon.springbootdatamanagement.exception.EonException;
import com.eon.springbootdatamanagement.payload.response.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class EonExceptionHandlerBaseController {

    @ExceptionHandler(EonException.class)
    public ResponseEntity<APIResponse> handleEonException(EonException e) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(e.getMessage());
        apiResponse.setCode(e.getCode());
        apiResponse.setHttpStatus(e.getHttpStatus());
        apiResponse.setData("error");
        apiResponse.setResponseStatusEnum(ResponseStatusEnum.FAILED);
        return new ResponseEntity<>(apiResponse, e.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse> handleException(Exception e) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(e.getMessage());
//        apiResponse.setCode("000000");
        apiResponse.setData("error");
        apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
