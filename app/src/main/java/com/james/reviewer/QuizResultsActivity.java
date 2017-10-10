package com.james.reviewer;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

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
        examId = getIntent().getExtras().getInt("examID");
        listView = (ListView)findViewById(R.id.list_answered);

        String[] questionArray = {"Hello","World"};
        //String[] answeredArray = new String[answeredList.size()];
        String[] answeredArray = {"World", "Hello"};
        // String[] correctArray = new String[correctList.size()];
        String[] correctArray = {"FOo" , "worldt"};



        //   questionArray = questionList.toArray(questionArray);
        // answeredArray = answeredList.toArray(answeredArray);
        //   correctArray = answeredList.toArray(correctArray);


        ItemAnsweredAdapter adapter = new ItemAnsweredAdapter(this,questionArray,answeredArray,correctArray);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        SetList();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetList();
            }
        });
     }


     void SetList(){
         Cursor cursor = database.getAnsweredList(examId);
         cursor.moveToFirst();
/*

         LinkedList<String> questionList = new LinkedList<>();
         LinkedList<String> answeredList = new LinkedList<>();
         LinkedList<String> correctList = new LinkedList<>();

         while(cursor.moveToNext()){
             questionList.add(cursor.getString(0));
             answeredList.add(cursor.getString(1));
             correctList.add(cursor.getString(2));
         }
         */

        // String[] questionArray = new String[questionList.size()];

     }

}
