package com.eon.springbootdatamanagement.payload.request;

import com.eon.springbootdatamanagement.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusUpdateRequest {
    private StatusEnum status;
}
