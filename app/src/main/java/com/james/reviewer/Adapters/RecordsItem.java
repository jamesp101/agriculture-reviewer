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
 * Created by james on 10/15/17.
 */

public class RecordsItem extends BaseAdapter {


    LinkedList <String> id, date;
    Context context;

    public RecordsItem (Context context,LinkedList<String> id , LinkedList <String> date){
        this.id = id;
        this.date = date;
        this.context = context;

        for(int x = 0; x < id.size(); x++){
            Log.w("zzzzzzz" ,  "  xx  " + this.id.get(x) + "    xx "  + this.date.get(x ));
        }

    }

    @Override
    public int getCount() {
        return id.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(id.get(i));
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.record_item, null);
        }



        TextView txtquestion =(TextView) v.findViewById(R.id.txt_date);
        txtquestion.setText(id.get(i));

        TextView txtanswer =(TextView) v.findViewById(R.id.txt_score);
        txtanswer.setText(date.get(i));

        return v;
    }
}
