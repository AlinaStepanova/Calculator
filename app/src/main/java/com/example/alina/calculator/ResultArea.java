package com.example.alina.calculator;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ResultArea {

    private TextView textView;
    private NumberFormat numberFormat;

    public ResultArea(TextView textView) {
        this.textView = textView;
        this.numberFormat =  new DecimalFormat("#.######");
    }

    public void appendSymbol(String text) {
        if (textView.getText().toString().equals("0")) {
            textView.setText("");
        }
        textView.append(text);
    }

    public void rewriteSign(String sign) {
        String text = textView.getText().toString();
        textView.setText(text.substring(0, text.length() - 1));
        textView.append(sign);
    }

    public void setResult(double value) {
        textView.setText(getFormat(value));
    }

    public String getFormat(double value) {
        return numberFormat.format(value);
    }

    public void setStringResult(String stringResult) {
        textView.setText(stringResult);
    }

    public void setMultipleStrings(String firstValue, String sign, String secondValue, String coma) {
        if (Double.parseDouble(secondValue) < 0) {
            setStringResult(getFormat(Double.valueOf(firstValue)) + sign + "("
                    + getFormat(Double.valueOf(secondValue)) + coma + ")");
        } else {
            setStringResult(getFormat(Double.valueOf(firstValue)) + sign
                    + getFormat(Double.valueOf(secondValue)) + coma);
        }
    }

    public void clearText() {
        textView.setText("0");
    }
}
