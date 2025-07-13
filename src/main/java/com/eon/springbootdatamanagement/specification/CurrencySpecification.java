package com.eon.springbootdatamanagement.specification;

import com.eon.springbootdatamanagement.entity.CurrencyEntity;
import com.eon.springbootdatamanagement.payload.request.CurrencyDataRequest;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

public class CurrencySpecification {

    private CurrencySpecification() {
    }

    public static Specification<CurrencyEntity> filterCurrency(CurrencyDataRequest currencyDataRequest) {
        return ((root, query, criteriaBuilder) -> {
            Predicate finalPredicate = criteriaBuilder.conjunction();

            if (StringUtils.isNotBlank(currencyDataRequest.getSearchText())) {
                String searchText = likePattern(currencyDataRequest.getSearchText());
                Predicate searchTextPredicate = criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), searchText),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), searchText)
                );
                finalPredicate = criteriaBuilder.and(finalPredicate, searchTextPredicate);
            }

            //Using currency name filter
            if (StringUtils.isNotBlank(currencyDataRequest.getCurrency())) {
                Predicate currencyNamePredicate = criteriaBuilder.equal(root.get("name"), currencyDataRequest.getCurrency());
                finalPredicate = criteriaBuilder.and(finalPredicate, currencyNamePredicate);
            }
            return finalPredicate;

        });
    }

    private static String likePattern(String value) {
        return "%" + value + "%";
    }
}
