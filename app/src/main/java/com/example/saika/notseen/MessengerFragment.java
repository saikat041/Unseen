package com.example.saika.notseen;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by saika on 11-12-2017.
 */

public class MessengerFragment extends android.support.v4.app.Fragment {
    private ArrayList<String> titles =new ArrayList<>();
    private ArrayList<String> bodys =new ArrayList<>();
    private ArrayList<String> times =new ArrayList<>();
    SQLiteDatabase db;
    Cursor resultSet;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        db=getActivity().openOrCreateDatabase("mydb",MODE_PRIVATE,null);
        db.execSQL("create table if not exists notifications(package_name varchar(50),title varchar(250),body varchar(250),time varchar(30));");
        resultSet = db.rawQuery("select * from notifications group by title having package_name='com.facebook.mlite';", null);
        while(resultSet.moveToNext()){
            titles.add(resultSet.getString(1));
            bodys.add(resultSet.getString(2));
            times.add(resultSet.getString(3));
        }
        View view=inflater.inflate(R.layout.messenger_fragment,container,false);
        MyArrayAdapter adapter=new MyArrayAdapter(getContext(),R.layout.single_line,titles,bodys,times);
        ListView listView=view.findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(),ChatActivity.class);
                TextView textView=view.findViewById(R.id.title);
                intent.putExtra("title",textView.getText());
                intent.putExtra("package","com.facebook.mlite");
                startActivity(intent);
            }
        });
        return view;
    }
}
