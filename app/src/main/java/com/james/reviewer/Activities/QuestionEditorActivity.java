package com.james.reviewer.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.james.reviewer.DatabaseHandler;
import com.james.reviewer.R;

public class QuestionEditorActivity extends AppCompatActivity {

    Spinner spinner;

    String id;

    boolean editMode;
    DatabaseHandler database = LoginActivity.database;



    TextView txtQuestion;
    TextView choice1;
    TextView choice2;
    TextView choice3;
    TextView choice4;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_editor);



        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String e = getIntent().getExtras().getString("editMode");
        if(e.equals("true")){
            editMode = true;


        }else if(e.equals("false")){
            editMode = false;
        }

        spinner = (Spinner) findViewById(R.id.spinner_correctAns);
        String ans [] = {"A" ,"B", "C", "D"};
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,ans);
        spinner.setAdapter(arrayAdapter);

         txtQuestion = (TextView) findViewById(R.id.edit_questions);
         choice1 = (TextView) findViewById(R.id.edit_choice1);
         choice2 = (TextView) findViewById(R.id.edit_choice2);
         choice3 = (TextView) findViewById(R.id.edit_choice3);
         choice4 = (TextView) findViewById(R.id.edit_choice4);






    }

    public boolean onOptionsItemSelected(MenuItem item){
        int getId = item.getItemId();

        String question = txtQuestion.getText().toString();
        String c1 = choice1.getText().toString();
        String c2 = choice2.getText().toString();
        String c3 = choice3.getText().toString();
        String c4 = choice4.getText().toString();

        String temp = spinner.getSelectedItem().toString();
        String correctAns = "";
        if(temp.equals("A")){
            correctAns  = c1;
        }else if(temp.equals("B")){
            correctAns  = c2;
        }else if(temp.equals("C")){
            correctAns  = c3;
        }else if(temp.equals("D")){
            correctAns  = c4;
        }

        switch (getId){
            default:{
                finish();
                break;
            }
            case R.id.nav_newQuestion:{
                database.addQuestions(question, c1, c2, c3 ,c4,correctAns);
                this.finish();
                Toast.makeText(this, "Question added", Toast.LENGTH_SHORT).show();
                startActivity( new Intent( getApplicationContext() , ManageQuestionsActivity.class));
                break;
            }case R.id.nav_deletQuestion:{


                database.DeleteQuestions(id);
                finish();
                startActivity( new Intent( getApplicationContext() , ManageQuestionsActivity.class));
                Toast.makeText(QuestionEditorActivity.this, "Question Deleted", Toast.LENGTH_SHORT).show();

                break;
            }case R.id.nav_saveQuestion:{
                database.UpdateQuestions(id , question, c1, c2, c3 ,c4,correctAns);
                Toast.makeText(this, "Question Updated", Toast.LENGTH_SHORT).show();
                startActivity( new Intent( getApplicationContext() , ManageQuestionsActivity.class));
                this.finish();
                break;
            }
        }
        return true;
    }
    public boolean onCreateOptionsMenu(Menu m){
        if(editMode){
            id = getIntent().getExtras().getString("id");
            Cursor cursor = database.GetQuestionById(id);
            cursor.moveToNext();
            String q = cursor.getString(1);
            String c1 = cursor.getString(2);
            String c2 = cursor.getString(3);
            String c3 = cursor.getString(4);
            String c4 = cursor.getString(5);
            String correctAns = cursor.getString(6);

            txtQuestion.setText(q);
            choice1.setText(c1);
            choice2.setText(c2);
            choice3.setText(c3);
            choice4.setText(c4);


            int spinnerSelection = 0;
            if(correctAns.equals("A") || correctAns.equals("a")){
                spinnerSelection = 0;
            }else if(correctAns.equals("B") || correctAns.equals("b")){
                spinnerSelection = 1;
            }
            else if(correctAns.equals("C") || correctAns.equals("c")){
                spinnerSelection = 2;
            }
            else if(correctAns.equals("D") || correctAns.equals("d")){
                spinnerSelection = 3;
            }
            spinner.setSelection(spinnerSelection);



            getMenuInflater().inflate(R.menu.question_editor_edit,m);
        }else if(!editMode){
            getMenuInflater().inflate(R.menu.question_editor_new,m);
        }
        return true;
    }


}
