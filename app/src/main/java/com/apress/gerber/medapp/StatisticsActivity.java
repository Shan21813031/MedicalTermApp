package com.apress.gerber.medapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StatisticsActivity extends AppCompatActivity {

    TextView text1,text2,text3;
    Button quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        text1 = (TextView) findViewById(R.id.t1);
        text2 = (TextView) findViewById(R.id.t2);
        text3 = (TextView) findViewById(R.id.t3);

        Intent x = getIntent();

        String questions = x.getStringExtra("total");
        String correct = x.getStringExtra("correct");
        String wrong = x.getStringExtra("incorrect");

        text1.setText(questions);
        text2.setText(correct);
        text3.setText(wrong);

        quit = findViewById(R.id.Return);

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StatisticsActivity.this, MenuActivity.class));
            }
        });





    }
}