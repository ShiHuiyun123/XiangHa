package com.example.administrator.xiangha;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.RelativeLayout;


public class NotifySetActivity extends ActionBarActivity {

    private RelativeLayout notify_relative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_set);
        notify_relative = ((RelativeLayout) findViewById(R.id.notify_relative));

        notify_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
