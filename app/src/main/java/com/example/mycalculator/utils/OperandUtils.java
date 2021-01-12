package com.example.mycalculator.utils;

import static com.example.mycalculator.utils.NumbersUtils.ZERO_NUMBER_DOUBLE_TYPE;
import static com.example.mycalculator.utils.StringUtils.EMPTY_STRING;

public class OperandUtils {

    public String sign = EMPTY_STRING;
    public String value = EMPTY_STRING;

    public void eraseOperands() {
        sign = EMPTY_STRING;
        value = EMPTY_STRING;
    }

    public Double getValue() {
        if (value == EMPTY_STRING) {
            return ZERO_NUMBER_DOUBLE_TYPE;
        } else {
            return Double.parseDouble(sign + value);
        }
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Boolean isEmpty() {
        return value.isEmpty();
    }

    public void addNumber(String number) {
        value += number;
    }
}

