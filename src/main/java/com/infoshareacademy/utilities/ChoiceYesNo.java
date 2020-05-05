package com.infoshareacademy.utilities;

public enum ChoiceYesNo {
    YES(true),
    NO(false),
    Y(true),
    N(false);

    private final boolean value;

    ChoiceYesNo(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}