package com.example.saika.notseen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by saika on 11-12-2017.
 */

public class MyArrayAdapter extends ArrayAdapter {

    private  Context context;
    private ArrayList<String> titles;
    private ArrayList<String> bodys;
    private ArrayList<String> times;
    public MyArrayAdapter(@NonNull Context context, int resource, ArrayList<String> titles, ArrayList<String>bodys, ArrayList<String> times) {
        super(context, resource,R.id.title,titles);
        Log.i("saikat","constructor called");
        this.context=context;
        this.titles=titles;
        this.bodys=bodys;
        this.times=times;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.single_line,parent,false);
        TextView title=row.findViewById(R.id.title);
        String title_string=titles.get(position);
        title.setText(title_string);
        TextView body=row.findViewById(R.id.body);
        String body_string=bodys.get(position);
        body.setText(body_string);
       TextView circle=row.findViewById(R.id.circle);circle.setText(titles.get(position).substring(0,1).toUpperCase());
        TextView time=row.findViewById(R.id.time);time.setText(times.get(position));
        return row;
    }

    public int min(int a,int b){
        if(a<b)return a;
        return b;
    }
}
