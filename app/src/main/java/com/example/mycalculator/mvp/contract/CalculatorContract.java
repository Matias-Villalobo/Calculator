package com.example.mycalculator.mvp.contract;

import com.example.mycalculator.utils.ErrorUtils;

public interface CalculatorContract {

    public interface CalculatorPresenterContract {
        public void numberPressed(String number);

        public void operationSymbolPressed(String operatorUsed);

        public void operatorResultPressed();

        public void erase();

    }

    public interface CalculatorModelContract {
        public void saveNumber(String number);

        public String getPartialResult();

        public void doOperations();

        public String getResult();

        public String eraseResult();

        public void saveOperationSymbol(String operatorSymbol);

        public ErrorUtils getError();
    }

    public interface CalculatorViewContract {
        public void drawNumber(String number);

        public void showErrorDivision(String errorMessage);

        public void showErrorInvalidOperation(String errorMessage);

        public void showErrorMessage(String errorMessage);
    }
}
