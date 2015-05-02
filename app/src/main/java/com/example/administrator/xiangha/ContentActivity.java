package com.example.administrator.xiangha;

import android.app.ActivityGroup;
import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;


import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;


public class ContentActivity extends TabActivity {
    private TabHost tabHost;
    private Intent intent;
    //用来跳转的activity


private String title[]={"菜谱","美食圈","null","消息","我"};//后面要进行一下判断


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_content);
        initTabView();
        RadioGroup radiogroup= (RadioGroup)this.findViewById(R.id.main_tab_group);
        //设置它的监听事件
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.main_tab_addExam1:
                        tabHost.setCurrentTabByTag("菜谱");
                        break;
                    case R.id.main_tab_addExam2:
                        tabHost.setCurrentTabByTag("美食圈");
                        break;
                    case R.id.main_tab_addExam3:

                        break;
                    case R.id.main_tab_addExam4:
                        tabHost.setCurrentTabByTag("消息");
                        break;
                    case R.id.main_tab_addExam5:
                        tabHost.setCurrentTabByTag("我");
                        break;

                }
            }
        });


    }




//定义一个方法，实例化tab标签

public void initTabView()
{
    tabHost=this.getTabHost();

    //由于继承了ActivityGroup，所以需要在setup方法里加入此参数，若继承TabActivity则可省略
   tabHost.setup(this.getLocalActivityManager());
    //创建标签
    TabHost.TabSpec spec;


           //设置跳转activity

        //载入view对象并设置跳转的activity
    intent=new Intent().setClass(this, MainActivity.class);
    spec=tabHost.newTabSpec("菜谱").setIndicator("菜谱").setContent(intent);
    tabHost.addTab(spec);
    intent=new Intent().setClass(this, MainActivity2.class);
    spec=tabHost.newTabSpec("美食圈").setIndicator("美食圈").setContent(intent);
    tabHost.addTab(spec);


    intent=new Intent().setClass(this, MainActivity3.class);
    spec=tabHost.newTabSpec("消息").setIndicator("消息").setContent(intent);
    tabHost.addTab(spec);

    intent=new Intent().setClass(this, MainActivity4.class);
    spec=tabHost.newTabSpec("我").setIndicator("我").setContent(intent);
    tabHost.addTab(spec);


    tabHost.setCurrentTab(1);//设置标记


}

}
