package com.eon.springbootdatamanagement.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyDataRequest extends PaginationRequest{
    private String searchText;
    private String currency;
}
