package com.example.mycalculator.mvp;

public class CalculatorModelImpl implements CalculatorContract.calculatorModel {

    public String getResult;
    private String operatorOne;
    private String operatorTwo;
    private String operation;

    @Override
    public void saveNumber(String number) {
        operatorOne = number;
    }

    public String getResult() {
        return operatorOne;
    }


}
