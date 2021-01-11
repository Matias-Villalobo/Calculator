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
        view.drawNumber(model.getPartialResult());
    }

    @Override
    public void operationSymbolPressed(String operatorUsed) {
        model.saveOperationSymbol(operatorUsed);
        view.drawNumber(model.getPartialResult());
    }

    @Override
    public void operatorResultPressed() {
        model.doOperations();
        switch (model.getError()) {
            case NONE: {
                view.drawNumber(model.getResult());
                break;
            }
            case ERROR_MESSAGE: {
                view.showErrorMessage();
                break;
            }
            case ERROR_MESSAGE_DIVISION: {
                view.showErrorDivision();
                break;
            }
            case ERROR_MESSAGE_INVALID_FORMAT: {
                view.showErrorInvalidOperation();
                break;
            }
        }
    }

    @Override
    public void erase() {
        view.drawNumber(model.eraseResult());
    }

    @Override
    public void showErrorMessage() {

    }

}
