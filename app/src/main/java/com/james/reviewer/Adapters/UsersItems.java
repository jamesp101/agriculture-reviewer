package com.james.reviewer.Adapters;

import android.content.Context;
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

public class UsersItems extends BaseAdapter {


    LinkedList<String> id;
    LinkedList<String> username;

    Context context;

    public UsersItems(Context context, LinkedList<String> id, LinkedList<String> username){
        this.context = context;
        this.id = id;
        this.username = username;
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
            v = vi.inflate(R.layout.item_user, null);
        }
        TextView textView = (TextView) v.findViewById(R.id.text_userList);
        textView.setText(username.get(i));


        return v;
    }
}
