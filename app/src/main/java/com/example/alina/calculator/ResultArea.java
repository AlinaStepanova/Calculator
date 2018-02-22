package com.example.alina.calculator;

import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import static com.example.alina.calculator.Values.CLOSED_PARENTHESIS;
import static com.example.alina.calculator.Values.EMPTY_STRING;
import static com.example.alina.calculator.Values.OPENED_PARENTHESIS;
import static com.example.alina.calculator.Values.ZERO;

public class ResultArea {

    private TextView textView;
    private NumberFormat numberFormat;

    public ResultArea(TextView textView) {
        this.textView = textView;
        this.numberFormat = new DecimalFormat("#.######");
    }

    public void appendSymbol(String text) {
        if (textView.getText().toString().equals(ZERO)) {
            textView.setText(EMPTY_STRING);
        }
        textView.append(text);
    }

    public int getTextViewLength() {
        return textView.getText().toString().length();
    }

    public String getSign(String firstValue) {
        String sign;
        if (textView.getText().toString().equals(ZERO)
                || textView.getText().toString().length() == firstValue.length()) {
            sign = EMPTY_STRING;
        } else {
            rewriteSign(textView.getText().toString().substring(getTextViewLength() - 1));
            sign = textView.getText().toString().substring(getTextViewLength() - 1);
        }
        return sign;
    }

    public String getFormat(String value) {
        return numberFormat.format(Double.valueOf(value));
    }

    public String getSignFromExpression(String firstValue) {
        return textView.getText().toString().substring(firstValue.length(), firstValue.length() + 1);
    }

    public String getSecondValueFromExpression(String firstValue) {
        return textView.getText().toString().substring(firstValue.length() + 1,
                getTextViewLength()).replace(",", ".");
    }

    public double getDoubleText() {
        Number number = null;
        try {
            number = numberFormat.parse(textView.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return number != null ? number.doubleValue() : 0;
    }

    public void setResult(double value) {
        textView.setText(numberFormat.format(value));
    }

    public void setStringResult(String stringResult) {
        textView.setText(stringResult);
    }

    public void setMultipleStrings(String firstValue, String sign, String secondValue,
                                   String coma) {
        if (secondValue.isEmpty()) {
            setStringResult(getFormat(firstValue) + sign);
        } else if (Double.parseDouble(secondValue) < 0) {
            setStringResult(getFormat(firstValue) + sign + OPENED_PARENTHESIS
                    + getFormat(secondValue) + coma + CLOSED_PARENTHESIS);
        } else {
            setStringResult(getFormat(firstValue) + sign + getFormat(secondValue) + coma);
        }
    }

    public void rewriteSign(String sign) {
        String text = textView.getText().toString();
        textView.setText(text.substring(0, text.length() - 1));
        textView.append(sign);
    }

    public void clearText() {
        textView.setText(ZERO);
    }
}
