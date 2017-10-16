package com.james.reviewer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.james.reviewer.R;


public class ScheduleItemAdapter extends BaseAdapter {


    Context context;


    String [] x;
    String [] y;
    String [] z;

    public ScheduleItemAdapter(Context c, String[] title, String[] location , String[] date){
        this.context = c;
        this.x = title;
        this.y = location;
        this.z = date;
    }


    @Override
    public int getCount() {
        return x.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
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



        TextView txtquestion =(TextView) v.findViewById(R.id.txt_schedTitle);
        txtquestion.setText(x[i]);

        TextView txtanswer =(TextView) v.findViewById(R.id.txt_schedLocation);
        txtanswer.setText(y[i]);

        TextView txtcorrectAns =(TextView) v.findViewById(R.id.txt_schedDate);
        txtcorrectAns.setText(z[i]);
        return v;
    }
}
