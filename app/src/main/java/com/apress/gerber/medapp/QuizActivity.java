package com.apress.gerber.medapp;

import static android.graphics.Color.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apress.gerber.medapp.Model.Questions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class QuizActivity extends AppCompatActivity {

    private View appView;
    TextView timer, qs;
    Button optA, optB, optC, optD, quitBtn;
    int cSystem = 0;
    int state = 0;
    int speed = 0;
    int direction = 0;
    int location = 0;
    int range = 0;
    int amount = 0;
    int procedure = 0;
    int opposites = 0;
    int total = 1;
    int correct = 0;
    int wrong = 0;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://medicaltermapp-default-rtdb.europe-west1.firebasedatabase.app");
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        appView = getWindow().getDecorView();
        appView.setOnSystemUiVisibilityChangeListener(visibility -> {
            if (visibility ==  0){
                appView.setSystemUiVisibility(hideSystemBars());
            }
        });

        timer = findViewById(R.id.timer);
        qs = findViewById(R.id.questiontxt);
        optA = findViewById(R.id.optionA);
        optB = findViewById(R.id.optionB);
        optC = findViewById(R.id.optionC);
        optD = findViewById(R.id.optionD);

        quitBtn = findViewById(R.id.Quit);

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizActivity.this, MenuActivity.class));
            }
        });

        updateQuestion();
        reverseTimer(600, timer);


    }

    private void updateQuestion() {

        total++;
        if (total>61){
            Toast.makeText(QuizActivity.this,"error", Toast.LENGTH_LONG).show();
            Intent x = new Intent(QuizActivity.this, StatisticsActivity.class);

            x.putExtra("total",String.valueOf(total));
            x.putExtra("correct",String.valueOf(correct));
            x.putExtra("incorrect",String.valueOf(wrong));
            x.putExtra("cSystem", String.valueOf(cSystem));
            x.putExtra("state", String.valueOf(state));
            x.putExtra("speed", String.valueOf(speed));
            x.putExtra("direction", String.valueOf(direction));
            x.putExtra("location", String.valueOf(location));
            x.putExtra("range", String.valueOf(range));
            x.putExtra("amount", String.valueOf(amount));
            x.putExtra("procedure", String.valueOf(procedure));
            x.putExtra("opposites", String.valueOf(opposites));

            startActivity(x);

        }
        else{

            reference = FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Questions questions = dataSnapshot.getValue(Questions.class);


                    qs.setText(questions.getQuestion());
                    optA.setText(questions.getOptionA());
                    optB.setText(questions.getOptionB());
                    optC.setText(questions.getOptionC());
                    optD.setText(questions.getOptionD());



                    optA.setOnClickListener(v -> {
                        if(optA.getText().toString().equals(questions.getAnswer())){

                            Toast.makeText(getApplicationContext(),"that is correct!", Toast.LENGTH_SHORT).show();

                            optA.setBackgroundColor(Color.GREEN);

                            Handler handler = new Handler();
                            handler.postDelayed(() -> {
                                correct++;
                                optA.setBackgroundColor(Color.parseColor("#03A9F4"));


                                updateQuestion();

                            }, 1500);
                        }
                        else{
                            wrong++;
                            optA.setBackgroundColor(RED);
                            if (!optA.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("cardiovascular system")){
                                cSystem++;
                            }
                            if (!optA.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("state")){
                                state++;
                            }
                            if (!optA.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("speed/pace")){
                                speed++;
                            }
                            if (!optA.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("direction")){
                                direction++;
                            }
                            if (!optA.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("location")){
                                location++;
                            }
                            if (!optA.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("range")){
                                range++;
                            }
                            if (!optA.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("amount/quantity")){
                                amount++;
                            }
                            if (!optA.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("procedure")){
                                procedure++;
                            }
                            if (!optA.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("opposites")){
                                opposites++;
                            }

                            if(optB.getText().toString().equals(questions.getAnswer())){
                                optB.setBackgroundColor(GREEN);
                            }
                            else if(optC.getText().toString().equals(questions.getAnswer())){

                                optC.setBackgroundColor(GREEN);

                            }
                            else if(optD.getText().toString().equals(questions.getAnswer())){
                                optD.setBackgroundColor(GREEN);
                            }

                            Handler handler = new Handler();
                            handler.postDelayed(() -> {
                                optA.setBackgroundColor(Color.parseColor("#03A9F4"));
                                optB.setBackgroundColor(Color.parseColor("#03A9F4"));
                                optC.setBackgroundColor(Color.parseColor("#03A9F4"));
                                optD.setBackgroundColor(Color.parseColor("#03A9F4"));
                                updateQuestion();

                            }, 1500);
                        }


                    });
                    optB.setOnClickListener(v -> {
                        if(optB.getText().toString().equals(questions.getAnswer())){

                            Toast.makeText(getApplicationContext(),"that is correct!", Toast.LENGTH_SHORT).show();

                            optB.setBackgroundColor(Color.GREEN);

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    correct++;
                                    optB.setBackgroundColor(Color.parseColor("#03A9F4"));


                                    updateQuestion();

                                }
                            }, 1500);
                        }
                        else{
                            wrong++;
                            optB.setBackgroundColor(RED);
                            if (!optB.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("cardiovascular system")){
                                cSystem++;
                            }
                            if (!optB.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("state")){
                                state++;
                            }
                            if (!optB.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("speed/pace")){
                                speed++;
                            }
                            if (!optB.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("direction")){
                                direction++;
                            }
                            if (!optB.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("location")){
                                location++;
                            }
                            if (!optB.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("range")){
                                range++;
                            }
                            if (!optB.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("amount/quantity")){
                                amount++;
                            }
                            if (!optB.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("procedure")){
                                procedure++;
                            }
                            if (!optB.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("opposites")){
                                opposites++;
                            }

                            if(optA.getText().toString().equals(questions.getAnswer())){
                                optA.setBackgroundColor(GREEN);
                            }
                            else if(optC.getText().toString().equals(questions.getAnswer())){

                                optC.setBackgroundColor(GREEN);

                            }
                            else if(optD.getText().toString().equals(questions.getAnswer())){
                                optD.setBackgroundColor(GREEN);
                            }

                            Handler handler = new Handler();
                            handler.postDelayed(() -> {
                                optA.setBackgroundColor(Color.parseColor("#03A9F4"));
                                optB.setBackgroundColor(Color.parseColor("#03A9F4"));
                                optC.setBackgroundColor(Color.parseColor("#03A9F4"));
                                optD.setBackgroundColor(Color.parseColor("#03A9F4"));
                                updateQuestion();

                            }, 1500);
                        }


                    });
                    optC.setOnClickListener(v -> {
                        if(optC.getText().toString().equals(questions.getAnswer())){

                            Toast.makeText(getApplicationContext(),"that is correct!", Toast.LENGTH_SHORT).show();

                            optC.setBackgroundColor(Color.GREEN);

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    correct++;
                                    optC.setBackgroundColor(Color.parseColor("#03A9F4"));


                                    updateQuestion();

                                }
                            }, 1500);
                        }
                        else{
                            wrong++;
                            optC.setBackgroundColor(RED);
                            if (!optC.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("cardiovascular system")){
                                cSystem++;
                            }
                            if (!optC.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("state")){
                                state++;
                            }
                            if (!optC.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("speed/pace")){
                                speed++;
                            }
                            if (!optC.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("direction")){
                                direction++;
                            }
                            if (!optC.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("location")){
                                location++;
                            }
                            if (!optC.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("range")){
                                range++;
                            }
                            if (!optC.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("amount/quantity")){
                                amount++;
                            }
                            if (!optC.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("procedure")){
                                procedure++;
                            }
                            if (!optC.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("opposites")){
                                opposites++;
                            }

                            if(optB.getText().toString().equals(questions.getAnswer())){
                                optB.setBackgroundColor(GREEN);
                            }
                            else if(optA.getText().toString().equals(questions.getAnswer())){

                                optA.setBackgroundColor(GREEN);

                            }
                            else if(optD.getText().toString().equals(questions.getAnswer())){
                                optD.setBackgroundColor(GREEN);
                            }

                            Handler handler = new Handler();
                            handler.postDelayed(() -> {
                                optA.setBackgroundColor(Color.parseColor("#03A9F4"));
                                optB.setBackgroundColor(Color.parseColor("#03A9F4"));
                                optC.setBackgroundColor(Color.parseColor("#03A9F4"));
                                optD.setBackgroundColor(Color.parseColor("#03A9F4"));
                                updateQuestion();

                            }, 1500);
                        }


                    });
                    optD.setOnClickListener(v -> {
                        if(optD.getText().toString().equals(questions.getAnswer())){

                            Toast.makeText(getApplicationContext(),"that is correct!", Toast.LENGTH_SHORT).show();

                            optD.setBackgroundColor(Color.GREEN);

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    correct++;
                                    optA.setBackgroundColor(Color.parseColor("#03A9F4"));


                                    updateQuestion();

                                }
                            }, 1500);
                        }
                        else{
                            wrong++;
                            optD.setBackgroundColor(RED);
                            if (!optD.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("cardiovascular system")){
                                cSystem++;
                            }
                            if (!optD.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("state")){
                                state++;
                            }
                            if (!optD.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("speed/pace")){
                                speed++;
                            }
                            if (!optD.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("direction")){
                                direction++;
                            }
                            if (!optD.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("location")){
                                location++;
                            }
                            if (!optD.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("range")){
                                range++;
                            }
                            if (!optD.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("amount/quantity")){
                                amount++;
                            }
                            if (!optD.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("procedure")){
                                procedure++;
                            }
                            if (!optD.getText().toString().equals(questions.getAnswer()) && questions.getCategory().equals("opposites")){
                                opposites++;
                            }

                            if(optB.getText().toString().equals(questions.getAnswer())){
                                optB.setBackgroundColor(GREEN);
                            }
                            else if(optC.getText().toString().equals(questions.getAnswer())){

                                optC.setBackgroundColor(GREEN);

                            }
                            else if(optA.getText().toString().equals(questions.getAnswer())){
                                optA.setBackgroundColor(GREEN);
                            }

                            Handler handler = new Handler();
                            handler.postDelayed(() -> {
                                optA.setBackgroundColor(Color.parseColor("#03A9F4"));
                                optB.setBackgroundColor(Color.parseColor("#03A9F4"));
                                optC.setBackgroundColor(Color.parseColor("#03A9F4"));
                                optD.setBackgroundColor(Color.parseColor("#03A9F4"));
                                updateQuestion();

                            }, 1500);
                        }


                    });


                }

                @Override
                public void onCancelled(DatabaseError error) {

                    Toast.makeText(QuizActivity.this,"error", Toast.LENGTH_LONG).show();

                }
            });

        }

    }

    public void reverseTimer(int seconds, final TextView tv){

        new CountDownTimer(seconds* 1000+1000, 1000) {

            public void onTick (long millisUntilFinished) {

                int seconds = (int) millisUntilFinished / 1000;
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));

            }

            @Override
            public void onFinish() {
                tv.setText(("Completed"));
                Intent myIntent = new Intent (QuizActivity.this, StatisticsActivity.class);
                myIntent.putExtra("total", String.valueOf(total));
                myIntent.putExtra("correct", String.valueOf(correct));
                myIntent.putExtra("incorrect", String.valueOf(wrong));
                myIntent.putExtra("cSystem", String.valueOf(cSystem));
                myIntent.putExtra("state", String.valueOf(state));
                myIntent.putExtra("speed", String.valueOf(speed));
                myIntent.putExtra("direction", String.valueOf(direction));
                myIntent.putExtra("location", String.valueOf(location));
                myIntent.putExtra("range", String.valueOf(range));
                myIntent.putExtra("amount", String.valueOf(amount));
                myIntent.putExtra("procedure", String.valueOf(procedure));
                myIntent.putExtra("opposites", String.valueOf(opposites));
                startActivity(myIntent);
            };
        }.start();

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(hasFocus){
            appView.setSystemUiVisibility(hideSystemBars());
        }
    }

    private int hideSystemBars() {

        return
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }
}