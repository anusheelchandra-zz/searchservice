package com.expertsearch.model;

import org.springframework.data.domain.Sort;

public class Constants {

    public static final String EMPTY_STRING = "";

    public static final String CREATE_PATH = "/expert";
    public static final String FIND_ALL_PATH = "/expert/findAll";
    public static final String FIND_BY_NAME_PATH = "/expert/name";
    public static final String FIND_BY_DESC_PATH = "/expert/description";
    public static final String SEARCH_BY_OPTIONS_PATH = "/search";

    public static final String SORT_BY = "sortBy";
    public static final String SORT_BY_DEFAULT = "STATUS";
    public static final String DIRECTION_ASC = "asc";

    static final Sort SORT_ORDER_BY_STATUS = Sort.by(Sort.Order.by("availability.status"));
    static final Sort SORT_ORDER_BY_PRICE = Sort.by(Sort.Order.by("availability.pricePerMinute"));
    public static final Sort SORT_ORDER_BY_BOTH = Sort.by(Sort.Order.by("availability.status"),
                                                   Sort.Order.by("availability.pricePerMinute"));

    public static final String FIND_BY_DESCRIPTION_QUERY = "select e from Expert e where LOWER(e.description) like %?1%";

}
