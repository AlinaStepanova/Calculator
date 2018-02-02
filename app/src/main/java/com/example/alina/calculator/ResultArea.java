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
        if (textView.getText().equals("0")) {
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
        textView.setText(numberFormat.format(value));
    }

    public void clearText() {
        textView.setText("0");
    }
}
