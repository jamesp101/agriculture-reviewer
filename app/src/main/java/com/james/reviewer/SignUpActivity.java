package com.james.reviewer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText editUser, editPass, editPassConfirm;
    TextView textError;
    Button btnConfirm, btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editUser = (EditText) findViewById(R.id.edit_addusername);
        editPass = (EditText)findViewById(R.id.edit_addpassword);
        editPassConfirm = (EditText) findViewById(R.id.edit_addpasswordconfirm);

        textError = (TextView) findViewById(R.id.text_error);
        btnBack = (Button) findViewById(R.id.button_signupback);
        btnConfirm = (Button) findViewById(R.id.button_confirm);

        textError.setVisibility(View.INVISIBLE);


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editPass.getText().toString().equals(editPassConfirm.getText().toString())){
                    LoginActivity.database.AddUser(editUser.getText().toString(), editPass.getText().toString());
                    finish();
                    Toast.makeText(SignUpActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                }else{
                    textError.setEnabled(true);
                    textError.setVisibility(View.VISIBLE);
                }
            }
        });
    }


}
