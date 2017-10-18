package com.james.reviewer.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.james.reviewer.R;

import java.util.LinkedList;

/**
 * Created by james on 10/17/17.
 */

public class QuestionEditorAdapter extends BaseAdapter {
    LinkedList<String> questionId;
    LinkedList<String> questionDesc;
    LinkedList<String> choice1;
    LinkedList<String> choice2;
    LinkedList<String> choice3;
    LinkedList<String> choice4;
    LinkedList<String> correctAns;

    Context context;
    public QuestionEditorAdapter (Context context,LinkedList<String> questionId, LinkedList<String> questionDesc, LinkedList<String> choice1, LinkedList<String> choice2 , LinkedList<String> choice3,
                                  LinkedList<String> choice4, LinkedList<String> correctAns){

        this.questionId = questionId;
        this.questionDesc = questionDesc;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.correctAns = correctAns;
        this.context = context;

        for(int a = 0; a < questionId.size(); a++){
            Log.wtf("questions" , "qid:" + questionId.get(a) + "\t qdesc:" + questionDesc.get(a) );
        }
    }

    @Override
    public int getCount() {
        return questionId.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(questionId.get(i));
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.item_question_list, null);
        }



        TextView txtquestion =(TextView) v.findViewById(R.id.text_qlistQuestions);
        txtquestion.setText(questionDesc.get(i));

        TextView txtchoice1 =(TextView) v.findViewById(R.id.text_qlistchoice1);
        txtchoice1.setText("A."+choice1.get(i));

        TextView txtchoice2 =(TextView) v.findViewById(R.id.text_qlistchoice2);
        txtchoice2.setText("B." +choice2.get(i));

        TextView txtchoice3 =(TextView) v.findViewById(R.id.text_qlistchoice3);
        txtchoice3.setText("C."+choice3.get(i));

        TextView txtchoice4 =(TextView) v.findViewById(R.id.text_qlistchoice4);
        txtchoice4.setText("D."+choice4.get(i));

        TextView txtans =(TextView) v.findViewById(R.id.text_qlistAns);
        txtans.setText("Correct answer : " + correctAns.get(i));


        return v;
    }
}
