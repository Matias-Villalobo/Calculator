package com.example.mycalculator.mvp.model;

import com.example.mycalculator.mvp.contract.CalculatorContract;

public class CalculatorModel implements CalculatorContract.CalculatorModelContract {

    private String firstOperand = "";
    private String secondOperand = "";
    private String operator = "";
    private String result = "";


    @Override
    public void saveNumber(String number) {
        if (operator.isEmpty()) {
            firstOperand = firstOperand + number;
        } else {
            secondOperand = secondOperand + number;
        }
    }

    public String getPartialResult() {

        return (firstOperand + operator + secondOperand);
    }

    @Override
    public String getFullResult() {

        switch (operator) {

            case "+":
                result = String.valueOf((Double.parseDouble(firstOperand) + Double.parseDouble(secondOperand)));
                break;

            case "-":
                result = String.valueOf((Double.parseDouble(firstOperand) - Double.parseDouble(secondOperand)));
                break;

            case "/":
                result = String.valueOf((Double.parseDouble(firstOperand) / Double.parseDouble(secondOperand)));
                break;

            case "X":
                result = String.valueOf((Double.parseDouble(firstOperand) * Double.parseDouble(secondOperand)));
                break;

            default:
                throw new IllegalStateException("Error");
        }

        return result;
    }

    @Override
    public String eraseResult() {
        result = "";
        firstOperand = "";
        secondOperand = "";
        operator = "";
        return "";
    }

    @Override
    public void saveOperator(String toString) {
        operator = toString;
    }
}
