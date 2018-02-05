package com.example.alina.calculator;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Controller {

    private ResultArea resultArea;
    private String firstValue;
    private String secondValue;
    private String sign;
    private double value;
    private Context context;
    private NumberFormat numberFormat;

    public Controller(TextView textView, Context context) {
        this.context = context;
        this.resultArea = new ResultArea(textView);
        this.numberFormat =  new DecimalFormat("#.######");
        initValues();
    }

    private void initValues() {
        value = 0;
        firstValue = "0";
        secondValue = "";
        sign = "";
    }

    public void clearCE() {
        if (!firstValue.isEmpty() && secondValue.isEmpty() && sign.isEmpty()) {
            clearAll();
        } else if (!firstValue.isEmpty() && secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(0);
            resultArea.setMultipleStrings(firstValue, sign, secondValue);
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(0);
            resultArea.setMultipleStrings(firstValue, sign, secondValue);
        }
    }

    public void findSqrt() {
        if (!firstValue.isEmpty() && secondValue.isEmpty() && sign.isEmpty()
                && (Double.parseDouble(firstValue) >= 0)) {
            firstValue = String.valueOf(Math.sqrt(Double.parseDouble(firstValue)));
            resultArea.setStringResult(numberFormat.format(Double.valueOf(firstValue)));
        } else if (!firstValue.isEmpty() && secondValue.isEmpty() && !sign.isEmpty()
                && (Double.parseDouble(firstValue) >= 0)) {
            secondValue = String.valueOf(Math.sqrt(Double.parseDouble(firstValue)));
            resultArea.setMultipleStrings(firstValue, sign, secondValue);
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()
                && (Double.parseDouble(secondValue) >= 0)) {
            secondValue = String.valueOf(Math.sqrt(Double.parseDouble(secondValue)));
            resultArea.setMultipleStrings(firstValue, sign, secondValue);
        } else {
            Toast.makeText(context, "Уведені некоректні дані", Toast.LENGTH_SHORT).show();
            clearAll();
        }
    }

    public void findPercent() {
        if (!firstValue.isEmpty() && secondValue.isEmpty() && sign.isEmpty()) {
            firstValue = String.valueOf(Double.parseDouble(firstValue) / 100);
            resultArea.setStringResult(numberFormat.format(Double.valueOf(firstValue)));
        } else if (!firstValue.isEmpty() && secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(Double.parseDouble(firstValue) / 100 *
                    Double.parseDouble(firstValue));
            resultArea.setMultipleStrings(firstValue, sign, secondValue);
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(Double.parseDouble(firstValue) / 100 *
                    Double.parseDouble(secondValue));
            resultArea.setMultipleStrings(firstValue, sign, secondValue);
        }
    }

    public void findSqr() {
        if (!firstValue.isEmpty() && secondValue.isEmpty() && sign.isEmpty()) {
            firstValue = String.valueOf(Math.pow((Double.parseDouble(firstValue)), 2));
            resultArea.setStringResult(numberFormat.format(Double.valueOf(firstValue)));
        } else if (!firstValue.isEmpty() && secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(Math.pow((Double.parseDouble(firstValue)), 2));
            resultArea.setMultipleStrings(firstValue, sign, secondValue);
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(Math.pow((Double.parseDouble(secondValue)), 2));
            resultArea.setMultipleStrings(firstValue, sign, secondValue);
        }
    }

    public void oneDivideX() {
        if (!firstValue.isEmpty() && secondValue.isEmpty() && sign.isEmpty()
                && (Double.parseDouble(firstValue) != 0)) {
            firstValue = String.valueOf(1 / (Double.parseDouble(firstValue)));
            resultArea.setStringResult(numberFormat.format(Double.valueOf(firstValue)));
        } else if (!firstValue.isEmpty() && secondValue.isEmpty() && !sign.isEmpty()
                && (Double.parseDouble(firstValue) != 0)) {
            secondValue = String.valueOf(1 / (Double.parseDouble(firstValue)));
            resultArea.setMultipleStrings(firstValue, sign, secondValue);
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()
                && (Double.parseDouble(secondValue) != 0)) {
            secondValue = String.valueOf(1 / (Double.parseDouble(secondValue)));
            resultArea.setMultipleStrings(firstValue, sign, secondValue);
        } else {
            Toast.makeText(context, "Ділення на 0 неможливе", Toast.LENGTH_SHORT).show();
            clearAll();
        }
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
        if(sign.equals("")) {
            firstValue += textViewValue;
        } else {
            secondValue += textViewValue;
        }
        Log.d("val", firstValue + " " + secondValue + " value " + value);
        resultArea.appendSymbol(textViewValue);
    }

    public void calculateExpression() {
        if (secondValue.isEmpty()) {
            secondValue = firstValue;
            value = Double.parseDouble(firstValue);
        }
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
                divideExpression();
                break;
            default: break;
        }
        resultArea.setResult(value);
        resetValues();
    }

    private void divideExpression() {
        if (Double.parseDouble(secondValue) != 0 ) {
            value = Double.parseDouble(firstValue) / Double.parseDouble(secondValue);
        } else {
            Toast.makeText(context, "Ділення на 0 неможливе", Toast.LENGTH_SHORT).show();
            clearAll();
        }
    }

    public void clearAll() {
        initValues();
        resultArea.clearText();
    }

    private void resetValues() {
        firstValue = appendAsIntegerNumber(value);
        secondValue = "";
        sign = "";
    }

    private String appendAsIntegerNumber(double value) {
        String text = "";
        String valueString = String.valueOf(value);
        int dotPosition = String.valueOf(value).indexOf(".");
        if (value % 10 == 0) {
            text = valueString.substring(0, dotPosition);
        } else {
            text += value;
        }
        return text;
    }
}
