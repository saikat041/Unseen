package com.example.saika.notseen;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    ArrayList<String> texts=new ArrayList<>();
    ArrayList<String> times=new ArrayList<>();
    SQLiteDatabase db;
    Cursor resultSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intent=getIntent();
        db=openOrCreateDatabase("mydb",MODE_PRIVATE,null);
        db.execSQL("create table if not exists notifications(package_name varchar(50),title varchar(250),body varchar(250),time varchar(30));");
        String title=intent.getStringExtra("title");
        String package_name=intent.getStringExtra("package");
        TextView textView=findViewById(R.id.textView);
        textView.setText(title);
        resultSet = db.rawQuery("select * from notifications where package_name='"+package_name+"' and title='"+title+"';", null);
        while(resultSet.moveToNext()){
            texts.add(resultSet.getString(2));
            times.add(resultSet.getString(3));
        }
        ListView listView=findViewById(R.id.listview);
        CustomArrayAdapter arrayAdapter=new CustomArrayAdapter(this,R.layout.chat_single_line,texts,times);
      //  ArrayAdapter arrayAdapter=new ArrayAdapter(this,R.layout.single_line,texts);
        listView.setAdapter(arrayAdapter);
    }
}

class CustomArrayAdapter extends ArrayAdapter{

    ArrayList<String> texts;
    ArrayList<String> times;
    Context context;
    public CustomArrayAdapter(@NonNull Context context, int resource,ArrayList<String> texts,ArrayList<String> times) {
        super(context, resource,R.id.text,texts);
        this.texts=texts;
        this.times=times;
        this.context=context;
       // Log.d("saikat","Custom arrayadapter called");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

    //    Log.d("saikat","Get view called");
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.chat_single_line,parent,false);
        TextView text=view.findViewById(R.id.text);
        TextView time=view.findViewById(R.id.time);
        text.setText(texts.get(position));
        time.setText(times.get(position));
        return view;
    }
}
