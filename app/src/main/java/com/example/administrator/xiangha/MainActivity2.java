package com.example.administrator.xiangha;
//美食圈页面
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import fra.ss.cns.Mshiquan_Zuixin_Fragment;


public class MainActivity2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        setextract(Mshiquan_Zuixin_Fragment.getMshiquan_Zuixin_Fragment());
    }


    private void setextract(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction traction = fragmentManager.beginTransaction();
        traction.replace(R.id.meishi, fragment);
        traction.commit();

    }
}
