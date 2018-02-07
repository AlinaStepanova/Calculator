package com.example.alina.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.alina.calculator.Values.TEXT_VIEW_VALUE;

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
    private Button clearAllTextButton;
    private Button sqrtButton;
    private Button sqrButton;
    private Button oneDivXButton;
    private Button percentButton;
    private Button clearCEButton;
    private Button changeSignButton;
    private Button comaButton;
    private Button deleteLastCharacterButton;
    private Controller controller;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUIElements();
        textView = findViewById(R.id.tvTextArea);
        if (savedInstanceState != null) {
            textView.setText(savedInstanceState.getString(TEXT_VIEW_VALUE));
        }
        controller = new Controller(textView, getApplicationContext());
        setListenerToButtons();
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
        equalsButton = findViewById(R.id.bEquals);
        clearAllTextButton = findViewById(R.id.bC);
        sqrtButton = findViewById(R.id.bSqrt);
        sqrButton = findViewById(R.id.bSqr);
        oneDivXButton = findViewById(R.id.bOneDivX);
        percentButton = findViewById(R.id.bPercent);
        clearCEButton = findViewById(R.id.bCE);
        changeSignButton = findViewById(R.id.bChangeSing);
        comaButton = findViewById(R.id.bComa);
        deleteLastCharacterButton = findViewById(R.id.bDeleteLast);
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
        clearAllTextButton.setOnClickListener(this);
        sqrtButton.setOnClickListener(this);
        sqrButton.setOnClickListener(this);
        oneDivXButton.setOnClickListener(this);
        percentButton.setOnClickListener(this);
        clearCEButton.setOnClickListener(this);
        changeSignButton.setOnClickListener(this);
        comaButton.setOnClickListener(this);
        deleteLastCharacterButton.setOnClickListener(this);
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
            case R.id.bSqrt:
                controller.findSqrt();
                break;
            case R.id.bSqr:
                controller.findSqr();
                break;
            case R.id.bOneDivX:
                controller.oneDivideX();
                break;
            case R.id.bPercent:
                controller.findPercent();
                break;
            case R.id.bCE:
                controller.clearCE();
                break;
            case R.id.bChangeSing:
                controller.changeSign();
                break;
            case R.id.bComa:
                controller.putComa();
                break;
            case R.id.bDeleteLast:
                controller.deleteLastCharacter();
                break;
            default: break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_VIEW_VALUE, textView.getText().toString());
    }
}
