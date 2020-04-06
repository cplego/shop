package org.cplego.foodie.enums;

public enum IsDefAddress {

    NOT(0,"非默认地址"),
    IS_DEF_ADDRESS(1,"默认地址");

    public final Integer type;
    public final String value;

    IsDefAddress(Integer type, String value) {
        this.type = type;
        this.value = value;
    }



}
