package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        EditText editTextFirst = findViewById(R.id.etFirst);
        EditText editTextSecond = findViewById(R.id.etSecond);
        EditText editTextThird = findViewById(R.id.etThird);
        TextView tvError = findViewById(R.id.tvError);
        Button btnCalc = findViewById(R.id.btnCalc);

        btnCalc.setOnClickListener(v -> {
                    String firstTermStr = editTextFirst.getText().toString().replaceFirst(",", ".");
                    String secondTermStr = editTextSecond.getText().toString().replaceFirst(",", ".");
                    String thirdTermStr = editTextThird.getText().toString().replaceFirst(",", ".");
                    if (!firstTermStr.isEmpty() && !secondTermStr.isEmpty() && !thirdTermStr.isEmpty()) {
                        if (!firstTermStr.matches("[a-z]") && !secondTermStr.matches("[a-z]") && !thirdTermStr.matches("[a-z]")) {
                            double result = 1.0 * Double.parseDouble(firstTermStr) * Double.parseDouble(secondTermStr) * Double.parseDouble(thirdTermStr);
                            DecimalFormat res = new DecimalFormat("#,###");
                            res.format(result);
                            Intent intent = new Intent();
                            intent.putExtra("data", result);
                            setResult(RESULT_OK, intent);
                            finish();
                        }else {
                            tvError.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid (", Toast.LENGTH_LONG).show();
                    }
                }
        );
        tvError.setVisibility(View.GONE);
    }
}