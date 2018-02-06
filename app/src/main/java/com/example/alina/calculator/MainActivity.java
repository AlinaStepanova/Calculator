package com.example.alina.calculator;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Controller controller;
    private TextView textView;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tvTextArea);
        controller = new Controller(textView, getApplicationContext());
        tableLayout = findViewById(R.id.tlContainer);
        initTableLayout();
    }

    private void initTableLayout() {
        initTableRow(initButton(R.id.bPercent, R.string.percent),
                initButton(R.id.bSqrt, R.string.sqrt),
                initButton(R.id.bSqr, R.string.xSquared),
                initButton(R.id.bOneDivX, R.string._1_x));
        initTableRow(initButton(R.id.bCE, R.string.ce),
                initButton(R.id.bC, R.string.c),
                initButton(R.id.bDeleteLast, R.string.deleteLast),
                initButton(R.id.bDivide, R.string.divide));
        initTableRow(initButton(R.id.bSeven, R.string.seven),
                initButton(R.id.bEight, R.string.eight),
                initButton(R.id.bNine, R.string.nine),
                initButton(R.id.bMultiply, R.string.multiply));
        initTableRow(initButton(R.id.bFour, R.string.four),
                initButton(R.id.bFive, R.string.five),
                initButton(R.id.bSix, R.string.six),
                initButton(R.id.bMinus, R.string.minus));
        initTableRow(initButton(R.id.bOne, R.string.one),
                initButton(R.id.bTwo, R.string.two),
                initButton(R.id.bThree, R.string.three),
                initButton(R.id.bPlus, R.string.plus));
        initTableRow(initButton(R.id.bChangeSign, R.string.changeSign),
                initButton(R.id.bZero, R.string.zero),
                initButton(R.id.bComa, R.string.coma),
                initButton(R.id.bEquals, R.string.equals));
    }

    private void initTableRow(CustomButton child, CustomButton child2, CustomButton child3,
                              CustomButton child4) {
        TableRow firstRow = new TableRow(this);
        firstRow.setLayoutParams(new TableLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT, (float) 1.0));
        firstRow.addView(child);
        firstRow.addView(child2);
        firstRow.addView(child3);
        firstRow.addView(child4);
        tableLayout.addView(firstRow);
    }

    @NonNull
    private CustomButton initButton(int id, int text) {
        CustomButton customButton =
                (CustomButton) getLayoutInflater().inflate(R.layout.custom_button, null);
        customButton.setValues(id, text);
        customButton.setOnClickListener(this);
        return customButton;
    }

    @Override
    public void onClick(View view) {
        Button button = (Button)view;
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
            default: break;
        }
    }
}
