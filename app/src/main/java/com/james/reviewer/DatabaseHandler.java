package com.james.reviewer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by james on 10/6/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "db_Reviewer.db";

    public static final String TBL_USERS  = "tblUsers";
    public static final String TBL_QUESTIONs= "tblQuesitons";
    public static final String TBL_ANSWERS = "tblAnswers";
    public static final String TBL_EXAMs = "tblExams";

    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "name";
    public static final String USER_PASS = "password";

    public static final String QUESTION_ID = "question_id";
    public static final String QUESTION_DESC = "description";
    public static final String QUESTION_CHOICE1 = "choice1";
    public static final String QUESTION_CHOICE2 = "choice2";
    public static final String QUESTION_CHOICE3 = "choice3";
    public static final String QUESTION_CHOICE4 = "choice4";
    public static final String QUESTION_CORRECTANS = "correct_ans";

    public static final String ANSWER_ID = "answer_id";
    public static final String ANSWERD_ANSWERED = "answered";

    public static final String EXAMS_ID = "exam_id";
    public static final String EXAMS_STATUS = "status";
    public static final String EXAMS_




    private SQLiteDatabase database;

    public DatabaseHandler(Context context){
        super(context, DB_NAME, null, DB_VER);
        database = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
