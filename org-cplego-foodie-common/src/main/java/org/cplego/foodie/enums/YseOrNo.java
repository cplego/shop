package org.cplego.foodie.enums;

public enum YseOrNo {

    Y(1,"是"),
    N(0,"否");
    public final Integer type;
    public final String value;

    YseOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

}
