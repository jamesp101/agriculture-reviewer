package com.james.reviewer.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.james.reviewer.DatabaseHandler;
import com.james.reviewer.Fragment.QuestionsAndAnswersFragment;
import com.james.reviewer.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;


//TODO add to tblExam and tblUsers

public class QuizActivity extends AppCompatActivity implements QuestionsAndAnswersFragment.OnFragmentInteractionListener {

    FragmentManager fragmentManager;

    LinkedList<QuestionsAndAnswersFragment> qnaFragmentList = new LinkedList<>();
    LinkedList <String> questionIdList = new LinkedList<>();
    LinkedList <String> answerList = new LinkedList<>();



    Button btnNext, btnBack,btnPrev;
    TextView txtCount;

    String queston,choice1,choice2,choice3,choice4;
    String ans;

    DatabaseHandler database = LoginActivity.database;



    Cursor c;

    int currentNo = 0;
    int examID = 0;
    int maxNo = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        fragmentManager = getSupportFragmentManager();

         c = database.GetRandomizedQuestion();


        NextQuestion();



        txtCount = (TextView) findViewById(R.id.txt_countCurrentNo);
        btnNext = (Button) findViewById(R.id.button_nextqustion);
        //btnPrev = (Button) findViewById(R.id.button_prevquestion);
       // btnBack = (Button) findViewById(R.id.button_quizback);


        QuestionsAndAnswersFragment trans = new QuestionsAndAnswersFragment();

        trans.SetQuestion(queston,choice1,choice2,choice3,choice4);
        qnaFragmentList.add(trans);
        fragmentManager.beginTransaction().replace(R.id.forFragment, qnaFragmentList.get(0)).commit();


        txtCount.setText(Integer.toString(currentNo) + "/" + maxNo);

    }


    public void PrevQuestionOnClick(View v){

    }

    public void NextQuestionOnClick(View v){


       // Finish();



            NextQuestion();



        currentNo++;

        if(currentNo > maxNo){

            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            database.AddExam(1,maxNo,LoginActivity.userID, currentDateTimeString);
            examID = database.getExamLastId(LoginActivity.userID);

            Log.wtf("wtf", examID+"xxxxxxxxxxxxxx");

            for(int a = 0; a < maxNo;a++){
                answerList.add(ans);
                database.AddAnswers(Integer.parseInt(questionIdList.get(a)), examID,answerList.get(a));

            }

            Intent intent = new Intent (getApplicationContext(), QuizResultsActivity.class);

            intent.putExtra("examID", examID+"");
            startActivity(intent);
            finish();
            return;
        }



        QuestionsAndAnswersFragment trans = new QuestionsAndAnswersFragment();
        trans.SetQuestion(queston,choice1,choice2,choice3,choice4);



        qnaFragmentList.add(trans);
        answerList.add(ans);

        fragmentManager.beginTransaction().replace(R.id.forFragment, trans).commit();


        txtCount.setText(Integer.toString(currentNo) + "/" + maxNo);
    }

    void Finish(){

    }

    void NextQuestion(){
//        if(c.getPosition() == 0){
//            c.moveToFirst();
//            c.moveToPosition(currentNo);
//          questionIdList.add(c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_ID)));
//            queston = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_DESC));
//            choice1 = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CHOICE1));
//            choice2 = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CHOICE2));
//            choice3 = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CHOICE3));
//            choice4 = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CHOICE4));
//            // correct = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CORRECTANS));
//        }
//        else{

            c.moveToNext( );
           questionIdList.add(c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_ID)));
            queston = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_DESC));
            choice1 = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CHOICE1));
            choice2 = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CHOICE2));
            choice3 = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CHOICE3));
            choice4 = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CHOICE4));
            // correct = c.getString(c.getColumnIndex(DatabaseHandler.QUESTION_CORRECTANS));
      //  }

    }







    @Override
    public void onFragmentInteraction(String string) {
        ans = string;
    }
}

