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

    public static OrderStatus codeOf(final int code) {
        for (OrderStatus orderStatus : values()) {
            if (orderStatus.getCode() == code) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("No such enum object for the code: " + code);
    }
}
