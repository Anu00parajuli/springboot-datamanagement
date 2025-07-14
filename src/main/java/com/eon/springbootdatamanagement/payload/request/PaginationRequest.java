package com.eon.springbootdatamanagement.payload.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationRequest {
    @Min(value = 0, message = "{pagination.pageNo.min}")
    private int pageNo;
    @Min(value = 5, message = "{pagination.pageSize.min}")
    @Max(value = 20, message = "{pagination.pageSize.max}")
    private int pageSize;
    private String sortBy;
    private String direction;
}
