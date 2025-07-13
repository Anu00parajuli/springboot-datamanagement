package com.eon.springbootdatamanagement.service;

import com.eon.springbootdatamanagement.enums.StatusEnum;
import com.eon.springbootdatamanagement.exception.EonException;
import com.eon.springbootdatamanagement.payload.request.CurrencyCreateUpdateRequest;
import com.eon.springbootdatamanagement.payload.request.CurrencyDataRequest;
import com.eon.springbootdatamanagement.payload.request.StatusUpdateRequest;
import com.eon.springbootdatamanagement.payload.response.APIResponse;

public interface CurrencyService {

    APIResponse createCurrency(CurrencyCreateUpdateRequest currencyCreateUpdateRequest) throws EonException;

    APIResponse updateCurrency(CurrencyCreateUpdateRequest currencyCreateUpdateRequest, Long id) throws EonException;

    APIResponse updateCurrencyStatus(Long id, StatusUpdateRequest statusUpdateRequest) throws EonException;

    APIResponse getCurrencyById(Long id) throws EonException;

    APIResponse getAllCurrencies() throws EonException;

    APIResponse getAllPaginatedCurrencies(CurrencyDataRequest currencyDataRequest) throws EonException;
}
