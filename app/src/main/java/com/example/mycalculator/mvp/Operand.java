package com.example.mycalculator.mvp;

import static com.example.mycalculator.utils.StringUtils.EMPTY_STRING;

public class Operand {

    public String sign = EMPTY_STRING;
    public String value = EMPTY_STRING;

    private String getSign() {
        return sign;
    }

    public void eraseOperands() {
        sign = "";
        value = "";
    }

    public Double getValue() {
        if (value == EMPTY_STRING) {
            return 0.0;
        } else {
            return Double.parseDouble(sign+value);
        }
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean isEmpty() {
        return value.isEmpty();
    }

    public String getFullResult() {
        return null;
    }

    public void addNumber(String number) {
        value += number;
    }
}

