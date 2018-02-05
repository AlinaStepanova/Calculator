package com.example.alina.calculator;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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
        value = 0;
        firstValue = "0";
        secondValue = "";
        sign = "";
    }

    private void divideExpression() {
        if (Double.parseDouble(secondValue) != 0 ) {
            value = Double.parseDouble(firstValue) / Double.parseDouble(secondValue);
        } else {
            Toast.makeText(context, "Ділення на 0 неможливе", Toast.LENGTH_SHORT).show();
            clearAll();
        }
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

    public void deleteLastCharacter() {
        if (!firstValue.isEmpty() && secondValue.isEmpty() && sign.isEmpty()
                && !firstValue.equals(String.valueOf(value))) {
            if (firstValue.length() == 1) {
                firstValue = "0";
            } else {
                firstValue = firstValue.substring(0, firstValue.length() - 1);
            }
            resultArea.setResult(Double.valueOf(firstValue));
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()) {
            if (secondValue.length() > 1) {
                secondValue = secondValue.substring(0, secondValue.length() - 1);
            } else if (secondValue.length() > 1 && (secondValue.contains(")"))) {
                secondValue = "0";
            } else {
                secondValue = "0";
            }
            resultArea.setMultipleStrings(firstValue, sign, secondValue, "");
        }
    }

    public void addNumberToTextViewValue(String textViewValue) {
        if (firstValue.equals(String.valueOf(value)) && secondValue.isEmpty() && sign.isEmpty()) {
            clearAll();
            firstValue += textViewValue;
        } else if (sign.equals("")) {
            firstValue += textViewValue;
        } else {
            if (secondValue.equals("0")) {
                secondValue = "";
            }
            secondValue += textViewValue;
        }
        resultArea.appendSymbol(textViewValue);
    }

    public void putComa() {
        if (!firstValue.isEmpty() && secondValue.isEmpty() && sign.isEmpty()
                && !firstValue.contains(".")) {
            firstValue = String.valueOf(firstValue + ".");
            resultArea.setStringResult(resultArea.getFormat(Double.valueOf(firstValue)) + ",");
        } else if (!firstValue.isEmpty() && secondValue.isEmpty() && !sign.isEmpty()
                && !secondValue.contains(".")) {
            secondValue = String.valueOf("0.");
            resultArea.setMultipleStrings(firstValue, sign, secondValue, ",");
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()
                && !secondValue.contains(".")) {
            secondValue = String.valueOf(secondValue + ".");
            resultArea.setMultipleStrings(firstValue, sign, secondValue, ",");
        }
    }

    public void changeSign() {
        if (!firstValue.isEmpty() && secondValue.isEmpty() && sign.isEmpty()) {
            firstValue = String.valueOf(-(Double.parseDouble(firstValue)));
            resultArea.setResult(Double.valueOf(firstValue));
        } else if (!firstValue.isEmpty() && secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(-(Double.parseDouble(firstValue)));
            resultArea.setMultipleStrings(firstValue, sign, secondValue, "");
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(-(Double.parseDouble(secondValue)));
            resultArea.setMultipleStrings(firstValue, sign, secondValue, "");
        }
    }

    public void clearCE() {
        if (!firstValue.isEmpty() && secondValue.isEmpty() && sign.isEmpty()) {
            clearAll();
        } else if (!firstValue.isEmpty() && secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(0);
            resultArea.setMultipleStrings(firstValue, sign, secondValue, "");
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(0);
            resultArea.setMultipleStrings(firstValue, sign, secondValue, "");
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
            resultArea.setMultipleStrings(firstValue, sign, secondValue, "");
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()
                && (Double.parseDouble(secondValue) >= 0)) {
            secondValue = String.valueOf(Math.sqrt(Double.parseDouble(secondValue)));
            resultArea.setMultipleStrings(firstValue, sign, secondValue, "");
        } else {
            Toast.makeText(context, "Уведені некоректні дані", Toast.LENGTH_SHORT).show();
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
            resultArea.setMultipleStrings(firstValue, sign, secondValue, "");
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(Double.parseDouble(firstValue) / 100 *
                    Double.parseDouble(secondValue));
            resultArea.setMultipleStrings(firstValue, sign, secondValue, "");
        }
    }

    public void findSqr() {
        if (!firstValue.isEmpty() && secondValue.isEmpty() && sign.isEmpty()) {
            firstValue = String.valueOf(Math.pow((Double.parseDouble(firstValue)), 2));
            resultArea.setResult(Double.valueOf(firstValue));
        } else if (!firstValue.isEmpty() && secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(Math.pow((Double.parseDouble(firstValue)), 2));
            resultArea.setMultipleStrings(firstValue, sign, secondValue, "");
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()) {
            secondValue = String.valueOf(Math.pow((Double.parseDouble(secondValue)), 2));
            resultArea.setMultipleStrings(firstValue, sign, secondValue, "");
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
            resultArea.setMultipleStrings(firstValue, sign, secondValue, "");
        } else if (!firstValue.isEmpty() && !secondValue.isEmpty() && !sign.isEmpty()
                && (Double.parseDouble(secondValue) != 0)) {
            secondValue = String.valueOf(1 / (Double.parseDouble(secondValue)));
            resultArea.setMultipleStrings(firstValue, sign, secondValue, "");
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
        Log.d("val", firstValue + " " + secondValue + " value " + value);
        resultArea.setResult(value);
        resetValues();
    }

    public void clearAll() {
        initValues();
        resultArea.clearText();
    }
}
