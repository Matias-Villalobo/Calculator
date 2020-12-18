package com.example.mycalculator.mvp.presenter;

import com.example.mycalculator.mvp.contract.CalculatorContract;

public class CalculatorPresenter implements CalculatorContract.CalculatorPresenterContract {

    private CalculatorContract.CalculatorModelContract model;
    private CalculatorContract.CalculatorViewContract view;

    public CalculatorPresenter(CalculatorContract.CalculatorModelContract model, CalculatorContract.CalculatorViewContract view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void numberPressed(String number) {
        model.saveNumber(number);
        view.drawNumber(model.getResult());
    }
}