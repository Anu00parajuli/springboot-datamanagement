package com.eon.springbootdatamanagement.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MasterCreateUpdateRequest {
    @NotNull(message = "Name must not be null")
    private String name;
}
