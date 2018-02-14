package com.example.alina.calculator;


import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;

import com.example.alina.calculator.data.FireBaseManager;
import com.example.alina.calculator.data.OnDataReceived;

import static com.example.alina.calculator.Values.EMPTY_STRING;

public class CustomButton extends AppCompatButton implements OnDataReceived {

    private FireBaseManager fireBaseManager;

    private int id;

    public CustomButton(Context context) {
        super(context);
        fireBaseManager = new FireBaseManager(this);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        fireBaseManager = new FireBaseManager(this);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        fireBaseManager = new FireBaseManager(this);
    }

    public void queryFireBaseValues(int id, String text) {
        this.id = id;
        setText(EMPTY_STRING);
        fireBaseManager.receiveValue(text);
    }

    @Override
    public void onDataReceived(String data) {
        setId(id);
        setText(data);
    }
}
