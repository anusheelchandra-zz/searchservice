package com.expertsearch.entity;

import lombok.Getter;

/* Have used int to order in a way ONLINE comes first for users, followed by BUSY & OFFLINE */
public enum Status {
    ONLINE(0),
    BUSY(1),
    OFFLINE(2);

    @Getter
    private Integer value;

    Status(Integer i) {
        this.value = i;
    }
}
