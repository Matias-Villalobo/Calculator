package com.example.mycalculator.mvp.contract;

public interface CalculatorContract {

    public interface CalculatorPresenterContract {
        public void numberPressed(String number);

        public void operationSymbolPressed(String operatorUsed);

        public void operatorResultPressed();

        public void erase();

    }

    public interface CalculatorModelContract {
        public void saveNumber(String number);

        String getPartialResult();

        String getFullResult();

        String eraseResult();

        void saveOperationSymbol(String operatorSymbol);
    }

    public interface CalculatorViewContract {
        public void drawNumber(String number);
    }

}
