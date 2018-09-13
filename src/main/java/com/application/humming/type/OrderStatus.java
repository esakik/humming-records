package com.application.humming.type;

import lombok.Getter;

public enum OrderStatus {

    UNDETERMINED(0, "注文未確定"),
    DETERMINED(1, "注文確定");

    @Getter
    private final int code;
    @Getter
    private final String label;

    private OrderStatus(final int code, final String label) {
        this.code = code;
        this.label = label;
    }
}
