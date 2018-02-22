package com.example.alina.calculator.data;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.alina.calculator.data.listeners.OnDataStored;

import java.util.HashMap;

import static com.example.alina.calculator.Values.NAME_NUMBER_KEY;
import static com.example.alina.calculator.Values.NAME_NUMBER_VALUE;
import static com.example.alina.calculator.Values.NAME_OPERATION_KEY;
import static com.example.alina.calculator.Values.NAME_OPERATION_VALUE;
import static com.example.alina.calculator.Values.NUMBERS_COUNT;
import static com.example.alina.calculator.Values.OPERATIONS_COUNT;

public class DataReceiver extends BroadcastReceiver {

    public HashMap<String, String> numbers = new HashMap<>();
    public HashMap<String, String> operations = new HashMap<>();
    public OnDataStored onDataStored;

    public DataReceiver(OnDataStored onDataStored) {
        this.onDataStored = onDataStored;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getStringExtra(NAME_NUMBER_KEY) != null
                && intent.getStringExtra(NAME_NUMBER_VALUE) != null) {
            numbers.put(intent.getStringExtra(NAME_NUMBER_KEY),
                    intent.getStringExtra(NAME_NUMBER_VALUE));
        }
        if (intent.getStringExtra(NAME_OPERATION_KEY) != null
                && intent.getStringExtra(NAME_OPERATION_VALUE) != null) {
            operations.put(intent.getStringExtra(NAME_OPERATION_KEY),
                    intent.getStringExtra(NAME_OPERATION_VALUE));
        }
        if (numbers.size() == NUMBERS_COUNT && operations.size() == OPERATIONS_COUNT) {
            onDataStored.onDataStored(numbers, operations);
        }
    }
}
