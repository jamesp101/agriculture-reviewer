package com.james.reviewer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by james on 10/6/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "db_Reviewer.db";

    public static final String TBL_USERS  = "tblUsers";
    public static final String TBL_QUESTIONS = "tblQuestions";
    public static final String TBL_ANSWERS = "tblAnswers";
    public static final String TBL_EXAMS = "tblExams";

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
    public static final String ANSWER_ANSWERED = "answered";

    public static final String EXAMS_ID = "exam_id";
    public static final String EXAMS_STATUS = "status";
    public static final String EXAMS_QUESTNO = "number_of_questions";

    public static final int DB_VER = 1;


    private SQLiteDatabase database;

    public DatabaseHandler(Context context){
        super(context, DB_NAME, null, DB_VER);
        database = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE "+ TBL_USERS + "(" +
                        USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        USER_NAME + " TEXT NOT NULL, " +
                        USER_PASS + " TEXT NOT NULL )");

        database.execSQL("CREATE TABLE "+ TBL_ANSWERS + "(" +
                ANSWER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                QUESTION_ID + " INTEGER NOT NULL, " +
                USER_PASS + " INTEGER NOT NULL," +
                EXAMS_ID + "INTEGER NOT NULL," +
                ANSWER_ANSWERED + "TEXT )");

        database.execSQL("CREATE TABLE "+ TBL_QUESTIONS + "(" +
                QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                QUESTION_DESC + " TEXT, " +
                QUESTION_CHOICE1 + " TEXT, " +
                QUESTION_CHOICE2 + " TEXT, " +
                QUESTION_CHOICE3 + " TEXT, " +
                QUESTION_CHOICE4 + " TEXT, " +
                QUESTION_CORRECTANS + " TEXT )");

        database.execSQL("CREATE TABLE "+ TBL_EXAMS+ "(" +
                EXAMS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                USER_ID + " INTEGER, " +
                EXAMS_STATUS + " INTEGER, " +
                EXAMS_QUESTNO + "INTEGER )");
    }


    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {

        database.execSQL("DELETE TABLE IF EXIST " + TBL_QUESTIONS);
        database.execSQL("DELETE TABLE IF EXIST " + TBL_USERS);
        database.execSQL("DELETE TABLE IF EXIST " + TBL_EXAMS);
        database.execSQL("DELETE TABLE IF EXIST " + TBL_ANSWERS);
        onCreate(database);
    }


    public void AddUser(String user, String pass){
        database.execSQL("INSERT INTO " + TBL_USERS + " (" +
                        USER_NAME + "," + USER_PASS + ") VALUES " +
                        "('" + user + "', '" + pass + "')" );

        /*
        ContentValues cv = new ContentValues();
        cv.put(user, USER_NAME);
        cv.put(pass, USER_PASS);
        database.insert(TBL_USERS, null , cv);
*/
    }

    public void DeleteUser(String id){

        database.execSQL("DELETE FROM " + TBL_USERS + "WHERE " + USER_ID + " = " + id );
    }

    public void UpdateUser(String id,String name, String pass){

        ContentValues cv = new ContentValues();
        cv.put(name, USER_NAME);
        cv.put(pass, USER_PASS);
        database.update(TBL_USERS,cv, TBL_QUESTIONS + "=" + id, null);
    }

    // TODO to be added; table_exam to be added
    public void NewExam(){

    }

    // TODO PROTOTYPE to be changed
    public void AddAnswers(String ans){
        ContentValues cv = new ContentValues();
        cv.put(ans,TBL_ANSWERS);

        //TODO to be removed/changed
        cv.put(Integer.toString(1),QUESTION_ID);
        cv.put(Integer.toString(2),QUESTION_ID);
        cv.put(Integer.toString(3),QUESTION_ID);

        cv.put(Integer.toString(1),EXAMS_ID);
        database.insert(TBL_ANSWERS, null , cv);

    }

    public void addQuestions(String question, String choice1, String choice2, String choice3,String choice4, String correctAns ){
        database.execSQL("INSERT INTO " + TBL_QUESTIONS + " ("
                            + QUESTION_DESC + ", " + QUESTION_CHOICE1 + ", " + QUESTION_CHOICE2 + ", " + QUESTION_CHOICE3 + ", " + QUESTION_CHOICE4 + ", " + QUESTION_CORRECTANS +  ") VALUES " +
                            "('" + question + "', '" + choice1 + "' , '"+ choice2+ "' , '" + choice3 + "', '" + choice4 + "', '" + correctAns + "')");
        /*
        ContentValues cv = new ContentValues();
        cv.put(question,QUESTION_DESC);
        cv.put(choice1,QUESTION_CHOICE1);
        cv.put(choice2,QUESTION_CHOICE2);
        cv.put(choice3,QUESTION_CHOICE3);
        cv.put(choice4,QUESTION_CHOICE4);
        cv.put(correctAns,QUESTION_CORRECTANS);
        database.insert(TBL_QUESTIONS, null , cv);
        */

    }
    public Cursor GetAnswers(){
        return database.rawQuery("SELECT * FROM " + TBL_ANSWERS +
                                "INNER JOIN " + TBL_QUESTIONS + " ON " +
                                TBL_ANSWERS+ "." + QUESTION_ID + "=" + TBL_QUESTIONS + "." + QUESTION_ID +
                                "WHERE " + TBL_ANSWERS + "." + ANSWER_ID + "=" + TBL_QUESTIONS + "." + ANSWER_ANSWERED ,null);
    }

    public Cursor Login(String user, String pass){
        return  database.rawQuery("SELECT * " +
                " FROM " + TBL_USERS +
                " WHERE " + USER_NAME + " = '" + user +  "' AND " +
                            USER_PASS +" = '" + pass + "'" ,null);
    }

    public Cursor GetRandomizedQuestion(){
        return  database.rawQuery("SELECT * FROM "+ TBL_QUESTIONS + " ORDER BY RANDOM()",null);
    }

    public void AddExam(){

    }




}