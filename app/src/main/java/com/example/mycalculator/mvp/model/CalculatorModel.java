package com.example.mycalculator.mvp.model;

import com.example.mycalculator.mvp.contract.CalculatorContract;

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

    private boolean isValidOperation(){
        if (operator.isEmpty()) {
            if (firstOperand.isEmpty()){
                result = EMPTY_STRING;
            } else {
                result = String.valueOf(Double.parseDouble(firstOperand));
            }
            return false;
        }else if (secondOperand.isEmpty()){
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
                        result = String.valueOf(Double.parseDouble(firstOperand) + Double.parseDouble(secondOperand));
                        break;

                case OPERATOR_MINUS:
                    result = String.valueOf(Double.parseDouble(firstOperand) - Double.parseDouble(secondOperand));
                    break;

                case OPERATOR_DIVIDE:
                    if ((Double.parseDouble(secondOperand)) == Double.parseDouble(TEXT_ZERO_NUMBER)) {
                        result = ERROR_MESSAGE_DIVISION;
                    } else {
                        result = String.valueOf(Double.parseDouble(firstOperand) / Double.parseDouble(secondOperand));
                    }
                    break;

                case OPERATOR_MULTIPLY:
                    result = String.valueOf(Double.parseDouble(firstOperand) * Double.parseDouble(secondOperand));
                    break;

                default:
                    result = ERROR_MESSAGE;
            }
        }
            return result;
        }

        @Override
        public String eraseResult () {
            result = EMPTY_STRING;
            firstOperand = EMPTY_STRING;
            secondOperand = EMPTY_STRING;
            operator = EMPTY_STRING;
            return EMPTY_STRING;
        }

        @Override
        public void saveOperationSymbol (String operatorSymbol){
            operator = operatorSymbol;
        }
    }
