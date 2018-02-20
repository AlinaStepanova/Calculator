package com.example.alina.calculator.data;


import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.example.alina.calculator.Values.EMPTY_STRING;

public class FireBaseManager {

    private DatabaseReference reference;
    private OnDataReceived onDataReceived;
    private Map<String, String> numbers = new HashMap<>();
    private Map<String, String> operations = new HashMap<>();

    public FireBaseManager(OnDataReceived onDataReceived) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        this.onDataReceived = onDataReceived;
    }

    public void receiveValue(String key) {
        String regex = "[0-9]+";
        if (key.matches(regex)) {
            receiveNumber(key);
        } else {
            receiveOperation(key);
        }
    }

    private void receiveNumber(final String key) {
        DatabaseReference numbersReference = reference.child("numbers");
        numbersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    FireBaseValue value = childSnapshot.getValue(FireBaseValue.class);
                    if (value != null) {
                        numbers.put(value.getKey(), value.getValue());
                    }
                    onDataReceived.onDataReceived(numbers.get(key));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void receiveOperation(final String key) {
        final DatabaseReference operationsReference = reference.child("operations");
        operationsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    FireBaseValue value = childSnapshot.getValue(FireBaseValue.class);
                    if (value != null) {
                        operations.put(value.getKey(), value.getValue());
                    }
                    onDataReceived.onDataReceived(operations.get(key));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
