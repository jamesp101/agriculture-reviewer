package com.james.reviewer.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.james.reviewer.R;

public class AdminPanelActivity extends AppCompatActivity {



    ImageButton btnManageQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        btnManageQuestion = (ImageButton) findViewById(R.id.btnManageQuestions);
        btnManageQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ManageQuestionsActivity.class));
            }
        });

    }
}
