package com.james.reviewer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;


//TODO add to tblExam and tblUsers

public class QuizActivity extends AppCompatActivity implements QuestionsAndAnswersFragment.OnFragmentInteractionListener{

    FragmentManager fragmentManager;

    LinkedList<QuestionsAndAnswersFragment> qnaFragmentList = new LinkedList<>();
    LinkedList <String> questionIdList = new LinkedList<>();
    LinkedList <String> answerList = new LinkedList<>();



    Button btnNext, btnBack,btnPrev;
    TextView txtCount;

    Cursor c;

    String queston,choice1,choice2,choice3,choice4;

    String ans;

    DatabaseHandler database = LoginActivity.database;

    int currentNo = 0;
    int examID = 0;
    int maxNo = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);



        fragmentManager = getSupportFragmentManager();


         c = database.GetRandomizedQuestion();
        c.moveToFirst();
        NextQuestion();



        database.AddExam(1,maxNo,LoginActivity.userID);
        examID = database.getExamLastId(LoginActivity.userID);



        QuestionsAndAnswersFragment first = new QuestionsAndAnswersFragment();
        first.SetQuestion(queston,choice1,choice2,choice3,choice4);

        qnaFragmentList.add(first);

        fragmentManager.beginTransaction().replace(R.id.forFragment, qnaFragmentList.get(0)).commit();



        txtCount = (TextView) findViewById(R.id.text_count);
        btnNext = (Button) findViewById(R.id.button_nextqustion);
        btnPrev = (Button) findViewById(R.id.button_prevquestion);
        btnBack = (Button) findViewById(R.id.button_quizback);


        QuestionsAndAnswersFragment trans = new QuestionsAndAnswersFragment();
        trans.SetQuestion(queston,choice1,choice2,choice3,choice4);
        fragmentManager.beginTransaction().replace(R.id.forFragment, trans).commit();


    }


    public void PrevQuestionOnClick(View v){

    }

    public void NextQuestionOnClick(View v){
        currentNo++;
       // Finish();
        if(currentNo < 10){

            NextQuestion();


            QuestionsAndAnswersFragment trans = new QuestionsAndAnswersFragment();
            trans.SetQuestion(queston,choice1,choice2,choice3,choice4);

            answerList.add(ans);

            qnaFragmentList.add(trans);
            fragmentManager.beginTransaction().replace(R.id.forFragment, trans).commit();
        }else {
            for(int a = 0; a < answerList.size();a++){
                database.AddAnswers(Integer.parseInt(questionIdList.get(a)), examID,answerList.get(a));
                Log.w("log", questionIdList.get(a) + " " + examID + " " +answerList.get(a));
            }

            Intent intent = new Intent (getApplicationContext(), QuizResultsActivity.class);

            intent.putExtra("examID", examID);
            startActivity(intent);
            finish();
        }



    }

    void Finish(){

    }

    void NextQuestion(){
        if(c.getPosition() == 0){
            c.moveToFirst();
            c.moveToPosition(currentNo);
          questionIdList.add(c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_ID)));
            queston = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_DESC));
            choice1 = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CHOICE1));
            choice2 = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CHOICE2));
            choice3 = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CHOICE3));
            choice4 = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CHOICE4));
            // correct = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CORRECTANS));
        }
        else{

            c.moveToNext( );
           questionIdList.add(c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_ID)));
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

