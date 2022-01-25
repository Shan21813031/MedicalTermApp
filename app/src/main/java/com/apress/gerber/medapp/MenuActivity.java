package com.apress.gerber.medapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {

    private View appView;
    ImageButton studyImageButton;
    ImageButton quizImageButton;
    Button quitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        appView = getWindow().getDecorView();
        appView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility ==  0){
                    appView.setSystemUiVisibility(hideSystemBars());
                }
                quizImageButton.setImageResource(R.drawable.qbtn);
                studyImageButton.setImageResource(R.drawable.sbtn);
            }
        });

    //    study = findViewById(R.id.learn);
    //    quiz = findViewById(R.id.quiz);

        studyImageButton = (ImageButton) findViewById(R.id.learn);
        studyImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadStudy = new Intent(MenuActivity.this, StudyActivity.class);
                studyImageButton.setImageResource(R.drawable.study);
                startActivity(intentLoadStudy);
            }
        });

        quizImageButton = (ImageButton) findViewById(R.id.quiz);
        quizImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadQuiz = new Intent(MenuActivity.this, QuizActivity.class);
                quizImageButton.setImageResource(R.drawable.quiz);
                startActivity(intentLoadQuiz);
            }
        });

       /* quiz.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(firstImage)
                {
                    quiz.setImageResource(R.drawable.quiz);
                    startActivity(new Intent(MenuActivity.this, QuizActivity.class));
                    firstImage = false;
                }
                else{
                    quiz.setImageResource(R.drawable.qbtn);
                    firstImage = true;

                }
                return true;
            }
        }); */

        quitBtn = findViewById(R.id.Return);
        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
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