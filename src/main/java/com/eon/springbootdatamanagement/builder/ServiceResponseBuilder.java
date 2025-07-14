package com.eon.springbootdatamanagement.builder;

import com.eon.springbootdatamanagement.component.MessageBundle;
import com.eon.springbootdatamanagement.enums.ApiStatusEnum;
import com.eon.springbootdatamanagement.exception.GlobalException;
import com.eon.springbootdatamanagement.payload.response.GlobalResponse;

public class ServiceResponseBuilder {

    private ServiceResponseBuilder() {
    }

    public static GlobalResponse buildSuccessResponse(String message) {
        return GlobalResponse.builder()
                .status(ApiStatusEnum.SUCCESS)
                .data(message)
                .build();
    }

    public static GlobalResponse buildSuccessResponse(String code, Object object) {
        return GlobalResponse.builder()
                .status(ApiStatusEnum.SUCCESS)
                .message(MessageBundle.getMessage(code))
                .data(object)
                .build();
    }

    public static GlobalResponse buildSuccessResponseWithMessage(String message, Object object) {
        return GlobalResponse.builder()
                .status(ApiStatusEnum.SUCCESS)
                .message(message)
                .data(object)
                .build();
    }

    public static GlobalResponse buildFailResponse(GlobalException e) {
        return GlobalResponse.builder()
                .status(ApiStatusEnum.FAILED)
                .message(e.getMessage())
                .code(e.getCode())
                .build();
    }

    public static GlobalResponse buildUnknownFailResponse(Exception e) {
        return GlobalResponse.builder()
                .status(ApiStatusEnum.FAILED)
                .message(e.getMessage())
                .build();
    }

    public static GlobalResponse buildPendingResponse(GlobalException e) {
        return GlobalResponse.builder()
                .status(ApiStatusEnum.PENDING)
                .message(e.getMessage())
                .build();
    }
}
