package com.example.mycalculator.mvp;

import com.example.mycalculator.MainActivity;
import com.example.mycalculator.databinding.ActivityMainBinding;

public class CalculatorViewImpl extends ActivityView implements CalculatorContract.CalculatorViewContract {

    private ActivityMainBinding binding;

    public CalculatorViewImpl(MainActivity mainActivity, ActivityMainBinding binding) {
        super(mainActivity);
        this.binding = binding;

    }
    @Override
    public void drawNumber(String number) {
        binding.screenTextExample.setText(number);
    }
}
