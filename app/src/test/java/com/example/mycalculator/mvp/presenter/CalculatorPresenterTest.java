package com.example.mycalculator.mvp.presenter;

import com.example.mycalculator.mvp.model.CalculatorModel;
import com.example.mycalculator.mvp.view.CalculatorView;

import org.junit.Before;
import org.junit.Test;

import static com.example.mycalculator.utils.ErrorUtils.ERROR_MESSAGE_DIVISION;
import static com.example.mycalculator.utils.ErrorUtils.ERROR_MESSAGE_INVALID_FORMAT;
import static com.example.utils.StringUtilsTest.EMPTY_STRING;
import static com.example.utils.StringUtilsTest.NUMBER_FIVE;
import static com.example.utils.StringUtilsTest.NUMBER_FOUR;
import static com.example.utils.StringUtilsTest.NUMBER_THREE;
import static com.example.utils.StringUtilsTest.NUMBER_TWO;
import static com.example.utils.StringUtilsTest.NUMBER_ZERO;
import static com.example.utils.StringUtilsTest.OPERATOR_DIVIDE;
import static com.example.utils.StringUtilsTest.OPERATOR_MINUS;
import static com.example.utils.StringUtilsTest.OPERATOR_MULTIPLY;
import static com.example.utils.StringUtilsTest.OPERATOR_SUM;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CalculatorPresenterTest {

    private CalculatorPresenter presenter;
    private CalculatorModel model;
    private CalculatorView view;

    @Before
    public void setup() {
        model = new CalculatorModel();
        view = mock(CalculatorView.class);
        presenter = new CalculatorPresenter(model, view);
    }

    @Test
    public void eraseTest() {
        presenter.erase();
        assertEquals(EMPTY_STRING, model.getResult());
        verify(view).drawNumber(EMPTY_STRING);
    }

    @Test
    public void testOperationSymbolPressedPlus() {
        model.saveNumber(NUMBER_THREE);
        presenter.operationSymbolPressed(OPERATOR_SUM);
        verify(view).drawNumber(model.getPartialResult());
        assertEquals(NUMBER_THREE + OPERATOR_SUM, model.getPartialResult());
    }

    @Test
    public void testOperationSymbolPressedNegative() {
        model.saveNumber(NUMBER_THREE);
        presenter.operationSymbolPressed(OPERATOR_MINUS);
        verify(view).drawNumber(model.getPartialResult());
        assertEquals(NUMBER_THREE + OPERATOR_MINUS, model.getPartialResult());
    }

    @Test
    public void testOperationSymbolPressedDivide() {
        model.saveNumber(NUMBER_THREE);
        presenter.operationSymbolPressed(OPERATOR_DIVIDE);
        verify(view).drawNumber(model.getPartialResult());
        assertEquals(NUMBER_THREE + OPERATOR_DIVIDE, model.getPartialResult());
    }

    @Test
    public void testOperationSymbolPressedWithMultiply() {
        model.saveNumber(NUMBER_THREE);
        presenter.operationSymbolPressed(OPERATOR_MULTIPLY);
        verify(view).drawNumber(model.getPartialResult());
        assertEquals(NUMBER_THREE + OPERATOR_MULTIPLY, model.getPartialResult());
    }

    @Test
    public void testDivideOperation() {
        model.saveNumber(NUMBER_FOUR);
        model.saveOperationSymbol(OPERATOR_DIVIDE);
        presenter.numberPressed(NUMBER_TWO);
        verify(view).drawNumber(model.getPartialResult());
        assertEquals(NUMBER_FOUR + OPERATOR_DIVIDE + NUMBER_TWO, model.getPartialResult());
    }

    @Test
    public void testOperationMultiply() {
        model.saveNumber(NUMBER_TWO);
        model.saveOperationSymbol(OPERATOR_MULTIPLY);
        presenter.numberPressed(NUMBER_TWO);
        verify(view).drawNumber(model.getPartialResult());
        assertEquals(NUMBER_TWO + OPERATOR_MULTIPLY + NUMBER_TWO, model.getPartialResult());
    }

    @Test
    public void testNumberPressedFirstOperand() {
        presenter.numberPressed(NUMBER_THREE);
        verify(view).drawNumber(model.getPartialResult());
        assertEquals(NUMBER_THREE, model.getPartialResult());
    }

    @Test
    public void testNumberPressedSecondOperand() {
        model.saveNumber(NUMBER_THREE);
        model.saveOperationSymbol(OPERATOR_SUM);
        presenter.numberPressed(NUMBER_THREE);
        verify(view).drawNumber(model.getPartialResult());
        assertEquals(NUMBER_THREE + OPERATOR_SUM + NUMBER_THREE, model.getPartialResult());
    }

    @Test
    public void testNumberPressedSecondOperandNegative() {
        model.saveNumber(NUMBER_THREE);
        model.saveOperationSymbol(OPERATOR_SUM);
        presenter.numberPressed(OPERATOR_MINUS + NUMBER_THREE);
        verify(view).drawNumber(model.getPartialResult());
        assertEquals(NUMBER_THREE + OPERATOR_SUM + OPERATOR_MINUS + NUMBER_THREE, model.getPartialResult());
    }

    @Test
    public void testNumberPressedFirstOperandNegative() {
        model.saveOperationSymbol(OPERATOR_MINUS);
        model.saveNumber(NUMBER_THREE);
        model.saveOperationSymbol(OPERATOR_SUM);
        presenter.numberPressed(NUMBER_THREE);
        verify(view).drawNumber(model.getPartialResult());
        assertEquals(OPERATOR_MINUS + NUMBER_THREE + OPERATOR_SUM + NUMBER_THREE, model.getPartialResult());
    }

    @Test
    public void testNumberPressedBothOperandsNegative() {
        model.saveOperationSymbol(OPERATOR_MINUS);
        model.saveNumber(NUMBER_THREE);
        model.saveOperationSymbol(OPERATOR_SUM);
        model.saveOperationSymbol(OPERATOR_MINUS);
        presenter.numberPressed(NUMBER_THREE);
        verify(view).drawNumber(model.getPartialResult());
        assertEquals(OPERATOR_MINUS + NUMBER_THREE + OPERATOR_SUM + OPERATOR_MINUS + NUMBER_THREE, model.getPartialResult());
    }

    @Test
    public void testOperatorResultPressedErrorNoneSum() {
        model.saveNumber(NUMBER_TWO);
        model.saveOperationSymbol(OPERATOR_SUM);
        model.saveNumber(NUMBER_THREE);
        presenter.operatorResultPressed();
        verify(view).drawNumber(model.getResult());
        assertEquals(NUMBER_FIVE, model.getResult());
    }

    @Test
    public void testOperatorResultPressedErrorNoneSumWithNegativeSecond() {
        model.saveNumber(NUMBER_FOUR);
        model.saveOperationSymbol(OPERATOR_SUM);
        model.saveOperationSymbol(OPERATOR_MINUS);
        model.saveNumber(NUMBER_TWO);
        presenter.operatorResultPressed();
        verify(view).drawNumber(model.getResult());
        assertEquals(NUMBER_TWO, model.getResult());
    }

    @Test
    public void testOperatorResultPressedErrorNoneSumWithNegativeFirst() {
        model.saveOperationSymbol(OPERATOR_MINUS);
        model.saveNumber(NUMBER_FOUR);
        model.saveOperationSymbol(OPERATOR_SUM);
        model.saveNumber(NUMBER_TWO);
        presenter.operatorResultPressed();
        verify(view).drawNumber(model.getResult());
        assertEquals(OPERATOR_MINUS + NUMBER_TWO, model.getResult());
    }

    @Test
    public void testOperatorResultPressedErrorNoneSumWithNegativeBoth() {
        model.saveOperationSymbol(OPERATOR_MINUS);
        model.saveNumber(NUMBER_TWO);
        model.saveOperationSymbol(OPERATOR_MINUS);
        model.saveNumber(NUMBER_TWO);
        presenter.operatorResultPressed();
        verify(view).drawNumber(model.getResult());
        assertEquals(OPERATOR_MINUS + NUMBER_FOUR, model.getResult());
    }

    @Test
    public void testOperatorResultPressedErrorNoneDivideWithNegativeBoth() {
        model.saveOperationSymbol(OPERATOR_MINUS);
        model.saveNumber(NUMBER_FOUR);
        model.saveOperationSymbol(OPERATOR_DIVIDE);
        model.saveOperationSymbol(OPERATOR_MINUS);
        model.saveNumber(NUMBER_TWO);
        presenter.operatorResultPressed();
        verify(view).drawNumber(model.getResult());
        assertEquals(NUMBER_TWO, model.getResult());
    }

    @Test
    public void testOperatorResultPressedErrorNoneDivideWithNegativeFirst() {
        model.saveOperationSymbol(OPERATOR_MINUS);
        model.saveNumber(NUMBER_FOUR);
        model.saveOperationSymbol(OPERATOR_DIVIDE);
        model.saveNumber(NUMBER_TWO);
        presenter.operatorResultPressed();
        verify(view).drawNumber(model.getResult());
        assertEquals(OPERATOR_MINUS + NUMBER_TWO, model.getResult());
    }

    @Test
    public void testOperatorResultPressedErrorNoneDivideWithNegativeSecond() {
        model.saveNumber(NUMBER_FOUR);
        model.saveOperationSymbol(OPERATOR_DIVIDE);
        model.saveOperationSymbol(OPERATOR_MINUS);
        model.saveNumber(NUMBER_TWO);
        presenter.operatorResultPressed();
        verify(view).drawNumber(model.getResult());
        assertEquals(OPERATOR_MINUS + NUMBER_TWO, model.getResult());
    }

    @Test
    public void testOperatorResultPressedErrorNoneMultiplyWithNegativeSecond() {
        model.saveNumber(NUMBER_TWO);
        model.saveOperationSymbol(OPERATOR_MULTIPLY);
        model.saveOperationSymbol(OPERATOR_MINUS);
        model.saveNumber(NUMBER_TWO);
        presenter.operatorResultPressed();
        verify(view).drawNumber(model.getResult());
        assertEquals(OPERATOR_MINUS + NUMBER_FOUR, model.getResult());
    }

    @Test
    public void testOperatorResultPressedErrorNoneMultiplyWithNegativeFirst() {
        model.saveOperationSymbol(OPERATOR_MINUS);
        model.saveNumber(NUMBER_TWO);
        model.saveOperationSymbol(OPERATOR_MULTIPLY);
        model.saveNumber(NUMBER_TWO);
        presenter.operatorResultPressed();
        verify(view).drawNumber(model.getResult());
        assertEquals(OPERATOR_MINUS + NUMBER_FOUR, model.getResult());
    }

    @Test
    public void testOperatorResultPressedErrorNoneMultiplyBothNegatives() {
        model.saveOperationSymbol(OPERATOR_MINUS);
        model.saveNumber(NUMBER_TWO);
        model.saveOperationSymbol(OPERATOR_MULTIPLY);
        model.saveOperationSymbol(OPERATOR_MINUS);
        model.saveNumber(NUMBER_TWO);
        presenter.operatorResultPressed();
        verify(view).drawNumber(model.getResult());
        assertEquals(NUMBER_FOUR, model.getResult());
    }

    @Test
    public void testOperatorResultPressedErrorNoneMinus() {
        model.saveNumber(NUMBER_FOUR);
        model.saveOperationSymbol(OPERATOR_MINUS);
        model.saveNumber(NUMBER_TWO);
        presenter.operatorResultPressed();
        verify(view).drawNumber(model.getResult());
        assertEquals(NUMBER_TWO, model.getResult());
    }

    @Test
    public void testOperatorResultPressedErrorNoneDivide() {
        model.saveNumber(NUMBER_FOUR);
        model.saveOperationSymbol(OPERATOR_DIVIDE);
        model.saveNumber(NUMBER_TWO);
        presenter.operatorResultPressed();
        verify(view).drawNumber(model.getResult());
        assertEquals(NUMBER_TWO, model.getResult());
    }

    @Test //REVISAR OPERACION DE MULTIPLICACION
    public void testOperatorResultPressedErrorNoneMultiply() {
        model.saveNumber(NUMBER_TWO);
        model.saveOperationSymbol(OPERATOR_MULTIPLY);
        model.saveNumber(NUMBER_TWO);
        presenter.operatorResultPressed();
        //verify(view).drawNumber(model.getResult());
        assertEquals(NUMBER_FOUR, model.getResult());
    }

    @Test
    public void testOperatorResultPressedErrorMessageDivision() {
        model.saveNumber(NUMBER_TWO);
        model.saveOperationSymbol(OPERATOR_DIVIDE);
        model.saveNumber(NUMBER_ZERO);
        presenter.operatorResultPressed();
        verify(view).showErrorDivision();
        assertEquals(ERROR_MESSAGE_DIVISION, model.getError());
    }

    @Test
    public void testOperatorResultPressedErrorMessageInvalidFormat() {
        model.saveNumber(NUMBER_TWO);
        model.saveOperationSymbol(OPERATOR_SUM);
        presenter.operatorResultPressed();
        verify(view).showErrorInvalidOperation();
        assertEquals(NUMBER_TWO + OPERATOR_SUM, model.getValue());
        assertEquals(ERROR_MESSAGE_INVALID_FORMAT, model.getError());
    }
}
