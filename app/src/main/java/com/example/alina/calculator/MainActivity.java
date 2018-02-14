package com.example.alina.calculator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static com.example.alina.calculator.Values.TEXT_VIEW_VALUE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Controller controller;
    private TextView textView;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tvTextArea);
        tableLayout = findViewById(R.id.tlContainer);
        if (savedInstanceState != null) {
            textView.setText(savedInstanceState.getString(TEXT_VIEW_VALUE));
        }
        controller = new Controller(textView, getApplicationContext());
        initTableLayout();
    }

    private void initTableLayout() {
        initTableRow(initButton(R.id.bPercent, "percent"),
                initButton(R.id.bSqrt, "sqrt"),
                initButton(R.id.bSqr, "xSquared"),
                initButton(R.id.bOneDivX, "_1_x"));
        initTableRow(initButton(R.id.bCE, "ce"),
                initButton(R.id.bC, "c"),
                initButton(R.id.bDeleteLast, "deleteLast"),
                initButton(R.id.bDivide, "divide"));
        initTableRow(initButton(R.id.bSeven, "7"),
                initButton(R.id.bEight, "8"),
                initButton(R.id.bNine, "9"),
                initButton(R.id.bMultiply, "multiply"));
        initTableRow(initButton(R.id.bFour, "4"),
                initButton(R.id.bFive, "5"),
                initButton(R.id.bSix, "6"),
                initButton(R.id.bMinus, "minus"));
        initTableRow(initButton(R.id.bOne, "1"),
                initButton(R.id.bTwo, "2"),
                initButton(R.id.bThree, "3"),
                initButton(R.id.bPlus, "plus"));
        initTableRow(initButton(R.id.bChangeSign, "changeSign"),
                initButton(R.id.bZero, "0"),
                initButton(R.id.bComa, "coma"),
                initButton(R.id.bEquals, "equals"));
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_VIEW_VALUE, textView.getText().toString());
    }

    @NonNull
    private CustomButton initButton(int id, String text) {
        CustomButton customButton =
                (CustomButton) getLayoutInflater().inflate(R.layout.custom_button, null);
        customButton.setLayoutParams(new TableRow.LayoutParams(0, MATCH_PARENT, 1.0f));
        customButton.setOnClickListener(this);
        customButton.queryFireBaseValues(id, text);
        return customButton;
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
}
