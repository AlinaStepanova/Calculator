package com.example.alina.calculator.data.listeners;


import java.util.HashMap;

public interface OnDataStored {

    void onDataStored(HashMap<String, String> numbers, HashMap<String, String> operations);
}
