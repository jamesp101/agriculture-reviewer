package com.james.reviewer;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;


//TODO add to tblExam and tblUsers

public class QuizActivity extends AppCompatActivity implements QuestionsAndAnswersFragment.OnFragmentInteractionListener{

    FragmentManager fragmentManager;
    LinkedList<QuestionsAndAnswersFragment> qnaFragmentList = new LinkedList<>();
    LinkedList <String> id = new LinkedList<>();
    Button btnNext, btnBack,btnPrev;
    TextView txtCount;

    Cursor c;

    String queston,choice1,choice2,choice3,choice4;

    String ans;

    DatabaseHandler database = LoginActivity.database;

    int currentNo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);



        fragmentManager = getSupportFragmentManager();


         c = database.GetRandomizedQuestion();
        c.moveToFirst();
        NextQuestion();

        database.AddExam(1,10,LoginActivity.userID);




        QuestionsAndAnswersFragment first = new QuestionsAndAnswersFragment();
        first.SetQuestion(queston,choice1,choice2,choice3,choice4);

        qnaFragmentList.add(first);

        fragmentManager.beginTransaction().replace(R.id.forFragment, qnaFragmentList.get(0)).commit();



        txtCount = (TextView) findViewById(R.id.text_count);
        btnNext = (Button) findViewById(R.id.button_nextqustion);
        btnPrev = (Button) findViewById(R.id.button_prevquestion);
        btnBack = (Button) findViewById(R.id.button_quizback);


        currentNo = 1;
        QuestionsAndAnswersFragment trans = new QuestionsAndAnswersFragment();
        trans.SetQuestion(queston,choice1,choice2,choice3,choice4);
        fragmentManager.beginTransaction().replace(R.id.forFragment, trans).commit();


    }


    public void PrevQuestionOnClick(View v){

    }

    public void NextQuestionOnClick(View v){
        NextQuestion();
        currentNo++;

        System.out.println(ans);
        QuestionsAndAnswersFragment trans = new QuestionsAndAnswersFragment();
        trans.SetQuestion(queston,choice1,choice2,choice3,choice4);

    //TODO add to tblAnswers

        qnaFragmentList.add(trans);
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
        }




    }


    @Override
    public void onFragmentInteraction(String string) {
        ans = string;
    }
}

