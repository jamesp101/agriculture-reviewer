package com.james.reviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainMenuActivity extends AppCompatActivity  {


    ImageButton btnStartQuiz;
    Button btnRecords;
    Button btnSchedules;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnStartQuiz = (ImageButton) findViewById(R.id.btn_startquiz);
        btnRecords = (Button) findViewById(R.id.btn_viewRec);
        btnSchedules = (Button) findViewById(R.id.btn_viewSched);




        btnSchedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), QuizActivity.class));

            }
        });
    }
}
