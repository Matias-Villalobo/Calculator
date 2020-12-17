package com.example.mycalculator.mvp;

public interface CalculatorContract {

    public interface calculatorPresenter {

        public void numberPressed(String number);
    }

    public interface calculatorModel {
        public void saveNumber(String number);
    }

    public interface calculatorView {
        public void drawNumber(String number);
    }

}
