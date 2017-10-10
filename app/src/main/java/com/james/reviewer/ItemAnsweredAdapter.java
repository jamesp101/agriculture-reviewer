package com.james.reviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by james on 10/10/17.
 */

public class ItemAnsweredAdapter extends BaseAdapter {

    private String[] question;
    private String[] correctAns;
    private String[] userAns;

    int item;

    Context context;

    private static LayoutInflater inflater = null;

    public ItemAnsweredAdapter(Context context, String[] question, String[] userAns,String[] correctAns){
        this.question = question;
        this.correctAns = correctAns;
        this.userAns = userAns;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        item = i;
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.answered_item_list, null);
        }

        TextView txtquestion =(TextView) v.findViewById(R.id.txt_list_item_question);
        txtquestion.setText(question[i]);

        TextView txtanswer =(TextView) v.findViewById(R.id.txt_list_item_usertAns);
        txtanswer.setText(userAns[i]);

        TextView txtcorrectAns =(TextView) v.findViewById(R.id.txt_list_item_correctAns);
        txtcorrectAns.setText(correctAns[i]);

        return v;
    }
}
