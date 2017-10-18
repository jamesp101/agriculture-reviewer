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
    public static final String USER_ISADMIN = "isAdmin";


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
    public static final String EXAMS_QUESTNO = "numberOfQuestions";
    public static final String EXAMS_DATE = "date";
    public static final String EXAMS_TIME = "exam_time";


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
                        USER_PASS + " TEXT NOT NULL ," +
                        USER_ISADMIN + " TEXT )" );

        database.execSQL("CREATE TABLE "+ TBL_ANSWERS + "(" +
                ANSWER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                QUESTION_ID + " INTEGER NOT NULL, " +
                EXAMS_ID + " INTEGER NOT NULL," +
                ANSWER_ANSWERED + " TEXT )");

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
                EXAMS_QUESTNO + " INTEGER, "+
                EXAMS_STATUS + " INTEGER," +
                EXAMS_DATE + " TEXT ," +
                EXAMS_TIME + "TEXT )");

        SetUp(database);
    }
    void SetUp(SQLiteDatabase db){

        this.AddUser("admin","admin" ,"t",db);
        this.addQuestions("How many stomachs do ruminant animals such as cattle and sheep have?" ,"1","2","3","4","4" ,db);
        this.addQuestions("Which of the following properties of a soil is determined by the relative proportion of sand, silt and clay?" ,"Porosity","Structure","Texture","Bulk density","Texture" ,db);
        this.addQuestions("Horticulturists often use a product called rooting powder or gel to stimulate cuttings to produce roots. What type of plant hormone is found in this product? " ,"Abscisic acid","Auxin","Cytokinin","Ethylene","Auxin" ,db );
        this.addQuestions("Horticulturists often use a product called rooting powder or gel to stimulate cuttings to produce roots. What type of plant hormone is found in this product? " ,"Abscisic acid","Auxin","Cytokinin","Ethylene","Auxin" ,db );
        this.addQuestions("Horticulturists often use a product called rooting powder or gel to stimulate cuttings to produce roots. What type of plant hormone is found in this product? " ,"Abscisic acid","Auxin","Cytokinin","Ethylene","Auxin" ,db );
        this.addQuestions("Horticulturists often use a product called rooting powder or gel to stimulate cuttings to produce roots. What type of plant hormone is found in this product? " ,"Abscisic acid","Auxin","Cytokinin","Ethylene","Auxin" ,db );
        this.addQuestions("Horticulturists often use a product called rooting powder or gel to stimulate cuttings to produce roots. What type of plant hormone is found in this product? " ,"Abscisic acid","Auxin","Cytokinin","Ethylene","Auxin" ,db );
        this.addQuestions("Horticulturists often use a product called rooting powder or gel to stimulate cuttings to produce roots. What type of plant hormone is found in this product? " ,"Abscisic acid","Auxin","Cytokinin","Ethylene","Auxin" ,db );
        this.addQuestions("Horticulturists often use a product called rooting powder or gel to stimulate cuttings to produce roots. What type of plant hormone is found in this product? " ,"Abscisic acid","Auxin","Cytokinin","Ethylene","Auxin" ,db );
        this.addQuestions("Horticulturists often use a product called rooting powder or gel to stimulate cuttings to produce roots. What type of plant hormone is found in this product? " ,"Abscisic acid","Auxin","Cytokinin","Ethylene","Auxin" ,db );
        this.addQuestions("Horticulturists often use a product called rooting powder or gel to stimulate cuttings to produce roots. What type of plant hormone is found in this product? " ,"Abscisic acid","Auxin","Cytokinin","Ethylene","Auxin" ,db );
        this.addQuestions("Horticulturists often use a product called rooting powder or gel to stimulate cuttings to produce roots. What type of plant hormone is found in this product? " ,"Abscisic acid","Auxin","Cytokinin","Ethylene","Auxin" ,db );
    }


    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {

        database.execSQL("DROP TABLE IF EXIST " + TBL_QUESTIONS);
        database.execSQL("DROP TABLE IF EXIST " + TBL_USERS);
        database.execSQL("DROP TABLE IF EXIST " + TBL_EXAMS);
        database.execSQL("DROP TABLE IF EXIST " + TBL_ANSWERS);
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
        database.insert(TBL_USERS, null , cv);TBL_QUESTIONS
*/
    }


    public void AddUser(String user, String pass,String admin){
        database.execSQL("INSERT INTO " + TBL_USERS + " (" +
                USER_NAME + "," + USER_PASS + "," + USER_ISADMIN + ") VALUES " +
                "('" + user + "', '" + pass + "', '" + admin+ "')" );


        /*
        ContentValues cv = new ContentValues();
        cv.put(user, USER_NAME);
        cv.put(pass, USER_PASS);
        database.insert(TBL_USERS, null , cv);TBL_QUESTIONS
*/
    }

    public void AddUser(String user, String pass,String admin, SQLiteDatabase db){
        db.execSQL("INSERT INTO " + TBL_USERS + " (" +
                USER_NAME + "," + USER_PASS + "," + USER_ISADMIN + ") VALUES " +
                "('" + user + "', '" + pass + "', '" + admin+ "')" );


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


    public Cursor Login(String user, String pass){
        return  database.rawQuery("SELECT * " +
                " FROM " + TBL_USERS +
                " WHERE " + USER_NAME + " = '" + user +  "' AND " +
                USER_PASS +" = '" + pass + "'" ,null);
    }

    public Cursor GetUser(int userID){
        return database.rawQuery("SELECT * FROM " + TBL_USERS +
                                " WHERE "+ USER_ID + " = " + userID,null);
    }
    public Cursor GetUserList(){
        return  database.rawQuery("SELECT * FROM " + TBL_USERS ,null);
    }







    public void UpdateQuestions(String id,String quuestion,  String choice1, String choice2, String choice3,String choice4, String correctAns){
        database.execSQL("UPDATE  " + TBL_QUESTIONS + " SET "+
                QUESTION_DESC + " = '" +  quuestion +  "', " +
                QUESTION_CHOICE1 + " = '" + choice1 + "', " +
                QUESTION_CHOICE2 + " = '" + choice2 + "', " +
                QUESTION_CHOICE3 + " = '" + choice3 + "', " +
                QUESTION_CHOICE4 + " = '" + choice4 + "', " +
                QUESTION_CORRECTANS + " = '" + correctAns +"'" +
                " WHERE " + QUESTION_ID + " = " + id
                );
    }
    public void DeleteQuestions(String id){
        database.execSQL("DELETE FROM " + TBL_QUESTIONS + " WHERE " + QUESTION_ID + " = " +id);
    }






    public void addQuestions(String question, String choice1, String choice2, String choice3,String choice4, String correctAns ){
       database.execSQL("INSERT INTO " + TBL_QUESTIONS + " ("
                             + QUESTION_DESC + ", " + QUESTION_CHOICE1 + ", " + QUESTION_CHOICE2 + ", " + QUESTION_CHOICE3 + ", " + QUESTION_CHOICE4 + ", " + QUESTION_CORRECTANS +  ") VALUES " +
                            "('" + question + "', '" + choice1 + "' , '"+ choice2+ "' , '" + choice3 + "', '" + choice4 + "', '" + correctAns + "')");

    }
    public void addQuestions(String question, String choice1, String choice2, String choice3,String choice4, String correctAns, SQLiteDatabase db ){
        db.execSQL("INSERT INTO " + TBL_QUESTIONS + " ("
                + QUESTION_DESC + ", " + QUESTION_CHOICE1 + ", " + QUESTION_CHOICE2 + ", " + QUESTION_CHOICE3 + ", " + QUESTION_CHOICE4 + ", " + QUESTION_CORRECTANS +  ") VALUES " +
                "('" + question + "', '" + choice1 + "' , '"+ choice2+ "' , '" + choice3 + "', '" + choice4 + "', '" + correctAns + "')");

    }



    public void AddAnswers(int questionId , int examID, String answered){
        database.execSQL("INSERT INTO " + TBL_ANSWERS + " (" +
                QUESTION_ID + ", " + EXAMS_ID + ", " + ANSWER_ANSWERED + ") VALUES " +
                "(" + questionId + ", " + examID + ", '" + answered + "')" );
    }



    public Cursor GetAnswers(){
        return database.rawQuery("SELECT * FROM " + TBL_ANSWERS +
                                "INNER JOIN " + TBL_QUESTIONS + " ON " +
                                TBL_ANSWERS+ "." + QUESTION_ID + "=" + TBL_QUESTIONS + "." + QUESTION_ID +
                                "WHERE " + TBL_ANSWERS + "." + ANSWER_ID + "=" + TBL_QUESTIONS + "." + ANSWER_ANSWERED ,null);
    }











    public Cursor GetRandomizedQuestion(){
        return  database.rawQuery("SELECT * FROM "+ TBL_QUESTIONS + " ORDER BY RANDOM()",null);
    }




    public void AddExam(int status , int noOfQuestions , int userId, String date){
        database.execSQL("INSERT INTO " + TBL_EXAMS
                        + "( " + EXAMS_STATUS +", " + EXAMS_QUESTNO + ", "+ USER_ID + ", " +EXAMS_DATE + ")"
                        + " VALUES "
                        + " (" + status + ", " + noOfQuestions + ", " + userId + " , '" + date +"')");
    }




    public void UpdateExamsStatus(int id, int status){
        database.execSQL("UPDATE "+ TBL_EXAMS +
                            " SET " +
                        EXAMS_STATUS + " = "+ status +
                        " WHERE " + EXAMS_ID + " = " + id,null);
    }


    public String GetUserById(String uid){
        Cursor c = database.rawQuery("SELECT " + USER_NAME + " FROM " + TBL_USERS + " WHERE " + USER_ID + " = " + uid , null);
        c.moveToNext();
        return c.getString(1);

    }


    public int getExamId(int userId){
        Cursor cursor = database.rawQuery("SELECT * FROM " + TBL_EXAMS +
                                " WHERE " + USER_ID + " = " +userId + " AND "+
                                    EXAMS_STATUS + " = " + 1,null);
        cursor.moveToFirst();
        System.out.println("<DEBUG>"+cursor.getInt(0));
        return cursor.getInt(0);
    }





    public int getExamLastId(int userId){
        Cursor cursor = database.rawQuery("SELECT * FROM " + TBL_EXAMS +
                " WHERE (" + USER_ID + " = " +userId + " AND "+
                EXAMS_STATUS + " = " + 1 + ")" + " ORDER BY " + EXAMS_ID + " DESC LIMIT 1",null);
        cursor.moveToFirst();
        System.out.println("<DEBUG>"+cursor.getInt(0));
        return cursor.getInt(0);
    }





    public Cursor getAnsweredList(int examid){
        return database.rawQuery("SELECT " +
                                TBL_QUESTIONS + "." + QUESTION_DESC + ", " +
                                TBL_QUESTIONS + "." + QUESTION_CORRECTANS + "  , " +
                                TBL_ANSWERS + "." + ANSWER_ANSWERED +
                                " FROM " + TBL_ANSWERS +
                                "  JOIN " +TBL_QUESTIONS +  " ON " +
                                TBL_ANSWERS + "." + QUESTION_ID + " = " + TBL_QUESTIONS + "." + QUESTION_ID +
                                " WHERE "+ EXAMS_ID + " = " + examid ,null);



      /*
       return database.rawQuery("SELECT * " +


                " FROM " + TBL_ANSWERS ,null);
*/
    }

    public String GetUserByExamId(String examId){
        Cursor c = database.rawQuery("SELECT " + TBL_EXAMS + "." + USER_ID +
                                        " FROM " + TBL_EXAMS + "" +
                                       // " INNER  JOIN " + TBL_EXAMS + " ON " + TBL_EXAMS + "." + USER_ID + " = " + TBL_USERS + "." + USER_ID+
                                 " WHERE " +TBL_EXAMS +"."+ EXAMS_ID + "=" + examId,null );

        c.moveToFirst();
        return c.getString(0);
    }


    public Cursor GetUserRecords(int userID){
        return database.rawQuery("SELECT  * " +
                " FROM " + TBL_EXAMS +
                " WHERE " + USER_ID + " = " + userID,null);
    }

    public int GetTotalCorrectAns(int examid){
        Cursor c = database.rawQuery("SELECT COUNT("+ TBL_ANSWERS+"." +ANSWER_ID +") AS TOTAL" +
                " FROM " + TBL_ANSWERS +
                " INNER JOIN " + TBL_QUESTIONS + " ON " +
                TBL_ANSWERS + "." + QUESTION_ID + " = " +
                         TBL_QUESTIONS + "."  + QUESTION_ID +
                " WHERE "+ EXAMS_ID + " = "  + examid + " AND " +
                TBL_ANSWERS + "." + ANSWER_ANSWERED  + " = " + TBL_QUESTIONS + "." + QUESTION_CORRECTANS ,  null);


        c.moveToFirst();
        int x = Integer.parseInt(c.getString(0));

        return x;
    }

    public Cursor GetQuestionList(){
        return database.rawQuery("SELECT * FROM " + TBL_QUESTIONS ,null);
    }
    public Cursor GetQuestionById(String id){
        return database.rawQuery("SELECT * FROM " + TBL_QUESTIONS + " WHERE " + QUESTION_ID + " = " + id ,null);
    }





}
