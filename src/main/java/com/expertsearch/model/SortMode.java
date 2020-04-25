package com.expertsearch.model;

import lombok.Getter;
import org.springframework.data.domain.Sort;

public enum SortMode {
    STATUS (Constants.SORT_ORDER_BY_STATUS),
    PRICE (Constants.SORT_ORDER_BY_PRICE),
    BOTH(Constants.SORT_ORDER_BY_BOTH);

    @Getter
    private Sort sortBy;

    SortMode(Sort sortBy) {
        this.sortBy = sortBy;
    }
}
