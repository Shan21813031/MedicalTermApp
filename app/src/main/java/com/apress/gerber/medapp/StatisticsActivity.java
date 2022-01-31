package com.apress.gerber.medapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StatisticsActivity extends AppCompatActivity {

    TextView text1, text2, text3;
    Button csStat, sStat, pStat, dStat, lStat, rStat, qStat, proStat, oppStat;
    Button quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        text1 = (TextView) findViewById(R.id.t1);
        text2 = (TextView) findViewById(R.id.t2);
        text3 = (TextView) findViewById(R.id.t3);

        csStat = (Button) findViewById(R.id.cardioSystem);
        sStat = (Button) findViewById(R.id.stateLbl);
        pStat = (Button) findViewById(R.id.pace);
        dStat = (Button) findViewById(R.id.directionLbl);
        lStat = (Button) findViewById(R.id.locationLbl);
        rStat = (Button) findViewById(R.id.rangeLbl);
        qStat = (Button) findViewById(R.id.quant);
        proStat = (Button) findViewById(R.id.procedure);
        oppStat = (Button) findViewById(R.id.oppLbl);



        Intent x = getIntent();

        String questions = x.getStringExtra("total");
        String correct = x.getStringExtra("correct");
        String wrong = x.getStringExtra("incorrect");
        String cardioSystem = x.getStringExtra("cSystem");
        String state = x.getStringExtra("speed");
        String direction = x.getStringExtra("direction");
        String location = x.getStringExtra("location");
        String range = x.getStringExtra("range");
        String amount = x.getStringExtra("amount");
        String procedure = x.getStringExtra("procedure");
        String opposite = x.getStringExtra("opposites");

        text1.setText(questions);
        text2.setText(correct);
        text3.setText(wrong);
        csStat.setText("cardiovascular system: " + cardioSystem);
        sStat.setText("state: " + state);
        dStat.setText("direction: " + direction);
        lStat.setText("location: " + location);
        rStat.setText("range: " + range);
        qStat.setText("amount/quantity: " + amount);
        proStat.setText("procedure: " + procedure);
        oppStat.setText("opposites: " + opposite);



        quit = findViewById(R.id.Return);

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StatisticsActivity.this, MenuActivity.class));
            }
        });
    }
}
