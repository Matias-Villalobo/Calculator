package com.example.mycalculator.mvp;

public class CalculatorModel implements CalculatorContract.CalculatorModelContract {

    private String firstOperand;

    @Override
    public void saveNumber(String number) {
        firstOperand = number;
    }

    public String getResult() {
        return firstOperand;
    }
}
