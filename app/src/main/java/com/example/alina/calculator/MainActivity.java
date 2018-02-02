package com.example.alina.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;
    private Button zeroButton;
    private Button equalsButton;
    private Button plusButton;
    private Button minusButton;
    private Button multiplyButton;
    private Button divideButton;
    private Button clearAllText;
    private Controller controller;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUIElements();
        controller = new Controller(textView);
        setListenerToButtons();
    }

    private void setListenerToButtons() {
        oneButton.setOnClickListener(this);
        twoButton.setOnClickListener(this);
        threeButton.setOnClickListener(this);
        fourButton.setOnClickListener(this);
        fiveButton.setOnClickListener(this);
        sixButton.setOnClickListener(this);
        sevenButton.setOnClickListener(this);
        eightButton.setOnClickListener(this);
        nineButton.setOnClickListener(this);
        zeroButton.setOnClickListener(this);
        equalsButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        multiplyButton.setOnClickListener(this);
        divideButton.setOnClickListener(this);
        clearAllText.setOnClickListener(this);
    }

    private void initUIElements() {
        oneButton = findViewById(R.id.bOne);
        twoButton = findViewById(R.id.bTwo);
        threeButton = findViewById(R.id.bThree);
        fourButton = findViewById(R.id.bFour);
        fiveButton = findViewById(R.id.bFive);
        sixButton = findViewById(R.id.bSix);
        sevenButton = findViewById(R.id.bSeven);
        eightButton = findViewById(R.id.bEight);
        nineButton = findViewById(R.id.bNine);
        zeroButton = findViewById(R.id.bZero);
        plusButton = findViewById(R.id.bPlus);
        minusButton = findViewById(R.id.bMinus);
        multiplyButton = findViewById(R.id.bMultiply);
        divideButton = findViewById(R.id.bDivide);
        textView = findViewById(R.id.tvTextArea);
        equalsButton = findViewById(R.id.bEquals);
        clearAllText = findViewById(R.id.bC);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button)view;
        switch (view.getId()) {
            case R.id.bOne:
            case R.id.bTwo:
            case R.id.bThree:
            case R.id.bFour:
            case R.id.bFive:
            case R.id.bSix:
            case R.id.bSeven:
            case R.id.bEight:
            case R.id.bNine:
            case R.id.bZero:
                controller.addNumberToTextViewValue(button.getText().toString());
                break;
            case R.id.bPlus:
            case R.id.bMinus:
            case R.id.bMultiply:
            case R.id.bDivide:
                controller.setSign(button.getText().toString());
                break;
            case R.id.bEquals:
                controller.calculateExpression();
                break;
            case R.id.bC:
                controller.clearAll();
                break;
            default: break;
        }
    }
}
