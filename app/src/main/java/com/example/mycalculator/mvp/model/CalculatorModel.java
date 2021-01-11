package com.example.mycalculator.mvp.model;

import com.example.mycalculator.utils.OperandUtils;
import com.example.mycalculator.mvp.contract.CalculatorContract;
import com.example.mycalculator.utils.ErrorUtils;


import static com.example.mycalculator.utils.NumbersUtils.NUMBER_ZERO;
import static com.example.mycalculator.utils.NumbersUtils.POSITION_ZERO;
import static com.example.mycalculator.utils.NumbersUtils.ZERO_NUMBER_DOUBLE_TYPE;
import static com.example.mycalculator.utils.NumbersUtils.POSITION_ONE;
import static com.example.mycalculator.utils.StringUtils.OPERATOR_DIVIDE;
import static com.example.mycalculator.utils.StringUtils.OPERATOR_MINUS;
import static com.example.mycalculator.utils.StringUtils.OPERATOR_MULTIPLY;
import static com.example.mycalculator.utils.StringUtils.OPERATOR_SUM;
import static com.example.mycalculator.utils.StringUtils.EMPTY_STRING;


public class CalculatorModel implements CalculatorContract.CalculatorModelContract {

    private OperandUtils firstOperandUtils = new OperandUtils();
    private OperandUtils secondOperandUtils = new OperandUtils();
    private String operator = EMPTY_STRING;
    private String result = EMPTY_STRING;
    private ErrorUtils error = ErrorUtils.NONE;

    @Override
    public void saveNumber(String number) {
        if (operator.isEmpty()) {
            firstOperandUtils.addNumber(number);
        } else {
            secondOperandUtils.addNumber(number);
        }
    }

    public String getValue() {
        String firstValue = String.valueOf(firstOperandUtils.getValue());
        String secondValue = String.valueOf(secondOperandUtils.getValue());

        if (secondOperandUtils.isEmpty()) {
            secondValue = EMPTY_STRING;
        }
        return firstValue + operator + secondValue;
    }

    @Override
    public String getPartialResult() {
        return getValue();
    }

    private boolean isValidOperation() {
        if (operator.isEmpty()) {
            if (firstOperandUtils.isEmpty()) {
                result = EMPTY_STRING;
            } else {
                result = String.valueOf(firstOperandUtils.getValue());
            }
            return false;
        } else if (secondOperandUtils.isEmpty()) {
            error = ErrorUtils.ERROR_MESSAGE_INVALID_FORMAT;
            result = EMPTY_STRING;
            return false;
        }
        return true;
    }

    @Override
    public void doOperations() {

        if (isValidOperation()) {

            switch (operator) {

                case OPERATOR_SUM:
                    result = String.valueOf(firstOperandUtils.getValue() + secondOperandUtils.getValue());
                    error = ErrorUtils.NONE;
                    break;

                case OPERATOR_MINUS:
                    result = String.valueOf(firstOperandUtils.getValue() - secondOperandUtils.getValue());
                    error = ErrorUtils.NONE;
                    break;

                case OPERATOR_DIVIDE:
                    if (secondOperandUtils.getValue() == NUMBER_ZERO) {
                        result = EMPTY_STRING;
                        error = ErrorUtils.ERROR_MESSAGE_DIVISION;
                    } else {
                        result = String.valueOf(firstOperandUtils.getValue() / secondOperandUtils.getValue());
                        error = ErrorUtils.NONE;
                    }
                    break;

                case OPERATOR_MULTIPLY:
                    result = String.valueOf(firstOperandUtils.getValue() * secondOperandUtils.getValue());
                    error = ErrorUtils.NONE;
                    break;

                default:
                    error = ErrorUtils.ERROR_MESSAGE;
                    result = EMPTY_STRING;
                    break;
            }
            updateFirstOperand();
            secondOperandUtils.eraseOperands();
        }
    }

    public String getResult() {
        return result;
    }

    private void updateFirstOperand() {
        if (result.substring(POSITION_ZERO).equals(OPERATOR_MINUS)) {
            firstOperandUtils.sign = OPERATOR_MINUS;
            firstOperandUtils.value = result.substring(POSITION_ONE, result.length());
        } else {
            firstOperandUtils.sign = EMPTY_STRING;
            firstOperandUtils.value = result;
        }
        operator = EMPTY_STRING;
    }

    @Override
    public String eraseResult() {
        result = EMPTY_STRING;
        firstOperandUtils.eraseOperands();
        secondOperandUtils.eraseOperands();
        operator = EMPTY_STRING;
        error = ErrorUtils.NONE;
        return EMPTY_STRING;
    }

    @Override
    public void saveOperationSymbol(String operatorSymbol) {
        if (firstOperandUtils.isEmpty()) {
            firstOperandUtils.setSign(OPERATOR_MINUS);
        } else if (operator.isEmpty()) {
            operator = operatorSymbol;
        } else {
            secondOperandUtils.setSign(OPERATOR_MINUS);
        }
    }

    @Override
    public ErrorUtils getError() {
        return error;
    }
}
