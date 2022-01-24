package com.apress.gerber.medapp;

import static android.graphics.Color.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
    Button optA, optB, optC, optD;
    int total = 1;
    int correct = 0;
    int wrong = 0;

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

        updateQuestion();


    }

    public void updateQuestion() {

        total++;
        if (total>27){

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