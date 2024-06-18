package com.example.database_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {


    DataBaseHandler db;
    TextView textView;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        textView = findViewById(R.id.ViewSaveData);
        db = new DataBaseHandler(this);

        data = db.getEmployee();
        textView.setText(data);

    }
}