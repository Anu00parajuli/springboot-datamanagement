package com.eon.springbootdatamanagement.util;

import com.eon.springbootdatamanagement.payload.request.PaginationRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class Helper {
    private Helper() {}

    public static Pageable getPageable(PaginationRequest paginationRequest) {
        return PageRequest.of(paginationRequest.getPageNo(), paginationRequest.getPageSize(),
                Sort.by(Objects.equals(paginationRequest.getDirection(), Constant.DEFAULT_PAGINATION_DIRECTION )? Sort.Direction.ASC : Sort.Direction.DESC,
                        paginationRequest.getSortBy() == null ? Constant.DEFAULT_SORT_BY : paginationRequest.getSortBy()));
    }
}
