package com.example.administrator.xiangha;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Timer;
import java.util.TimerTask;

import ss.simple.zi.BaseActivity;
import ss.simple.zi.MyAppalication;


public class WelcomActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        getHomeActivity();

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //表示，返回真表示返回键被屏蔽
        if(event.getKeyCode()==KeyEvent.KEYCODE_BACK)
        {
            creatDialog();
        }
        return super.onKeyDown(keyCode, event);
    }

    //定义一个弹框的。。退出
    private void creatDialog() {
        new AlertDialog.Builder(this)
                .setMessage("是否退出app?")
                .setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                System.exit(0);
                            }
                        })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }




    //定义一个自动跳转的方法
    private void getHomeActivity()
    {
        Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(WelcomActivity.this,ContentActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);


            }
        };

        timer.schedule(task,2500);

    }


}
