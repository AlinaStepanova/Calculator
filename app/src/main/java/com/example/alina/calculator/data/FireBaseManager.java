package com.example.alina.calculator.data;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.alina.calculator.Values.EMPTY_STRING;

public class FireBaseManager {

    private DatabaseReference reference;
    private OnDataReceived onDataReceived;

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
        DatabaseReference numbersReference = reference.child("numbers").child(key);
        numbersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            String number = EMPTY_STRING;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                number = dataSnapshot.getValue(String.class);
                onDataReceived.onDataReceived(number);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void receiveOperation(String key) {
        final DatabaseReference operationsReference = reference.child("operations").child(key);
        operationsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            String operation = EMPTY_STRING;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                operation = dataSnapshot.getValue(String.class);
                onDataReceived.onDataReceived(operation);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
