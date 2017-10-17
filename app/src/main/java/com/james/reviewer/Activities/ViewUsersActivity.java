package com.james.reviewer.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.james.reviewer.Adapters.UsersItems;
import com.james.reviewer.DatabaseHandler;
import com.james.reviewer.R;

import java.util.LinkedList;

public class ViewUsersActivity extends AppCompatActivity {

    ListView listview;
    DatabaseHandler database = LoginActivity.database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);



        listview = (ListView) findViewById(R.id.list_users);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent (getApplicationContext(), RecordsActivity.class);
                intent.putExtra("userId" , l + "");
                startActivity(intent);
            }
        });


        Cursor c = database.GetUserList();
        LinkedList<String> id  = new LinkedList<>() ;
        LinkedList<String> username = new LinkedList<>();

        while(c.moveToNext()){
           id.add(c.getString(0));
            username.add(c.getString(1));
        }

        UsersItems usersItems = new UsersItems(this, id , username);
        listview.setAdapter(usersItems);
    }



}



