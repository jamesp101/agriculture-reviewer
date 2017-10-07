package com.james.reviewer;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;


public class QuizActivity extends AppCompatActivity implements QuestionsAndAnswersFragment.OnFragmentInteractionListener{

    FragmentManager fragmentManager;
    LinkedList<QuestionsAndAnswersFragment> qnaFragment;

    Button btnNext, btnBack,btnPrev;

    String ans;

    DatabaseHandler database = LoginActivity.database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        fragmentManager = getSupportFragmentManager();

        qnaFragment.add( new  QuestionsAndAnswersFragment());

        fragmentManager.beginTransaction().replace(R.id.forFragment, qnaFragment.get(0)).commit();


        btnNext = (Button) findViewById(R.id.button_nextqustion);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        btnNext = (Button) findViewById(R.id.button_prevquestion);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnBack = (Button) findViewById(R.id.button_quizback);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }






    @Override
    public void onFragmentInteraction(String string) {
        ans = string;
    }
}

