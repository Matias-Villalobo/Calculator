package com.example.mycalculator.mvp.contract;

public interface CalculatorContract {

    public interface CalculatorPresenterContract {
        public void numberPressed(String number);
    }

    public interface CalculatorModelContract {
        public void saveNumber(String number);

        String getResult();
    }

    public interface CalculatorViewContract {
        public void drawNumber(String number);
    }
}
