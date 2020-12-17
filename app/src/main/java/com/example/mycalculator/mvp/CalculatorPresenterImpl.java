package com.example.mycalculator.mvp;

public class CalculatorPresenterImpl implements CalculatorContract.calculatorPresenter {

    private CalculatorModelImpl model;
    private CalculatorViewImpl view;


    public CalculatorPresenterImpl(CalculatorModelImpl model, CalculatorViewImpl view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void numberPressed(String number) {

        model.saveNumber(number);
        view.drawNumber(model.getResult());
    }


}
