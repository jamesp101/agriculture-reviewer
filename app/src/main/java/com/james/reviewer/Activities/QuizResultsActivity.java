package com.james.reviewer.Activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.james.reviewer.Adapters.ItemAnsweredAdapter;
import com.james.reviewer.DatabaseHandler;
import com.james.reviewer.R;

import java.util.LinkedList;

public class QuizResultsActivity extends AppCompatActivity {



    String examId;

    Button button;
    ListView listView;

    DatabaseHandler database = LoginActivity.database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        button = (Button) findViewById(R.id.button_z);
        listView = (ListView)findViewById(R.id.list_answered);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

       // Cursor c = database.GetUser(LoginActivity.userID);
     //   String s = c.getString(0);



        examId = getIntent().getExtras().getString("examID");
        SetList();

     }


     void SetList(){
         Cursor cursor = database.getAnsweredList(Integer.parseInt(examId));
         String temp = database.GetUserByExamId(examId);
         String username = database.GetUserByExamId(temp);
         String points = ""+database.GetTotalCorrectAns(Integer.parseInt(examId));
         TextView textView = (TextView) findViewById(R.id.txt_results);

         textView.setText(username + "\t" + points);



         LinkedList<String> questionList = new LinkedList<>();
         LinkedList<String> answeredList = new LinkedList<>();
         LinkedList<String> correctList = new LinkedList<>();

         while(cursor.moveToNext()){
             questionList.add(cursor.getString(0));
             answeredList.add(cursor.getString(1));
             correctList.add(cursor.getString(2));
         }

         String[] questionArray = new String[questionList.size()];
         String[] answeredArray = new String[answeredList.size()];
         String[] correctArray = new String[correctList.size()];

/*
         String[] questionArray = {"x","1"};
         String[] answeredArray = {"y","2"};
         String[] correctArray = {"z","3"};
         */
            questionArray = questionList.toArray(questionArray);
          answeredArray = answeredList.toArray(answeredArray);
            correctArray = correctList.toArray(correctArray);

         for(int a = 0 ; a < questionArray.length; a++){
             Log.w ("array " , questionArray[a] + " - " +answeredArray[a] + " - " + correctArray[a]);
         }


         ItemAnsweredAdapter adapter = new ItemAnsweredAdapter(this,questionArray,correctArray,answeredArray);



         listView.setAdapter(adapter);


        // String[] questionArray = new String[questionList.size()];

     }

}
