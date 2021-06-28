package com.assylias.jbloomberg.mock;

public enum DateTimeTypeEnum {
    neither(0), date(1), time(2), both(3);

    private final int index;

    DateTimeTypeEnum(int index) {
        this.index = index;
    }

    public int index() {
        return index;
    }
}
