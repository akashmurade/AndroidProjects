package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

// Import Rhino's Context using the full package name to avoid conflicts
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText input, output;
    MaterialButton ac, back, divide, multiply, seven, eight, nine, minus, four, five, six, plus, one, two, three, equals, zero, period;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        output = findViewById(R.id.output);

        ac = findViewById(R.id.ac);
        back = findViewById(R.id.back);
        divide = findViewById(R.id.divide);
        multiply = findViewById(R.id.multiply);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        minus = findViewById(R.id.minus);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        plus = findViewById(R.id.plus);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        equals = findViewById(R.id.equals);
        zero = findViewById(R.id.zero);
        period = findViewById(R.id.period);

        // Set click listeners for all buttons
        setListeners();
    }

    private void setListeners() {
        MaterialButton[] buttons = {ac, back, divide, multiply, seven, eight, nine, minus, four, five, six, plus, one, two, three, equals, zero, period};

        for (MaterialButton button : buttons) {
            button.setOnClickListener(this);
        }
    }

    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = input.getText().toString();

        if (buttonText.equals("AC")) {
            input.setText("");
            output.setText("");
            return;
        }
        else if (buttonText.equals("=")) {
            output.setText(getResult(dataToCalculate));
            input.setText(output.getText());
            return;
        }
        else if (buttonText.equals("B")) {
            if (dataToCalculate.length() > 0) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        }
        else if (dataToCalculate.equals("0")) {
            if(!(buttonText.equals("+") || buttonText.equals("*") || buttonText.equals("/"))) {
                dataToCalculate = buttonText;
            }
        }
        else {
            if (dataToCalculate.length() > 0 && "+-*/".contains(dataToCalculate.charAt(dataToCalculate.length() - 1) + "")) {
                if (buttonText.equals("+") || buttonText.equals("*") || buttonText.equals("/") || buttonText.equals("-")) {
                    dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
                }
            }
            dataToCalculate = dataToCalculate + buttonText;
        }
        input.setText(dataToCalculate);
    }

    private String getResult(String dataToCalculate) {
        try {
            // Use the full class name to avoid conflicts with Android Context
            org.mozilla.javascript.Context context = org.mozilla.javascript.Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();

            if(dataToCalculate.endsWith(".") || dataToCalculate.endsWith("-") || dataToCalculate.endsWith("+") || dataToCalculate.endsWith("*") || dataToCalculate.endsWith("/")) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }

            String finalResult = context.evaluateString(scriptable, dataToCalculate, "Javascript", 1, null).toString();
            context.exit();  // Always exit the context to avoid memory leaks

            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }
}
