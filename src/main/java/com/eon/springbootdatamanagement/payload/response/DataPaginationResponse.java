package com.eon.springbootdatamanagement.payload.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataPaginationResponse {
    long totalElementCount;
    List<?> result;
}
