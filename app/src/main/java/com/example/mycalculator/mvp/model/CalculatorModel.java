package com.example.mycalculator.mvp.model;

import com.example.mycalculator.mvp.contract.CalculatorContract;

import static com.example.mycalculator.symbols.OperatorsSymbols.operatorDivide;
import static com.example.mycalculator.symbols.OperatorsSymbols.operatorMinus;
import static com.example.mycalculator.symbols.OperatorsSymbols.operatorMultiply;
import static com.example.mycalculator.symbols.OperatorsSymbols.operatorSum;

import static com.example.mycalculator.utils.StringUtils.EMPTY_STRING;

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

    public String getPartialResult() {

        return (firstOperand + operator + secondOperand);
    }

    @Override
    public String getFullResult() {

        switch (operator) {

            case operatorSum:
                result = String.valueOf((Double.parseDouble(firstOperand) + Double.parseDouble(secondOperand)));
                break;

            case operatorMinus:
                result = String.valueOf((Double.parseDouble(firstOperand) - Double.parseDouble(secondOperand)));
                break;

            case operatorDivide:
                result = String.valueOf((Double.parseDouble(firstOperand) / Double.parseDouble(secondOperand)));
                break;

            case operatorMultiply:
                result = String.valueOf((Double.parseDouble(firstOperand) * Double.parseDouble(secondOperand)));
                break;

            default:
                result = "Error, unknow Symbol used";
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
    public void saveOperationSymbol(String toString) {
        operator = toString;
    }
}
