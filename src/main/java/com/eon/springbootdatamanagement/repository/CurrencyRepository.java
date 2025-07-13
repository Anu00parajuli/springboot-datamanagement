package com.eon.springbootdatamanagement.repository;

import com.eon.springbootdatamanagement.entity.CurrencyEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends BaseRepository<CurrencyEntity> {
    boolean existsCurrencyEntityByName(String name);

    CurrencyEntity findCurrencyEntityById(Long id);
}
