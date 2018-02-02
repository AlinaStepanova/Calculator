package com.example.alina.calculator;

import android.widget.TextView;

public class Controller {

    private ResultArea resultArea;
    private String firstValue;
    private String secondValue;
    private String sign;
    private double value;

    public Controller(TextView textView) {
        resultArea = new ResultArea(textView);
        initValues();
    }

    private void initValues() {
        firstValue = "";
        secondValue = "";
        sign = "";
        value = 0;
    }

    public void setSign(String sign) {
        if (this.sign.equals("")) {
            resultArea.appendSymbol(sign);
            this.sign = sign;
        } else if(!firstValue.isEmpty() && !sign.isEmpty() && secondValue.isEmpty()) {
            resultArea.rewriteSign(sign);
            this.sign = sign;
        }
        else {
            calculateExpression();
        }
    }

    public void addNumberToTextViewValue(String textViewValue) {
        resultArea.appendSymbol(textViewValue);
        if(sign.equals("")) {
            firstValue += textViewValue;
        } else {
            secondValue += textViewValue;
        }
    }

    public void calculateExpression() {
        if (secondValue.isEmpty()) {secondValue = firstValue;}
        switch (sign) {
            case "+":
                value = Double.parseDouble(firstValue) + Double.parseDouble(secondValue);
                break;
            case "-":
                value = Double.parseDouble(firstValue) - Double.parseDouble(secondValue);
                break;
            case "*":
                value = Double.parseDouble(firstValue) * Double.parseDouble(secondValue);
                break;
            case "/":
                value = Double.parseDouble(firstValue) / Double.parseDouble(secondValue);
                break;
            default: break;
        }
        resultArea.setResult(value);
        resetValues();
    }

    public void clearAll() {
        initValues();
        resultArea.clearText();
    }

    private void resetValues() {
        firstValue = String.valueOf(value);
        secondValue = "";
        sign = "";
    }
}
