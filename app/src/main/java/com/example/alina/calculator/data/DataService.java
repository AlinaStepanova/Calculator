package com.example.alina.calculator.data;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.alina.calculator.data.listeners.OnDataReceived;

import static com.example.alina.calculator.Values.ACTION_REQUEST_TO_FIRE_BASE;
import static com.example.alina.calculator.Values.NAME_NUMBER_KEY;
import static com.example.alina.calculator.Values.NAME_NUMBER_VALUE;
import static com.example.alina.calculator.Values.NAME_OPERATION_KEY;
import static com.example.alina.calculator.Values.NAME_OPERATION_VALUE;

public class DataService extends Service implements OnDataReceived {

    private Intent intent;

    public DataService() {
        new FireBaseManager(this);
        intent = new Intent();
        intent.setAction(ACTION_REQUEST_TO_FIRE_BASE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDataReceived(String key, String value) {
        String regex = "[0-9]+";
        Log.d("val", "in onDataReceived key " + key + " value " + value);
        if (key.matches(regex)) {
            intent.putExtra(NAME_NUMBER_KEY, key);
            intent.putExtra(NAME_NUMBER_VALUE, value);
            sendBroadcast(intent);
        } else {
            intent.putExtra(NAME_OPERATION_KEY, key);
            intent.putExtra(NAME_OPERATION_VALUE, value);
            sendBroadcast(intent);
        }
    }
}
