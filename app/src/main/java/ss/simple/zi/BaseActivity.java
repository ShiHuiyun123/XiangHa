package ss.simple.zi;

import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by aaa on 15-5-4.
 * 抽象出添加activity的方法
 *
 */
public class BaseActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyAppalication.getInstance().addActivity(BaseActivity.this);
    }
}
