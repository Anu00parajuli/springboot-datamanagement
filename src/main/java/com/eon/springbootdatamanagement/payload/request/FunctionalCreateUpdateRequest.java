package com.eon.springbootdatamanagement.payload.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FunctionalCreateUpdateRequest extends  MasterCreateUpdateRequest{
    private String type;
}
