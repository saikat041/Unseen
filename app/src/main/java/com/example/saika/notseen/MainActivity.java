package com.example.saika.notseen;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
 private FragmentManager fm;
 SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm=getSupportFragmentManager();
        Myadapter myadapter=new Myadapter(fm);
        ViewPager viewPager=findViewById(R.id.viewpager);
        viewPager.setAdapter(myadapter);
    }

}

class Myadapter extends FragmentStatePagerAdapter{

    Fragment fragment=null;
    public Myadapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            fragment=new WhatsappFragment();
        }
        if(position==1){
            fragment=new MessengerFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0)return "Whatsapp";
        if(position==1)return "Messenger";
        return  null;
    }
}
