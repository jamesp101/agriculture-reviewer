package com.james.reviewer.Adapters;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.james.reviewer.R;

/**
 * Created by james on 10/12/17.
 */

public class ItemAnsweredAdapter extends BaseAdapter {

    Context context;


    String [] x;
    String [] y;
    String [] z;

    public ItemAnsweredAdapter(Context c, String[] x, String[] y , String[] z){
        this.context = c;
        this.x = x;
        this.y = y;
        this.z = z;
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
            v = vi.inflate(R.layout.item_answered, null);
        }
        ImageView imageView = v.findViewById(R.id.imageView2);



        TextView txtquestion =(TextView) v.findViewById(R.id.txt_list_item_question);
        txtquestion.setText(x[i]);

        TextView txtanswer =(TextView) v.findViewById(R.id.txt_list_item_usertAns);
        txtanswer.setText(y[i]);

        TextView txtcorrectAns =(TextView) v.findViewById(R.id.txt_list_item_correctAns);
        txtcorrectAns.setText(z[i]);

        if(y[i].equals(z[i])){
            imageView.setImageResource(R.drawable.checked);
            txtanswer.setTextColor(Color.rgb(0,150,0));
        }else{
            imageView.setImageResource(R.drawable.wrong);
            txtanswer.setTextColor(Color.rgb(150,0,0));
        }


        return v;
    }
}
