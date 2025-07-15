package com.eon.springbootdatamanagement.controller;

import com.eon.springbootdatamanagement.annotation.CurrencyRestController;
import com.eon.springbootdatamanagement.enums.ApiStatusEnum;
import com.eon.springbootdatamanagement.exception.GlobalException;
import com.eon.springbootdatamanagement.payload.request.CurrencyCreateUpdateRequest;
import com.eon.springbootdatamanagement.payload.request.CurrencyDataRequest;
import com.eon.springbootdatamanagement.payload.request.StatusUpdateRequest;
import com.eon.springbootdatamanagement.payload.response.GlobalResponse;
import com.eon.springbootdatamanagement.service.CurrencyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CurrencyRestController
public class CurrencyController {
    private final CurrencyService currencyService;
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping("/create")
    public ResponseEntity<GlobalResponse> createCurrency(@RequestBody  @Valid CurrencyCreateUpdateRequest currencyCreateUpdateRequest) throws GlobalException {
        return ResponseEntity.ok(currencyService.createCurrency(currencyCreateUpdateRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<GlobalResponse> updateCurrency(@RequestBody CurrencyCreateUpdateRequest currencyCreateUpdateRequest, @RequestHeader Long id) throws GlobalException {
        GlobalResponse.builder().status(ApiStatusEnum.SUCCESS).httpStatus(HttpStatus.OK).build();
        return ResponseEntity.ok(currencyService.updateCurrency(currencyCreateUpdateRequest, id));
    }

    @PatchMapping("/updateStatus")
    public ResponseEntity<GlobalResponse> updateCurrencyStatus(@RequestHeader Long id, @RequestBody StatusUpdateRequest statusUpdateRequest) throws GlobalException {
        return ResponseEntity.ok(currencyService.updateCurrencyStatus(id, statusUpdateRequest));
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<GlobalResponse> findCurrencyById(@RequestHeader Long id) throws GlobalException {
        return ResponseEntity.ok(currencyService.getCurrencyById(id));
    }

    @GetMapping("/find-all")
    public ResponseEntity<GlobalResponse> findAllCurrency() throws GlobalException {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }

    @PostMapping("/find-paginated-data")
    public ResponseEntity<GlobalResponse> findCurrencyPaginatedData(@RequestBody @Valid CurrencyDataRequest currencyDataRequest) throws GlobalException {
        return ResponseEntity.ok(currencyService.getAllPaginatedCurrencies(currencyDataRequest));
    }

}
