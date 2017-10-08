package com.james.reviewer;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;


public class QuizActivity extends AppCompatActivity implements QuestionsAndAnswersFragment.OnFragmentInteractionListener{

    FragmentManager fragmentManager;
    LinkedList<QuestionsAndAnswersFragment> qnaFragment = new LinkedList<>();
    LinkedList <String> id = new LinkedList<>();
    Button btnNext, btnBack,btnPrev;

    Cursor c;

    String queston,choice1,choice2,choice3,choice4;

    String ans;

    DatabaseHandler database = LoginActivity.database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        fragmentManager = getSupportFragmentManager();


         c = database.GetRandomizedQuestion();
        c.moveToFirst();
        NextQuestion();




        QuestionsAndAnswersFragment first = new QuestionsAndAnswersFragment();
        first.SetQuestion(queston,choice1,choice2,choice3,choice4);

        qnaFragment.add(first);

        fragmentManager.beginTransaction().replace(R.id.forFragment, qnaFragment.get(0)).commit();



        btnNext = (Button) findViewById(R.id.button_nextqustion);

        btnNext = (Button) findViewById(R.id.button_prevquestion);

        btnBack = (Button) findViewById(R.id.button_quizback);


        QuestionsAndAnswersFragment trans = new QuestionsAndAnswersFragment();
        trans.SetQuestion(queston,choice1,choice2,choice3,choice4);
        fragmentManager.beginTransaction().replace(R.id.forFragment, trans).commit();


    }


    public void PrevQuestionOnClick(View v){

    }

    public void NextQuestionOnClick(View v){
        NextQuestion();

        QuestionsAndAnswersFragment trans = new QuestionsAndAnswersFragment();
        trans.SetQuestion(queston,choice1,choice2,choice3,choice4);
        fragmentManager.beginTransaction().replace(R.id.forFragment, trans).commit();
    }

    void NextQuestion(){
        if(c.getPosition() == 0){
            c.moveToFirst();
        }
        if(c.getPosition() < c.getCount()){
            id.add(c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_ID)));
            queston = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_DESC));
            choice1 = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CHOICE1));
            choice2 = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CHOICE2));
            choice3 = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CHOICE3));
            choice4 = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CHOICE4));
            // correct = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CORRECTANS));
            c.moveToNext();
        }




    }


    @Override
    public void onFragmentInteraction(String string) {
        ans = string;
    }
}

