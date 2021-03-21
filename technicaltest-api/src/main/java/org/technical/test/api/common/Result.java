package org.technical.test.api.common;

public enum Result {

    CA ("CREATION ACCOUNT"),
    TD("TRANSFER DONE"),
    NG ("NEGATIVE BALANCE"),
    TB ("TRANSFER BLOCK"),
    ADE ("ACCOUNT DOESN'T EXIST"),
    BA ("BALANCE ADDED"),
    IC ("INVALID CURRENCY");

    private final String value;

    private Result(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
