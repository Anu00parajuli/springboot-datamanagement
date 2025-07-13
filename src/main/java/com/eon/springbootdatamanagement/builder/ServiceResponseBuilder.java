package com.eon.springbootdatamanagement.builder;

import com.eon.springbootdatamanagement.component.MessageBundle;
import com.eon.springbootdatamanagement.enums.ResponseStatusEnum;
import com.eon.springbootdatamanagement.payload.response.APIResponse;
import org.springframework.http.HttpStatus;

public class ServiceResponseBuilder {

    private ServiceResponseBuilder(){
    }

    public static APIResponse buildSuccessResponse(Object object){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setResponseStatusEnum(ResponseStatusEnum.SUCCESS);
        apiResponse.setData(object);
        return apiResponse;
    }

    public static APIResponse buildSuccessResponse(String code, Object object){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setHttpStatus(HttpStatus.OK);
        apiResponse.setResponseStatusEnum(ResponseStatusEnum.SUCCESS);
        apiResponse.setData(object);
        apiResponse.setMessage(MessageBundle.getMessage(code));
        return apiResponse;
    }

    public static APIResponse buildSuccessResponse(String code){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setHttpStatus(HttpStatus.OK);
        apiResponse.setResponseStatusEnum(ResponseStatusEnum.SUCCESS);
        apiResponse.setMessage(MessageBundle.getMessage(code));
        apiResponse.setCode(code);
        return apiResponse;
    }
}
