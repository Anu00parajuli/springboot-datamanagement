package com.eon.springbootdatamanagement.controller;

import com.eon.springbootdatamanagement.annotation.CurrencyRestController;
import com.eon.springbootdatamanagement.exception.EonException;
import com.eon.springbootdatamanagement.payload.request.CurrencyCreateUpdateRequest;
import com.eon.springbootdatamanagement.payload.request.CurrencyDataRequest;
import com.eon.springbootdatamanagement.payload.request.StatusUpdateRequest;
import com.eon.springbootdatamanagement.payload.response.APIResponse;
import com.eon.springbootdatamanagement.service.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CurrencyRestController
public class CurrencyController {
    private final CurrencyService currencyService;
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createCurrency(@RequestBody CurrencyCreateUpdateRequest currencyCreateUpdateRequest) throws EonException {
        return ResponseEntity.ok(currencyService.createCurrency(currencyCreateUpdateRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse> updateCurrency(@RequestBody CurrencyCreateUpdateRequest currencyCreateUpdateRequest, @RequestHeader Long id) throws EonException {
        return ResponseEntity.ok(currencyService.updateCurrency(currencyCreateUpdateRequest, id));
    }

    @PatchMapping("/updateStatus")
    public ResponseEntity<APIResponse> updateCurrencyStatus(@RequestHeader Long id, @RequestBody StatusUpdateRequest statusUpdateRequest) throws EonException {
        return ResponseEntity.ok(currencyService.updateCurrencyStatus(id, statusUpdateRequest));
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<APIResponse> findCurrencyById(@RequestHeader Long id) throws EonException {
        return ResponseEntity.ok(currencyService.getCurrencyById(id));
    }

    @GetMapping("/find-all")
    public ResponseEntity<APIResponse> findAllCurrency() throws EonException {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }

    @PostMapping("/find-paginated-data")
    public ResponseEntity<APIResponse> findCurrencyPaginatedData(@RequestBody CurrencyDataRequest currencyDataRequest) throws EonException {
        return ResponseEntity.ok(currencyService.getAllPaginatedCurrencies(currencyDataRequest));
    }

}
