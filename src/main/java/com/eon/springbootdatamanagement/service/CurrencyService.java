package com.eon.springbootdatamanagement.service;

import com.eon.springbootdatamanagement.exception.GlobalException;
import com.eon.springbootdatamanagement.payload.request.CurrencyCreateUpdateRequest;
import com.eon.springbootdatamanagement.payload.request.CurrencyDataRequest;
import com.eon.springbootdatamanagement.payload.request.StatusUpdateRequest;
import com.eon.springbootdatamanagement.payload.response.GlobalResponse;

public interface CurrencyService {

    GlobalResponse createCurrency(CurrencyCreateUpdateRequest currencyCreateUpdateRequest) throws GlobalException;

    GlobalResponse updateCurrency(CurrencyCreateUpdateRequest currencyCreateUpdateRequest, Long id) throws GlobalException;

    GlobalResponse updateCurrencyStatus(Long id, StatusUpdateRequest statusUpdateRequest) throws GlobalException;

    GlobalResponse getCurrencyById(Long id) throws GlobalException;

    GlobalResponse getAllCurrencies() throws GlobalException;

    GlobalResponse getAllPaginatedCurrencies(CurrencyDataRequest currencyDataRequest) throws GlobalException;
}
