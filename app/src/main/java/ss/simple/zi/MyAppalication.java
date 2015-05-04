package ss.simple.zi;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;

/**
 * Created by aaa on 15-5-4.
 */
public class MyAppalication extends Application {
    //存放activity的集合
    private static  ArrayList<Activity>list=new ArrayList<>();
    private static MyAppalication instance;
    public MyAppalication()
    {

    }
//利用单例模式获取MyApplication实例
    public static MyAppalication getInstance()
    {
        if(null==instance)
        {
            instance=new MyAppalication();
        }
        return instance;
    }
//添加activity到list集合
    public  void addActivity(Activity activity)
    {

        list.add(activity);
    }
//退出所有的activity
    public void exit()
    {

        for(Activity activity:list)
        {
            activity.finish();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
