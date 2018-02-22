package com.example.alina.calculator.data;


import com.example.alina.calculator.data.listeners.OnDataReceived;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FireBaseManager {

    private DatabaseReference reference;
    private OnDataReceived onDataReceived;

    public FireBaseManager(OnDataReceived onDataReceived) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        this.onDataReceived = onDataReceived;
        receiveNumber();
        receiveOperation();
    }

    private void receiveNumber() {
        DatabaseReference numbersReference = reference.child("numbers");
        numbersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    FireBaseEntity value = childSnapshot.getValue(FireBaseEntity.class);
                    if (value != null) {
                        onDataReceived.onDataReceived(value.getKey(), value.getValue());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void receiveOperation() {
        final DatabaseReference operationsReference = reference.child("operations");
        operationsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    FireBaseEntity value = childSnapshot.getValue(FireBaseEntity.class);
                    if (value != null) {
                        onDataReceived.onDataReceived(value.getKey(), value.getValue());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
