package com.example.mycalculator.mvp.view;

import com.example.mycalculator.MainActivity;
import com.example.mycalculator.databinding.ActivityMainBinding;
import com.example.mycalculator.mvp.contract.CalculatorContract;

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

}
