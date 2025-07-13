package com.eon.springbootdatamanagement.payload.response;

import com.eon.springbootdatamanagement.enums.ResponseStatusEnum;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse {
    private HttpStatus httpStatus;
    private ResponseStatusEnum responseStatusEnum;
    private String message;
    private Object data;
    private String code;
}
