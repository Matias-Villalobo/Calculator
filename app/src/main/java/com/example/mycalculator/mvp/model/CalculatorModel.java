package com.example.mycalculator.mvp.model;

import com.example.mycalculator.mvp.contract.CalculatorContract;

import static com.example.mycalculator.utils.StringUtils.OPERATOR_DIVIDE;
import static com.example.mycalculator.utils.StringUtils.OPERATOR_MINUS;
import static com.example.mycalculator.utils.StringUtils.OPERATOR_MULTIPLY;
import static com.example.mycalculator.utils.StringUtils.OPERATOR_SUM;
import static com.example.mycalculator.utils.StringUtils.EMPTY_STRING;
import static com.example.mycalculator.utils.StringUtils.ERROR_MESSAGE;

public class CalculatorModel implements CalculatorContract.CalculatorModelContract {

    private String firstOperand = EMPTY_STRING;
    private String secondOperand = EMPTY_STRING;
    private String operator = EMPTY_STRING;
    private String result = EMPTY_STRING;


    @Override
    public void saveNumber(String number) {
        if (operator.isEmpty()) {
            firstOperand += number;
        } else {
            secondOperand += number;
        }
    }

    @Override
    public String getPartialResult() {

        return (firstOperand + operator + secondOperand);
    }

    @Override
    public String getFullResult() {

        switch (operator) {

            case OPERATOR_SUM:
                if (result == EMPTY_STRING) {
                    result = String.valueOf(Double.parseDouble(firstOperand) + Double.parseDouble(secondOperand));
                }

                break;

            case OPERATOR_MINUS:
                result = String.valueOf(Double.parseDouble(firstOperand) - Double.parseDouble(secondOperand));
                break;

            case OPERATOR_DIVIDE:
                result = String.valueOf(Double.parseDouble(firstOperand) / Double.parseDouble(secondOperand));
                break;

            case OPERATOR_MULTIPLY:
                result = String.valueOf(Double.parseDouble(firstOperand) * Double.parseDouble(secondOperand));
                break;

            default:
                result = ERROR_MESSAGE;
        }

        return result;
    }

    @Override
    public String eraseResult() {
        result = EMPTY_STRING;
        firstOperand = EMPTY_STRING;
        secondOperand = EMPTY_STRING;
        operator = EMPTY_STRING;
        return EMPTY_STRING;
    }

    @Override
    public void saveOperationSymbol(String operatorSymbol) {
        operator = operatorSymbol;
    }
}
