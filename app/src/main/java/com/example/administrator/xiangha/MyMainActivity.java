package com.example.administrator.xiangha;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.xxj.xianghamy.R;
//import java.lang.CharSequence;
//import java.lang.Object;
//import java.lang.Override;
//import java.lang.Runnable;import java.lang.String;import java.lang.StringBuilder;import java.lang.System;import java.lang.Throwable;import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
//import cn.sharesdk.framework.Platform;
//import cn.sharesdk.framework.PlatformActionListener;
//import cn.sharesdk.framework.ShareSDK;
//import cn.sharesdk.tencent.qq.QQ;
//import sharesdk.onekeyshare.OnekeyShare;


public class MyMainActivity extends ActionBarActivity {
    private String my_listview1_title[] = {"美食贴", "菜谱", "收藏", "离线菜谱"};
    private int my_listview1_icon[] = {R.drawable.z_me_list_ico_meishitie, R.drawable.z_me_list_ico_caipu, R.drawable.z_me_list_ico_fav, R.drawable.z_me_list_ico_offline};
    private String my_listview2_title[] = {"意见反馈", "邀请好友", "给香哈好评", "检查更新","清理缓存"};
    private int my_listview2_icon[] = {R.drawable.z_me_list_ico_feedback, R.drawable.z_me_list_ico_sendfriends, R.drawable.z_me_list_ico_comment, R.drawable.z_me_list_ico_renew, R.drawable.z_me_list_ico_del};
    private ListView listview1;
    private ListView listview2;
    private RelativeLayout login_relative;
    private LinearLayout message_linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity_main);
        listview1 = ((ListView) findViewById(R.id.my_listview1));
        listview2 = ((ListView) findViewById(R.id.my_listview2));
        login_relative = ((RelativeLayout) findViewById(R.id.login_relative));
        message_linear = ((LinearLayout) findViewById(R.id.my_login_linear));

        login_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyMainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        listview1.setAdapter(new MyAdapter(MyMainActivity.this,my_listview1_icon,my_listview1_title));
        listview2.setAdapter(new MyAdapter2(MyMainActivity.this,my_listview2_icon,my_listview2_title));

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyMainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        message_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyMainActivity.this, NotifySetActivity.class);
                startActivity(intent);
            }
        });
        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
//                        showShare();
                        break;
                    case 1:
//                        showShare();
                        break;
                    case 2:
                        break;
                    case 3:
                        Toast.makeText(MyMainActivity.this,"已是最新版本，无需更新",Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(MyMainActivity.this,"缓存已清除",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }
//    private void showShare() {
//        ShareSDK.initSDK(this);
//        m.framework.utils.UIHandler.prepare();
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//
//// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
//        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
//        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle(getString(R.string.share));
//        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
////        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");
//        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
//        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(getString(R.string.app_name));
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");
//
//// 启动分享GUI
//        oks.show(this);
//    }
//    private void authorize(){
//        ShareSDK.initSDK(this);
//        Platform weibo = ShareSDK.getPlatform(this, QQ.NAME);
//        weibo.setPlatformActionListener(MyMainActivity.this);
//        weibo.showUser(null);//执行登录，登录后在回调里面获取用户资料
////weibo.showUser(“3189087725”);//获取账号为“3189087725”的资料
//    }

//    @Override
//    public void onComplete(Platform platform, int i, HashMap<String, Object> stringObjectHashMap) {
//        final StringBuilder builder=new StringBuilder();
//        Set<Map.Entry<String, Object>> entries = stringObjectHashMap.entrySet();
//        for (Map.Entry<String,Object> entry:entries){
//            builder.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
//        }
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
////                text.setText(builder.toString());
//            }
//        });
////        text.setText(builder.toString());
//    }
//
//    @Override
//    public void onError(Platform platform, int i, Throwable throwable) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(MyMainActivity.this,"授权失败",Toast.LENGTH_SHORT).show();
//            }
//        });
//        System.out.println(throwable);
//    }
//
//    @Override
//    public void onCancel(Platform platform, int i) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(MyMainActivity.this,"授权取消",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    class MyAdapter extends BaseAdapter{
        private Context context;
        private int[] array_icon;
        private String array_title[];
        private LayoutInflater inflater;

        MyAdapter(Context context, int[] array_icon, String[] array_title) {
            this.context = context;
            this.array_icon = array_icon;
            this.array_title = array_title;
            inflater=LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return array_icon.length;
        }

        @Override
        public Object getItem(int position) {
            return array_icon[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView==null){
                convertView=inflater.inflate(R.layout.listview_item,parent,false);
                holder=new ViewHolder();
                holder.icon= ((ImageView) convertView.findViewById(R.id.my_listview_icon));
                holder.title= ((TextView) convertView.findViewById(R.id.my_listview_title));
                holder.text= ((TextView) convertView.findViewById(R.id.my_listview_text));
                convertView.setTag(holder);
            }else{
                holder= ((ViewHolder) convertView.getTag());
            }
            //赋值
            holder.icon.setImageResource(array_icon[position]);
            holder.title.setText(((CharSequence) array_title[position]));
            if ("离线菜谱".equals(array_title[position])){
                holder.text.setText("0");
            }
            return convertView;
        }
        class ViewHolder{
            ImageView icon;
            TextView title,text;
        }
    }
    class MyAdapter2 extends BaseAdapter{
        private Context context;
        private int[] array_icon;
        private String array_title[];
        private LayoutInflater inflater;

        MyAdapter2(Context context, int[] array_icon, String[] array_title) {
            this.context = context;
            this.array_icon = array_icon;
            this.array_title = array_title;
            inflater=LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return array_icon.length;
        }

        @Override
        public Object getItem(int position) {
            return array_icon[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView==null){
                convertView=inflater.inflate(R.layout.listview_item,parent,false);
                holder=new ViewHolder();
                holder.icon= ((ImageView) convertView.findViewById(R.id.my_listview_icon));
                holder.title= ((TextView) convertView.findViewById(R.id.my_listview_title));
                holder.text= ((TextView) convertView.findViewById(R.id.my_listview_text));
                convertView.setTag(holder);
            }else{
                holder= ((ViewHolder) convertView.getTag());
            }
            //赋值
            holder.icon.setImageResource(array_icon[position]);
            holder.title.setText(((CharSequence) array_title[position]));
            if ("意见反馈".equals(array_title[position])){
                holder.text.setText("随时倾听您的建议");
            }else if ("检查更新".equals(array_title[position])){
                holder.text.setText("3.1.7");
            }
            return convertView;
        }
        class ViewHolder{
            ImageView icon;
            TextView title,text;
        }
    }
}
