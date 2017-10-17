package com.james.reviewer.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.james.reviewer.Adapters.RecordsItem;
import com.james.reviewer.DatabaseHandler;
import com.james.reviewer.R;

import java.util.LinkedList;

public class RecordsActivity extends AppCompatActivity {


    DatabaseHandler database = LoginActivity.database;
    String userid;




    Cursor data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records2);

        userid = getIntent().getExtras().getString("userId");


        ListRecords();


    }

    void ListRecords(){

        data = database.GetUserRecords(Integer.parseInt(userid));

                LinkedList <String> id = new LinkedList<>();
        LinkedList <String> date = new LinkedList<>();
        LinkedList<String > score = new LinkedList<>();

        for (int x = 0; data.moveToNext(); x++){
            id.add(data.getString(0));
            date.add(data.getString(4));


        }


        ListView listView = (ListView) findViewById(R.id.list_records);


        RecordsItem recordsItem = new RecordsItem(this,id, date);


        listView.setAdapter(recordsItem);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent (getApplicationContext(), QuizResultsActivity.class);
                intent.putExtra("examID",i+"");
                startActivity(intent);

            }
        });
    }
}
