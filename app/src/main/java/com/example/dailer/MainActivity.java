package com.example.dailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView inputHolder;

    public static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputHolder = findViewById(R.id.input_holder);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.call_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void zero(View view) {
        inputHolder.setText(inputHolder.getText() + "0");
    }

    @SuppressLint("SetTextI18n")
    public void one(View view) {
        inputHolder.setText(inputHolder.getText() + "1");
    }

    @SuppressLint("SetTextI18n")
    public void two(View view) {
        inputHolder.setText(inputHolder.getText() + "2");
    }

    @SuppressLint("SetTextI18n")
    public void three(View view) {
        inputHolder.setText(inputHolder.getText() + "3");
    }

    @SuppressLint("SetTextI18n")
    public void four(View view) {
        inputHolder.setText(inputHolder.getText() + "4");
    }

    @SuppressLint("SetTextI18n")
    public void five(View view) {
        inputHolder.setText(inputHolder.getText() + "5");
    }

    @SuppressLint("SetTextI18n")
    public void six(View view) {
        inputHolder.setText(inputHolder.getText() + "6");
    }

    @SuppressLint("SetTextI18n")
    public void seven(View view) {
        inputHolder.setText(inputHolder.getText() + "7");
    }

    @SuppressLint("SetTextI18n")
    public void eight(View view) {
        inputHolder.setText(inputHolder.getText() + "8");
    }

    @SuppressLint("SetTextI18n")
    public void nine(View view) {
        inputHolder.setText(inputHolder.getText() + "9");
    }

    @SuppressLint("SetTextI18n")
    public void star(View view) {
        inputHolder.setText(inputHolder.getText() + "*");
    }

    @SuppressLint("SetTextI18n")
    public void hash(View view) {
        inputHolder.setText(inputHolder.getText() + "#");
    }

    public void delete(View view) {
        if (inputHolder.getText().equals("")) {
            inputHolder.setText(null);
        } else {
            inputHolder.setText(inputHolder.getText().subSequence(0, inputHolder.getText().length() - 1));
        }
    }

    public void call() {
        String number = inputHolder.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                //Check if the value entered is USSD.
                if (number.startsWith("*") && number.endsWith("#")) {
                    number = number.substring(0, number.length() - 1);//We remove the last #..
                    String newUSSD = number + Uri.encode("#");//..and we encode our own #(hash) onto the USSD CODE
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + newUSSD)));
                } else {
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number)));
                }
            }
        } else {
            Toast.makeText(this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
