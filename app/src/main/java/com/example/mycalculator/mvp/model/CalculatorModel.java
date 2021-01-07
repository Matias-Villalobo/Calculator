package com.example.mycalculator.mvp.model;

import com.example.mycalculator.mvp.contract.CalculatorContract;

import java.util.function.Function;

import static com.example.mycalculator.utils.StringUtils.ERROR_MESSAGE_DIVISION;
import static com.example.mycalculator.utils.StringUtils.ERROR_MESSAGE_INVALID_FORMAT;
import static com.example.mycalculator.utils.StringUtils.OPERATOR_DIVIDE;
import static com.example.mycalculator.utils.StringUtils.OPERATOR_MINUS;
import static com.example.mycalculator.utils.StringUtils.OPERATOR_MULTIPLY;
import static com.example.mycalculator.utils.StringUtils.OPERATOR_SUM;
import static com.example.mycalculator.utils.StringUtils.EMPTY_STRING;
import static com.example.mycalculator.utils.StringUtils.ERROR_MESSAGE;
import static com.example.mycalculator.utils.StringUtils.TEXT_ZERO_NUMBER;

public class CalculatorModel implements CalculatorContract.CalculatorModelContract {

    private Operand firstOperand = new Operand();
    private Operand secondOperand = new Operand();
    private String operator = EMPTY_STRING;
    private String result = EMPTY_STRING;

    private class Operand {
        private String sign = "";
        private String value = "";

        private String getSign() {
            return sign;
        }

        public void eraseOperands() {
            sign = "";
            value = "";
        }

        public Double getValue() {
            if (value == "") {
                return 0.0;
            } else {
                return Double.parseDouble(sign+value);
            }
        }

        private void setSign(String sign) {
            this.sign = sign;
        }

        private void setValue(String value) {
            this.value = value;
        }

        public Boolean isEmpty() {
            return value.isEmpty();
        }

        public String getPartialResult() {
            return sign + firstOperand + operator + secondOperand;
        }

        public String getFullResult() {
            return null;
        }

        public void addNumber(String number) {
            value += number;
        }

    }


    @Override
    public void saveNumber(String number) {
        if (operator.isEmpty()) {
            firstOperand.addNumber(number);
        } else {
            secondOperand.addNumber(number);
        }
    }

    @Override
    public String getPartialResult() {
        return String.valueOf(firstOperand.getValue()) + operator + String.valueOf(secondOperand.getValue());
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
            result = ERROR_MESSAGE_INVALID_FORMAT;
            return false;
        }
        return true;
    }


    @Override
    public String getFullResult() {

        if (isValidOperation()) {

            switch (operator) {

                case OPERATOR_SUM:
                    result = String.valueOf(firstOperand.getValue() + secondOperand.getValue());
                    break;

                case OPERATOR_MINUS:
                    result = String.valueOf(firstOperand.getValue() - secondOperand.getValue());
                    break;

                case OPERATOR_DIVIDE:
                    if (firstOperand.getValue() == secondOperand.getValue()) {
                        result = ERROR_MESSAGE_DIVISION;
                    } else {
                        result = String.valueOf(firstOperand.getValue() / secondOperand.getValue());
                    }
                    break;

                case OPERATOR_MULTIPLY:
                    result = String.valueOf(firstOperand.getValue() * secondOperand.getValue());
                    break;

                default:
                    result = ERROR_MESSAGE;
            }
            if (result.substring(0).equals("-")) {
                firstOperand.sign = "-";
                firstOperand.value = result.substring(1, result.length());
            } else {
                firstOperand.sign = "";
                firstOperand.value = result;
            }

            secondOperand.eraseOperands();
        }

        return result;
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
            firstOperand.setSign("-");
        } else if (operator.isEmpty()) {
            operator = operatorSymbol;
        } else {
            secondOperand.setSign("-");
        }
    }
}
