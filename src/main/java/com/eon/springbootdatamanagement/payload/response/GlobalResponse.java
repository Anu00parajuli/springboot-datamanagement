package com.eon.springbootdatamanagement.payload.response;

import com.eon.springbootdatamanagement.enums.ApiStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalResponse {
    private ApiStatusEnum status;
    @JsonIgnore
    private HttpStatus httpStatus;
    private String code;
    private Object data;
    private String message;
    private String debugMessage;
}
