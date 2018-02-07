package com.example.alina.calculator;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.alina.calculator.Values.*;

public class Controller {

    private ResultArea resultArea;
    private String firstValue;
    private String secondValue;
    private String sign;
    private double value;
    private Context context;

    public Controller(TextView textView, Context context) {
        this.context = context;
        this.resultArea = new ResultArea(textView);
        initValues();
    }

    private void initValues() {
        value = resultArea.getDoubleText();
        firstValue = appendAsIntegerNumber(value);
        secondValue = EMPTY_STRING;
        sign = EMPTY_STRING;
    }

    private void divideExpression() {
        if (Double.parseDouble(secondValue) != 0 ) {
            value = Double.parseDouble(firstValue) / Double.parseDouble(secondValue);
        } else {
            Toast.makeText(context, DIVIDE_BY_ZERO_MESSAGE, Toast.LENGTH_SHORT).show();
            clearAll();
        }
    }

    private void resetValues() {
        firstValue = appendAsIntegerNumber(value);
        value = 0;
        secondValue = EMPTY_STRING;
        sign = EMPTY_STRING;
    }

    private String appendAsIntegerNumber(double value) {
        String text = EMPTY_STRING;
        String valueString = String.valueOf(value);
        int dotPosition = String.valueOf(value).indexOf(DOT);
        if (valueString.substring(valueString.length() - 1).equals(ZERO)) {
            text = valueString.substring(0, dotPosition);
        } else {
            text += value;
        }
        return text;
    }

    public void deleteLastCharacter() {
        if (!firstValue.isEmpty() && secondValue.isEmpty() && sign.isEmpty()
                && !firstValue.equals(String.valueOf(value))) {
            if (firstValue.length() == 1) {
                firstValue = ZERO;
            } else {
                firstValue = firstValue.substring(0, firstValue.length() - 1);
            }
            resultArea.setResult(Double.valueOf(firstValue));
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()) {
            if (secondValue.length() > 1) {
                secondValue = secondValue.substring(0, secondValue.length() - 1);
            } else if (secondValue.length() > 1 && (secondValue.contains(CLOSED_PARENTHESIS))) {
                secondValue = ZERO;
            } else {
                secondValue = ZERO;
            }
            resultArea.setMultipleStrings(firstValue, sign, secondValue, EMPTY_STRING);
        }
    }

    public void addNumberToTextViewValue(String textViewValue) {
        if (firstValue.equals(String.valueOf(value)) && secondValue.isEmpty() && sign.isEmpty()) {
            clearAll();
            firstValue += textViewValue;
        } else if (sign.equals(EMPTY_STRING)) {
            firstValue += textViewValue;
        } else {
            if (secondValue.equals(ZERO)) {
                secondValue = EMPTY_STRING;
            }
            secondValue += textViewValue;
        }
        resultArea.appendSymbol(textViewValue);
    }

    public void putComa() {
        if (!firstValue.isEmpty() && secondValue.isEmpty() && sign.isEmpty()
                && !firstValue.contains(DOT)) {
            firstValue = String.valueOf(firstValue + DOT);
            resultArea.setStringResult(resultArea.getFormat(firstValue) + COMA);
        } else if (firstValue.equals(appendAsIntegerNumber(value)) && secondValue.isEmpty()
                && sign.isEmpty()) {
            firstValue = String.valueOf(ZERO + DOT);
            resultArea.setStringResult(resultArea.getFormat(firstValue) + COMA);
        } else if (!firstValue.isEmpty() && secondValue.isEmpty() && !sign.isEmpty()
                && !secondValue.contains(DOT)) {
            secondValue = String.valueOf(ZERO + DOT);
            resultArea.setMultipleStrings(firstValue, sign, secondValue, COMA);
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()
                && !secondValue.contains(DOT)) {
            secondValue = String.valueOf(secondValue + DOT);
            resultArea.setMultipleStrings(firstValue, sign, secondValue, COMA);
        }
    }

    public void changeSign() {
        if (!firstValue.isEmpty() && secondValue.isEmpty() && sign.isEmpty()) {
            firstValue = String.valueOf(-(Double.parseDouble(firstValue)));
            resultArea.setResult(Double.valueOf(firstValue));
        } else if (!firstValue.isEmpty() && secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(-(Double.parseDouble(firstValue)));
            resultArea.setMultipleStrings(firstValue, sign, secondValue, EMPTY_STRING);
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(-(Double.parseDouble(secondValue)));
            resultArea.setMultipleStrings(firstValue, sign, secondValue, EMPTY_STRING);
        }
    }

    public void clearCE() {
        if (!firstValue.isEmpty() && secondValue.isEmpty() && sign.isEmpty()) {
            clearAll();
        } else if (!firstValue.isEmpty() && secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(0);
            resultArea.setMultipleStrings(firstValue, sign, secondValue, EMPTY_STRING);
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(0);
            resultArea.setMultipleStrings(firstValue, sign, secondValue, EMPTY_STRING);
        }
    }

    public void findSqrt() {
        if (!firstValue.isEmpty() && secondValue.isEmpty() && sign.isEmpty()
                && (Double.parseDouble(firstValue) >= 0)) {
            firstValue = String.valueOf(Math.sqrt(Double.parseDouble(firstValue)));
            resultArea.setResult(Double.valueOf(firstValue));
        } else if (!firstValue.isEmpty() && secondValue.isEmpty() && !sign.isEmpty()
                && (Double.parseDouble(firstValue) >= 0)) {
            secondValue = String.valueOf(Math.sqrt(Double.parseDouble(firstValue)));
            resultArea.setMultipleStrings(firstValue, sign, secondValue, EMPTY_STRING);
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()
                && (Double.parseDouble(secondValue) >= 0)) {
            secondValue = String.valueOf(Math.sqrt(Double.parseDouble(secondValue)));
            resultArea.setMultipleStrings(firstValue, sign, secondValue, EMPTY_STRING);
        } else {
            Toast.makeText(context, INCORRECT_VALUES_MESSAGE, Toast.LENGTH_SHORT).show();
            clearAll();
        }
    }

    public void findPercent() {
        if (!firstValue.isEmpty() && secondValue.isEmpty() && sign.isEmpty()) {
            firstValue = String.valueOf(Double.parseDouble(firstValue) / 100);
            resultArea.setResult(Double.valueOf(firstValue));
        } else if (!firstValue.isEmpty() && secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(Double.parseDouble(firstValue) / 100 *
                    Double.parseDouble(firstValue));
            resultArea.setMultipleStrings(firstValue, sign, secondValue, EMPTY_STRING);
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(Double.parseDouble(firstValue) / 100 *
                    Double.parseDouble(secondValue));
            resultArea.setMultipleStrings(firstValue, sign, secondValue, EMPTY_STRING);
        }
    }

    public void findSqr() {
        if (!firstValue.isEmpty() && secondValue.isEmpty() && sign.isEmpty()) {
            firstValue = String.valueOf(Math.pow((Double.parseDouble(firstValue)), 2));
            resultArea.setResult(Double.valueOf(firstValue));
        } else if (!firstValue.isEmpty() && secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(Math.pow((Double.parseDouble(firstValue)), 2));
            resultArea.setMultipleStrings(firstValue, sign, secondValue, EMPTY_STRING);
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(Math.pow((Double.parseDouble(secondValue)), 2));
            resultArea.setMultipleStrings(firstValue, sign, secondValue, EMPTY_STRING);
        }
    }

    public void oneDivideX() {
        if (!firstValue.isEmpty() && secondValue.isEmpty() && sign.isEmpty()
                && (Double.parseDouble(firstValue) != 0)) {
            firstValue = String.valueOf(1 / (Double.parseDouble(firstValue)));
            resultArea.setResult(Double.valueOf(firstValue));
        } else if (!firstValue.isEmpty() && secondValue.isEmpty() && !sign.isEmpty()
                && (Double.parseDouble(firstValue) != 0)) {
            secondValue = String.valueOf(1 / (Double.parseDouble(firstValue)));
            resultArea.setMultipleStrings(firstValue, sign, secondValue, EMPTY_STRING);
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()
                && (Double.parseDouble(secondValue) != 0)) {
            secondValue = String.valueOf(1 / (Double.parseDouble(secondValue)));
            resultArea.setMultipleStrings(firstValue, sign, secondValue, EMPTY_STRING);
        } else {
            Toast.makeText(context, DIVIDE_BY_ZERO_MESSAGE, Toast.LENGTH_SHORT).show();
            clearAll();
        }
    }

    public void setSign(String sign) {
        if (this.sign.equals(EMPTY_STRING)) {
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

    public void calculateExpression() {
        if (secondValue.isEmpty()) {
            secondValue = firstValue;
            value = Double.parseDouble(firstValue);
        }
        switch (sign) {
            case PLUS:
                value = Double.parseDouble(firstValue) + Double.parseDouble(secondValue);
                break;
            case MINUS:
                value = Double.parseDouble(firstValue) - Double.parseDouble(secondValue);
                break;
            case MULTIPLY:
                value = Double.parseDouble(firstValue) * Double.parseDouble(secondValue);
                break;
            case DIVIDE:
                divideExpression();
                break;
            default: break;
        }
        resultArea.setResult(value);
        resetValues();
    }

    public void clearAll() {
        resetValues();
        resultArea.clearText();
    }
}
