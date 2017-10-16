package com.james.reviewer.Activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import com.james.reviewer.DatabaseHandler;
import com.james.reviewer.Adapters.ItemAnsweredAdapter;
import com.james.reviewer.R;

import java.util.LinkedList;

public class QuizResultsActivity extends AppCompatActivity {



    int examId;

    Button button;
    ListView listView;

    DatabaseHandler database = LoginActivity.database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        button = (Button) findViewById(R.id.button_z);
        listView = (ListView)findViewById(R.id.list_answered);





       // Cursor c = database.GetUser(LoginActivity.userID);
     //   String s = c.getString(0);



        examId = getIntent().getExtras().getInt("examID");
        SetList();

     }


     void SetList(){
         Cursor cursor = database.getAnsweredList(examId);
         cursor.moveToFirst();


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


         ItemAnsweredAdapter adapter = new ItemAnsweredAdapter(this,questionArray,answeredArray,correctArray);



         listView.setAdapter(adapter);


        // String[] questionArray = new String[questionList.size()];

     }

}
