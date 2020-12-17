package com.example.mycalculator.mvp;

import android.widget.Button;
import android.widget.TextView;

import com.example.mycalculator.MainActivity;
import com.example.mycalculator.databinding.ActivityMainBinding;

public class CalculatorViewImpl implements CalculatorContract.calculatorView {

    private ActivityMainBinding binding;

    public CalculatorViewImpl(MainActivity mainActivity, ActivityMainBinding binding) {
        this.binding = binding;

    }

    @Override
    public void drawNumber(String number) {
        binding.screenTextExample.setText(number);
    }
}
