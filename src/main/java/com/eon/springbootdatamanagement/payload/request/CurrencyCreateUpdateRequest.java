package com.eon.springbootdatamanagement.payload.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyCreateUpdateRequest extends MasterCreateUpdateRequest{
    private String description;
}
