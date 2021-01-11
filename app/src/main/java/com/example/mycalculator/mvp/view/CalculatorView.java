package com.example.mycalculator.mvp.view;

import com.example.mycalculator.MainActivity;
import com.example.mycalculator.R;
import com.example.mycalculator.databinding.ActivityMainBinding;
import com.example.mycalculator.mvp.contract.CalculatorContract;
import com.example.mycalculator.utils.ErrorUtils;

public class CalculatorView extends ActivityView implements CalculatorContract.CalculatorViewContract {

    private ActivityMainBinding binding;

    public CalculatorView(MainActivity mainActivity, ActivityMainBinding binding) {
        super(mainActivity);
        this.binding = binding;
    }

    @Override
    public void drawNumber(String number) {
        binding.screenTextExample.setText(number);
    }

    @Override
    public void showErrorDivision() {
        binding.screenTextExample.setText(getContext().getString(R.string.error_message_in_division));
    }

    @Override
    public void showErrorInvalidOperation() {
        binding.screenTextExample.setText(getContext().getString(R.string.error_message_when_invalid_format));
    }

    @Override
    public void showErrorMessage() {
        binding.screenTextExample.setText(getContext().getString((R.string.error_generic_message)));
    }
}
