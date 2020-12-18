package com.example.mycalculator.mvp;

public class CalculatorModelImpl implements CalculatorContract.CalculatorModelContract {

    private String firstOperator;
    private String secondOperator;
    private String operation;

    @Override
    public void saveNumber(String number) {
        firstOperator = number;
    }

    public String getResult() {
        return firstOperator;
    }
}
