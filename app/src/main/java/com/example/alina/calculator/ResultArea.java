package com.example.alina.calculator;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import static com.example.alina.calculator.Values.*;

public class ResultArea {

    private TextView textView;
    private NumberFormat numberFormat;

    public ResultArea(TextView textView) {
        this.textView = textView;
        this.numberFormat =  new DecimalFormat("#.######");
    }

    public void appendSymbol(String text) {
        if (textView.getText().toString().equals(ZERO)) {
            textView.setText(EMPTY_STRING);
        }
        textView.append(text);
    }

    public void rewriteSign(String sign) {
        String text = textView.getText().toString();
        textView.setText(text.substring(0, text.length() - 1));
        textView.append(sign);
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

    public String getFormat(String value) {
        return numberFormat.format(Double.valueOf(value));
    }

    public void setStringResult(String stringResult) {
        textView.setText(stringResult);
    }

    public void setMultipleStrings(String firstValue, String sign, String secondValue, String coma) {
        if (Double.parseDouble(secondValue) < 0) {
            setStringResult(getFormat(firstValue) + sign + OPENED_PARENTHESIS
                    + getFormat(secondValue) + coma + CLOSED_PARENTHESIS);
        } else {
            setStringResult(getFormat(firstValue) + sign + getFormat(secondValue) + coma);
        }
    }

    public void clearText() {
        textView.setText(ZERO);
    }
}
