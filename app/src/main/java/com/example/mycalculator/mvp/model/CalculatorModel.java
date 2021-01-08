package com.example.mycalculator.mvp.model;

import com.example.mycalculator.mvp.Operand;
import com.example.mycalculator.mvp.contract.CalculatorContract;
import com.example.mycalculator.utils.ErrorUtils;


import static com.example.mycalculator.utils.NumbersUtils.POSITION_ZERO;
import static com.example.mycalculator.utils.NumbersUtils.ZERO_NUMBER_DOUBLE_TYPE;
import static com.example.mycalculator.utils.NumbersUtils.POSITION_ONE;
import static com.example.mycalculator.utils.StringUtils.OPERATOR_DIVIDE;
import static com.example.mycalculator.utils.StringUtils.OPERATOR_MINUS;
import static com.example.mycalculator.utils.StringUtils.OPERATOR_MULTIPLY;
import static com.example.mycalculator.utils.StringUtils.OPERATOR_SUM;
import static com.example.mycalculator.utils.StringUtils.EMPTY_STRING;


public class CalculatorModel implements CalculatorContract.CalculatorModelContract {

    private Operand firstOperand = new Operand();
    private Operand secondOperand = new Operand();
    private String operator = EMPTY_STRING;
    private String result = EMPTY_STRING;
    private ErrorUtils error;

    @Override
    public void saveNumber(String number) {
        if (operator.isEmpty()) {
            firstOperand.addNumber(number);
        } else {
            secondOperand.addNumber(number);
        }
    }

    public String getValue() {
        String firstValue = String.valueOf(firstOperand.getValue());
        String secondValue = String.valueOf(secondOperand.getValue());
        String operatorUsed = operator;

        return firstValue + operatorUsed + secondValue;
    }

    @Override
    public String getPartialResult() {
        return getValue();
    }

    private boolean isValidOperation() {
        if (operator.isEmpty()) {
            if (firstOperand.isEmpty()) {
                result = EMPTY_STRING;
            } else {
                result = String.valueOf(firstOperand.getValue());
            }
            return false;
        } else if (secondOperand.isEmpty()) {
            result = EMPTY_STRING;
            error = ErrorUtils.ERROR_MESSAGE_INVALID_FORMAT;
            return false;
        }
        return true;
    }

    @Override
    public void doOperations() {

        if (isValidOperation()) {

            switch (operator) {

                case OPERATOR_SUM:
                    result = String.valueOf(firstOperand.getValue() + secondOperand.getValue());
                    break;

                case OPERATOR_MINUS:
                    result = String.valueOf(firstOperand.getValue() - secondOperand.getValue());
                    break;

                case OPERATOR_DIVIDE:
                    if (secondOperand.getValue() == ZERO_NUMBER_DOUBLE_TYPE) {
                        result = EMPTY_STRING;
                        error = ErrorUtils.ERROR_MESSAGE_DIVISION;
                    } else {
                        result = String.valueOf(firstOperand.getValue() / secondOperand.getValue());
                    }
                    break;

                case OPERATOR_MULTIPLY:
                    result = String.valueOf(firstOperand.getValue() * secondOperand.getValue());
                    break;

                default:
                    result = EMPTY_STRING;
                    error = ErrorUtils.ERROR_MESSAGE;
                    break;
            }
            checkNumberSign();

            error = ErrorUtils.NONE;
            secondOperand.eraseOperands();
        }
    }

    public String getResult() {
        return result;
    }

    private void checkNumberSign() {
        if (result.substring(POSITION_ZERO).equals(OPERATOR_MINUS)) {
            firstOperand.sign = OPERATOR_MINUS;
            firstOperand.value = result.substring(POSITION_ONE, result.length());
        } else {
            firstOperand.sign = EMPTY_STRING;
            firstOperand.value = result;
        }
    }

    @Override
    public String eraseResult() {
        result = EMPTY_STRING;
        firstOperand.eraseOperands();
        secondOperand.eraseOperands();
        operator = EMPTY_STRING;
        return EMPTY_STRING;
    }

    @Override
    public void saveOperationSymbol(String operatorSymbol) {
        if (firstOperand.isEmpty()) {
            firstOperand.setSign(OPERATOR_MINUS);
        } else if (operator.isEmpty()) {
            operator = operatorSymbol;
        } else {
            secondOperand.setSign(OPERATOR_MINUS);
        }
    }

    @Override
    public ErrorUtils getError() {
        return error;
    }
}
