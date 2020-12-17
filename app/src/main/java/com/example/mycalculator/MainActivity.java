package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mycalculator.databinding.ActivityMainBinding;
import com.example.mycalculator.mvp.CalculatorModelImpl;
import com.example.mycalculator.mvp.CalculatorPresenterImpl;
import com.example.mycalculator.mvp.CalculatorViewImpl;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private CalculatorPresenterImpl presenter;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new CalculatorPresenterImpl(new CalculatorModelImpl(), new CalculatorViewImpl(this, binding));

        binding.buttonNumberZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed("0");
            }
        });
        binding.buttonNumberOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed("1");
            }
        });
        binding.buttonNumberTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed("2");
            }
        });
        binding.buttonNumberThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed("3");
            }
        });
        binding.buttonNumberFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed("4");
            }
        });
        binding.buttonNumberFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed("5");
            }
        });
        binding.buttonNumberSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed("6");
            }
        });
        binding.buttonNumberSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed("7");
            }
        });
        binding.buttonNumberEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed("8");
            }
        });
        binding.buttonNumberNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed("9");
            }
        });
        binding.buttonFunctionErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.numberPressed("");
            }
        });
    }
}
