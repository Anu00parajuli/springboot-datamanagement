package com.eon.springbootdatamanagement.payload.response;

import com.eon.springbootdatamanagement.enums.StatusEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyResponse {
    private Long id;
    private String name;
    private String description;
    private StatusEnum status;
}
