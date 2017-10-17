package com.james.reviewer.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.james.reviewer.Adapters.QuestionEditorAdapter;
import com.james.reviewer.DatabaseHandler;
import com.james.reviewer.R;

import java.util.LinkedList;

public class ManageQuestionsActivity extends AppCompatActivity {


    ListView list;
    FloatingActionButton fab;

    DatabaseHandler database = LoginActivity.database;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_questions);


        list = (ListView) findViewById(R.id.list_questions);
        fab = (FloatingActionButton) findViewById(R.id.fab_addquestion);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuestionEditorActivity.class);
                intent.putExtra("editMode", "false");



                startActivity(intent);
            }
        });



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), QuestionEditorActivity.class);
                intent.putExtra("editMode", "true");
                intent.putExtra("id", Long.toString(l));
                startActivity(intent);
                finish();
            }
        });
        SetQuestions();
    }


    private void SetQuestions(){

        cursor= database.GetQuestionList();

        LinkedList<String> questionId = new LinkedList<>();
        LinkedList<String> questionDesc = new LinkedList<>();
        LinkedList<String> choice1  = new LinkedList<>() ;
        LinkedList<String> choice2  = new LinkedList<>();
        LinkedList<String> choice3  = new LinkedList<>();
        LinkedList<String> choice4 = new LinkedList<>();
        LinkedList<String> correctAns = new LinkedList<>();

        for(int a =0; cursor.moveToNext() ; a++){
            questionId.add(cursor.getString(0));
            questionDesc.add(cursor.getString(1));
            choice1.add(cursor.getString(2));
            choice2.add(cursor.getString(3));
            choice3.add(cursor.getString(4));
            choice4.add(cursor.getString(5));
            correctAns.add(cursor.getString(6));


        }

        QuestionEditorAdapter adapter = new QuestionEditorAdapter(this, questionId,questionDesc,choice1,choice2,choice3,choice4,correctAns);

        list.setAdapter(adapter);

    }


}
