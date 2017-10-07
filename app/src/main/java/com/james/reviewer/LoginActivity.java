package com.james.reviewer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static DatabaseHandler database;

    Button btnLogin;
    Button btnCreateUser;

    EditText editUser, editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnLogin = (Button) findViewById(R.id.button_login);
        btnCreateUser = (Button) findViewById(R.id.button_createUser);

        editUser = (EditText) findViewById(R.id.edit_addusername);
        editPass = (EditText) findViewById(R.id.edit_password);

        database = new DatabaseHandler(this);


        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Cursor cursor = database.Login(editUser.getText().toString(), editPass.getText().toString());
                    cursor.moveToFirst();
                    if(cursor.getCount() != 0){
                        startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Error Username or Password", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    System.out.println("<DEBUG> "+ e.toString());
                }

            }
        });



    }
}
