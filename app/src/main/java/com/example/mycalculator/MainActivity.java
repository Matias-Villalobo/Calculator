package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.example.mycalculator.databinding.ActivityMainBinding;
import com.example.mycalculator.mvp.contract.CalculatorContract;
import com.example.mycalculator.mvp.model.CalculatorModel;
import com.example.mycalculator.mvp.presenter.CalculatorPresenter;
import com.example.mycalculator.mvp.view.CalculatorView;
import com.example.mycalculator.utils.StringUtils;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CalculatorContract.CalculatorPresenterContract presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new CalculatorPresenter(new CalculatorModel(), new CalculatorView(this, binding));

        setListeners();
    }

    private void setListeners() {
        binding.buttonNumberZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed(binding.buttonNumberZero.getText().toString());
            }
        });
        binding.buttonNumberOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed(binding.buttonNumberOne.getText().toString());
            }
        });
        binding.buttonNumberTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed(binding.buttonNumberTwo.getText().toString());
            }
        });
        binding.buttonNumberThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed(binding.buttonNumberThree.getText().toString());
            }
        });
        binding.buttonNumberFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed(binding.buttonNumberFour.getText().toString());
            }
        });
        binding.buttonNumberFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed(binding.buttonNumberFive.getText().toString());
            }
        });
        binding.buttonNumberSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed(binding.buttonNumberSix.getText().toString());
            }
        });
        binding.buttonNumberSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed(binding.buttonNumberSeven.getText().toString());
            }
        });
        binding.buttonNumberEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed(binding.buttonNumberEight.getText().toString());
            }
        });
        binding.buttonNumberNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed(binding.buttonNumberNine.getText().toString());
            }
        });
        binding.buttonFunctionErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed(StringUtils.EMPTY_STRING);
            }
        });
    }
}
