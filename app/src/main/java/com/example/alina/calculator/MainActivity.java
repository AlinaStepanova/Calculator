package com.example.alina.calculator;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.alina.calculator.data.DataReceiver;
import com.example.alina.calculator.data.DataService;
import com.example.alina.calculator.data.listeners.OnDataStored;

import java.util.HashMap;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static com.example.alina.calculator.Values.ACTION_REQUEST_TO_FIRE_BASE;
import static com.example.alina.calculator.Values.KEY_NUMBERS_MAP;
import static com.example.alina.calculator.Values.KEY_OPERATIONS_MAP;
import static com.example.alina.calculator.Values.TEXT_VIEW_VALUE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnDataStored{

    private Controller controller;
    private TextView textView;
    private TableLayout tableLayout;
    private DataReceiver dataReceiver;
    public HashMap<String, String> numbers = new HashMap<>();
    public HashMap<String, String> operations = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tvTextArea);
        tableLayout = findViewById(R.id.tlContainer);
        dataReceiver = new DataReceiver(this);
        startService();
        resetUI(savedInstanceState);
        controller = new Controller(textView, getApplicationContext());
    }

    private void resetUI(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            textView.setText(savedInstanceState.getString(TEXT_VIEW_VALUE));
            numbers = (HashMap<String, String>) savedInstanceState.getSerializable(KEY_NUMBERS_MAP);
            operations = (HashMap<String, String>) savedInstanceState.getSerializable(KEY_OPERATIONS_MAP);
            initTableLayout(numbers, operations);
        }
    }

    private void startService() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_REQUEST_TO_FIRE_BASE);
        registerReceiver(dataReceiver, intentFilter);
        Intent intent = new Intent(this, DataService.class);
        startService(intent);
    }

    private void initTableLayout(HashMap<String, String> numbers,
                                 HashMap<String, String> operations) {
        Log.d("val", "in initTableLayout");
        initTableRow(initButton(R.id.bPercent, operations.get("percent")),
                initButton(R.id.bSqrt, operations.get("sqrt")),
                initButton(R.id.bSqr, operations.get("xSquared")),
                initButton(R.id.bOneDivX, operations.get("_1_x")));
        initTableRow(initButton(R.id.bCE, operations.get("ce")),
                initButton(R.id.bC, operations.get("c")),
                initButton(R.id.bDeleteLast, operations.get("deleteLast")),
                initButton(R.id.bDivide, operations.get("divide")));
        initTableRow(initButton(R.id.bSeven, numbers.get("7")),
                initButton(R.id.bEight, numbers.get("8")),
                initButton(R.id.bNine, numbers.get("9")),
                initButton(R.id.bMultiply, operations.get("multiply")));
        initTableRow(initButton(R.id.bFour, numbers.get("4")),
                initButton(R.id.bFive, numbers.get("5")),
                initButton(R.id.bSix, numbers.get("6")),
                initButton(R.id.bMinus, operations.get("minus")));
        initTableRow(initButton(R.id.bOne, numbers.get("1")),
                initButton(R.id.bTwo, numbers.get("2")),
                initButton(R.id.bThree, numbers.get("3")),
                initButton(R.id.bPlus, operations.get("plus")));
        initTableRow(initButton(R.id.bChangeSign, operations.get("changeSign")),
                initButton(R.id.bZero, numbers.get("0")),
                initButton(R.id.bComa, operations.get("coma")),
                initButton(R.id.bEquals, operations.get("equals")));
    }

    private void initTableRow(CustomButton child, CustomButton child2, CustomButton child3,
                              CustomButton child4) {
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT, 1f));
        row.setGravity(Gravity.CENTER);
        row.addView(child);
        row.addView(child2);
        row.addView(child3);
        row.addView(child4);
        tableLayout.addView(row);
    }

    @NonNull
    private CustomButton initButton(int id, String text) {
        CustomButton customButton =
                (CustomButton) getLayoutInflater().inflate(R.layout.custom_button, null);
        customButton.setLayoutParams(new TableRow.LayoutParams(0, MATCH_PARENT, 1.0f));
        customButton.setOnClickListener(this);
        customButton.initValues(id, text);
        return customButton;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_VIEW_VALUE, textView.getText().toString());
        outState.putSerializable(KEY_NUMBERS_MAP, numbers);
        outState.putSerializable(KEY_OPERATIONS_MAP, operations);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        switch (view.getId()) {
            case R.id.bOne:
            case R.id.bTwo:
            case R.id.bThree:
            case R.id.bFour:
            case R.id.bFive:
            case R.id.bSix:
            case R.id.bSeven:
            case R.id.bEight:
            case R.id.bNine:
            case R.id.bZero:
                controller.addNumberToTextViewValue(button.getText().toString());
                break;
            case R.id.bPlus:
            case R.id.bMinus:
            case R.id.bMultiply:
            case R.id.bDivide:
                controller.setSign(button.getText().toString());
                break;
            case R.id.bEquals:
                controller.calculateExpression();
                break;
            case R.id.bC:
                controller.clearAll();
                break;
            case R.id.bSqrt:
                controller.findSqrt();
                break;
            case R.id.bSqr:
                controller.findSqr();
                break;
            case R.id.bOneDivX:
                controller.oneDivideX();
                break;
            case R.id.bPercent:
                controller.findPercent();
                break;
            case R.id.bCE:
                controller.clearCE();
                break;
            case R.id.bChangeSign:
                controller.changeSign();
                break;
            case R.id.bComa:
                controller.putComa();
                break;
            case R.id.bDeleteLast:
                controller.deleteLastCharacter();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStop() {
        unregisterReceiver(dataReceiver);
        super.onStop();
    }

    @Override
    public void onDataStored(HashMap<String, String> numbers,
                             HashMap<String, String> operations) {
        this.numbers = numbers;
        this.operations = operations;
        initTableLayout(this.numbers, this.operations);
    }
}
