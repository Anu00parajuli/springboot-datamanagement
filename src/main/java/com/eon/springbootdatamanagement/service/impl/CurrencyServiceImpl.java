package com.eon.springbootdatamanagement.service.impl;

import com.eon.springbootdatamanagement.builder.ServiceResponseBuilder;
import com.eon.springbootdatamanagement.entity.CurrencyEntity;
import com.eon.springbootdatamanagement.enums.StatusEnum;
import com.eon.springbootdatamanagement.exception.EonException;
import com.eon.springbootdatamanagement.payload.request.CurrencyCreateUpdateRequest;
import com.eon.springbootdatamanagement.payload.request.CurrencyDataRequest;
import com.eon.springbootdatamanagement.payload.request.StatusUpdateRequest;
import com.eon.springbootdatamanagement.payload.response.APIResponse;
import com.eon.springbootdatamanagement.payload.response.CurrencyResponse;
import com.eon.springbootdatamanagement.repository.CurrencyRepository;
import com.eon.springbootdatamanagement.service.CurrencyService;
import com.eon.springbootdatamanagement.specification.CurrencySpecification;
import com.eon.springbootdatamanagement.util.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final MessageSource messageSource;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, MessageSource messageSource) {
        this.currencyRepository = currencyRepository;
        this.messageSource = messageSource;
    }

    @Override
    public APIResponse createCurrency(CurrencyCreateUpdateRequest currencyCreateUpdateRequest) throws EonException {
        if (currencyRepository.existsCurrencyEntityByName(currencyCreateUpdateRequest.getName())) {
            throw new EonException("CMS001", HttpStatus.CONFLICT);
        }
        if (currencyCreateUpdateRequest.getName() == null) {
            throw new EonException("CMS002", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        CurrencyEntity currencyEntity = CurrencyEntity.builder()
                .name(currencyCreateUpdateRequest.getName())
                .description(currencyCreateUpdateRequest.getDescription())
                .build();
        currencyRepository.save(currencyEntity);
        log.info("currency has been created successful");
        return ServiceResponseBuilder.buildSuccessResponse(messageSource.getMessage("currency.create.success", null, LocaleContextHolder.getLocale()));
    }

    @Override
    public APIResponse updateCurrency(CurrencyCreateUpdateRequest currencyCreateUpdateRequest, Long id) throws EonException {
        if (!currencyRepository.existsById(id)) {
            throw new EonException("CMS002", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        CurrencyEntity currency = currencyRepository.findById(id).get();
        if (currencyCreateUpdateRequest.getName() != null) {
            currency.setName(currencyCreateUpdateRequest.getName());
        }
        if (currencyCreateUpdateRequest.getDescription() != null) {
            currency.setDescription(currencyCreateUpdateRequest.getDescription());
        }
        currencyRepository.save(currency);
        log.info("currency has been updated successfully");
        return ServiceResponseBuilder.buildSuccessResponse(messageSource.getMessage("currency.update.success", null, LocaleContextHolder.getLocale()));

    }

    @Override
    public APIResponse updateCurrencyStatus(Long id, StatusUpdateRequest statusUpdateRequest) throws EonException {
        if (!currencyRepository.existsById(id)) {
            throw new EonException("CMS002", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        CurrencyEntity currency = currencyRepository.findCurrencyEntityById(id);

        if (currency.getStatus() == StatusEnum.DELETED) {
            throw new EonException("CMS004", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        currency.setStatus(statusUpdateRequest.getStatus());
        currencyRepository.save(currency);
        log.info("status of currency has been updated successfully");
        return ServiceResponseBuilder.buildSuccessResponse(messageSource.getMessage("currency.update.success", null, LocaleContextHolder.getLocale()));
    }

    @Override
    public APIResponse getCurrencyById(Long id) throws EonException {
        if (!currencyRepository.existsById(id)) {
            throw new EonException("CMS002", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        CurrencyEntity currency = currencyRepository.findCurrencyEntityById(id);
        CurrencyResponse currencyResponse = createCurrencyResponse(currency);
        return ServiceResponseBuilder.buildSuccessResponse("Currency exists", currencyResponse);

    }

    @Override
    public APIResponse getAllCurrencies() throws EonException {
        List<CurrencyEntity> currencies = currencyRepository.findAll();
        List<CurrencyResponse> currencyResponseList = currencies.stream().map(currency -> createCurrencyResponse(currency)).toList();
        return ServiceResponseBuilder.buildSuccessResponse("All currencies listed: ", currencyResponseList);
    }

    @Override
    public APIResponse getAllPaginatedCurrencies(CurrencyDataRequest currencyDataRequest) throws EonException {
        Specification<CurrencyEntity> specification = CurrencySpecification.filterCurrency(currencyDataRequest);
        Page<CurrencyEntity> currencyEntityPage = currencyRepository.findAll(specification, Helper.getPageable(currencyDataRequest));
        List<CurrencyEntity> currencyEntityList = currencyEntityPage.getContent();
        List<CurrencyResponse> currencyResponseList = currencyEntityList.stream().map(this::createCurrencyResponse).toList();
        return ServiceResponseBuilder.buildSuccessResponse("All currency listed: ", currencyResponseList);
    }

    private CurrencyResponse createCurrencyResponse(CurrencyEntity currencyEntity) {
        return CurrencyResponse.builder()
                .id(currencyEntity.getId())
                .name(currencyEntity.getName())
                .description(currencyEntity.getDescription())
                .build();
    }
}
