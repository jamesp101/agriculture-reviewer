package com.james.reviewer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    DatabaseHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        try{
            database = new DatabaseHandler(this);

            database.AddUser("Hello" , "World");

            System.out.println("<DEBUG> USER CREATED!");

            Cursor c =   database.GeUsers("Hello" , "World");
            System.out.println(c.getColumnNames());
            c.moveToFirst();
            String s = c.getString(c.getColumnIndex(DatabaseHandler.USER_NAME));

            System.out.println("<DEBUG> USER FOUND! " + s);

        }catch (Exception e){
            System.out.println("<DEBUG> "+e);
        }

    }
}
