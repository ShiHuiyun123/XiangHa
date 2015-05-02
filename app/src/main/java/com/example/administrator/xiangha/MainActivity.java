package com.example.administrator.xiangha;
//菜谱页面
import android.content.Context;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.AbsListView.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lidroid.xutils.BitmapUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.qust.utils.PinnedHeaderExpandableListView;
import cn.qust.utils.StickyLayout;

import fra.ss.cns.AddPageFragment2;
import fra.ss.cns.AddPageFragment3;
import ss.simple.zi.AddPageAdapter;
import ss.simple.zi.Group;



public class MainActivity extends ActionBarActivity implements PinnedHeaderExpandableListView.OnHeaderUpdateListener, ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupClickListener, StickyLayout.OnGiveUpTouchEventListener {
    private PinnedHeaderExpandableListView expandableListView;
    private StickyLayout stickyLayout;
    private ViewPager viewpage1,viewpage2,viewpage3;
    private RadioGroup radiopage1,radiopage2;
    private TextView tv1,notv,notv1,notv2;
    private Button caipu,jiangkang;
    private ImageView noiv,noiv1,noiv2;
    //用来存放各个模块的数据
    private List<String>cai=new ArrayList<>();
    private List<String>jian=new ArrayList<>();

    private List<HashMap<String,Object>>list1=new ArrayList<>();
    private List<HashMap<String,Object>>list2=new ArrayList<>();
    private List<HashMap<String,Object>>list3=new ArrayList<>();
    private List<HashMap<String,Object>>list4=new ArrayList<>();
    private List<HashMap<String,Object>>list5=new ArrayList<>();
    private List<HashMap<String,Object>>list6=new ArrayList<>();


 private final static String PA="http://api.xiangha.com/home5/getIndexData/?type=newData";
private final static int ids[]={R.id.zhu_rbg1,R.id.zhu_rbg2,R.id.zhu_rbg3};
private final static int ids2[]={R.id.zhu_rbg1_1,R.id.zhu_rbg2_2,R.id.zhu_rbg3_3};
private GridView gridview;
    private MyexpandableListAdapter adapter;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         caipu= (Button) findViewById(R.id.zhu_caipu);
        jiangkang= (Button) findViewById(R.id.zhu_yangsheng);
        viewpage1= (ViewPager) findViewById(R.id.zhu_viewpager1);
        radiopage1= (RadioGroup) findViewById(R.id.zhu_rg);
        viewpage2= (ViewPager) findViewById(R.id.zhu_viewpager1_2);
        radiopage2= (RadioGroup) findViewById(R.id.zhu_rg_2);
        viewpage3= (ViewPager) findViewById(R.id.zhu_viewpager1_3);
          tv1= (TextView) findViewById(R.id.zhu_textview1);
        requestQueue =Volley.newRequestQueue(MainActivity.this);
        notv = (TextView) findViewById(R.id.no_textview);
        notv1 = (TextView) findViewById(R.id.no_textview1);
        notv2 = (TextView) findViewById(R.id.no_textview2);
        noiv= (ImageView) findViewById(R.id.no_imageview);
        noiv1= (ImageView) findViewById(R.id.no_imageview1);
        noiv2= (ImageView) findViewById(R.id.no_imageview2);
        gridview= (GridView) findViewById(R.id.zhu_gridview);
        expandableListView = (PinnedHeaderExpandableListView) findViewById(R.id.expandablelist);
        stickyLayout = (StickyLayout)findViewById(R.id.sticky_layout);
        //先进行网络下载，获取信息
        getData();
        adapter = new MyexpandableListAdapter(this);
       expandableListView.setAdapter(adapter);
        expandableListView.setOnChildClickListener(this);
        expandableListView.setOnGroupClickListener(this);
        stickyLayout.setOnGiveUpTouchEventListener(this);

        //给gridview设置数据...默认
        ArrayAdapter<String>adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cai);
        gridview.setAdapter(adapter3);
//给viewpage1设置适配器
viewpage1.setAdapter(new AddPageAdapter(getSupportFragmentManager()));
  //设置viewpage1的监听事件
        viewpage1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radiopage1.check(ids[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
       //设置radiopage1的监听事假
        radiopage1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < ids.length; i++) {
                    if (ids[i] == checkedId) {
                        // 设置当前被选中的ViewPager页
                        viewpage1.setCurrentItem(i);
                    }
                }
            }
        });
        //给viewpage2设置数据
          //  viewpage2.setAdapter(new AddpageAdapter2(getSupportFragmentManager()));


      // 给viewpage3设置数据

      viewpage3.setAdapter(new AddpageAdapter3(getSupportFragmentManager()) );



    }





    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


        return false;
    }

    @Override
    public boolean giveUpTouchEvent(MotionEvent event) {
        if (expandableListView.getFirstVisiblePosition() == 0) {
            View view = expandableListView.getChildAt(0);
            if (view != null && view.getTop() >= 0) {
                return true;
            }
        }
        return false;

    }



    @Override
    public View getPinnedHeader() {
        View headerView = (ViewGroup) getLayoutInflater().inflate(R.layout.group, null);
        headerView.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        return headerView;
    }

    @Override
   public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {
        Group firstVisibleGroup = (Group) adapter.getGroup(firstVisibleGroupPos);


    }


    @Override
    public boolean onGroupClick(final ExpandableListView parent, final View v,
                                int groupPosition, final long id) {

        return false;
    }

    class  MyexpandableListAdapter extends BaseExpandableListAdapter
   {
       private Context context;
       private LayoutInflater inflater;
       public MyexpandableListAdapter(MainActivity context) {
           this.context = context;
           inflater = LayoutInflater.from(context);
       }

       @Override
       public int getGroupCount() {
           return 1;
       }

       @Override
       public int getChildrenCount(int groupPosition) {
           return 0;
       }


       @Override
       public Object getGroup(int groupPosition) {
           return null;
       }

       @Override
       public Object getChild(int groupPosition, int childPosition) {
           return null;
       }


       @Override
       public long getGroupId(int groupPosition) {
           return groupPosition;

       }

       @Override
       public long getChildId(int groupPosition, int childPosition) {
           return childPosition;
       }

       @Override
       public boolean hasStableIds() {
           return true;
       }


       @Override
       public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {


               convertView = inflater.inflate(R.layout.group, null);


               return convertView;

       }

       @Override
       public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {


               convertView = inflater.inflate(R.layout.child, null);


               Button button = (Button) convertView
                       .findViewById(R.id.button1);
               button.setOnClickListener(new Button.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Toast.makeText(MainActivity.this, "clicked pos=", Toast.LENGTH_SHORT).show();
                   }
               });




           return convertView;
       }

       @Override
       public boolean isChildSelectable(int groupPosition, int childPosition) {
           return true;
       }
   }

//定义一方法用来下载数据
    public void getData()
    {

        JsonObjectRequest request=new JsonObjectRequest(PA,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                //开始下载数据

                try {
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONArray recommend = data.getJSONArray("recommend");
                    HashMap<String,Object>map1;
                    for(int i=0;i<recommend.length();i++)
                    {
                        map1=new HashMap<>();
                        JSONObject obj1 = recommend.getJSONObject(i);
                        map1.put("title",obj1.get("title"));//时间
                        map1.put("type",obj1.get("type"));
                        map1.put("url",obj1.get("url"));//跳转地址
                        map1.put("img",obj1.get("img"));//图片
                        map1.put("time",obj1.get("time"));
                        list1.add(map1);


                    }
                    JSONObject whatHour = data.getJSONObject("whatHour");
                    HashMap<String ,Object>map2=new HashMap<>();
                    map2.put("title",whatHour.get("title"));//标题
                    map2.put("url",whatHour.get("url"));//跳转地址
                    tv1.setText(whatHour.get("title").toString());

                    list2.add(map2);
                    JSONArray fenlei = data.getJSONArray("fenlei");
                    HashMap<String,Object>map3;
                    for(int i=0;i<fenlei.length();i++)
                    {
                        JSONObject obj3 = fenlei.getJSONObject(i);
                        JSONArray tags = obj3.getJSONArray("tags");
                        map3=new HashMap<>();
                        for(int j=0;j<tags.length();j++)
                        {
                            JSONObject obj3_2 = tags.getJSONObject(j);

                            map3.put("isHot",obj3_2.get("isHot"));
                            map3.put("url",obj3_2.get("url"));
                            map3.put("name",obj3_2.get("name"));
                            if(i==0)
                            {
                                cai.add(obj3_2.get("name").toString());
                            }else
                            {
                                jian.add(obj3_2.get("name").toString());
                            }

                        }
                        list3.add(map3);

                    }
                    cai.add("更多>>");
                    jian.add("更多>>");
                    JSONArray hotUser = data.getJSONArray("hotUser");
                    HashMap<String,Object>map4;
                    for(int i=0;i<hotUser.length();i++)
                    {
                        map4=new HashMap<>();
                        JSONObject obj4 = hotUser.getJSONObject(i);
                        map4.put("code",obj4.get("code"));
                        map4.put("nickName",obj4.get("nickName"));
                        map4.put("img",obj4.get("img"));
                        map4.put("hotNum",obj4.get("hotNum"));

                       list4.add(map4);
                    }



                    JSONArray nouses = data.getJSONArray("nouses");
                    HashMap<String,Object>map5;
                    for(int i=0;i<nouses.length();i++)
                    {
                        map5=new HashMap<>();
                        JSONObject obj5 = nouses.getJSONObject(i);
                        map5.put("code",obj5.get("code"));
                        map5.put("title",obj5.get("title"));
                        map5.put("img",obj5.get("img"));
                        list5.add(map5);
                        //给控件设置
                        if(i==0)
                        {
                            notv.setText(obj5.get("title").toString());
                            BitmapUtils bitmapUtils = new BitmapUtils(MainActivity.this, Environment.getExternalStorageDirectory().getAbsolutePath(), 1 / 8.0f, 1024 * 1024 * 1024);
                            bitmapUtils.display(noiv, obj5.get("img").toString());

                        }else if(i==1)
                        {
                            notv1.setText(obj5.get("title").toString());
                            BitmapUtils bitmapUtils = new BitmapUtils(MainActivity.this, Environment.getExternalStorageDirectory().getAbsolutePath(), 1 / 8.0f, 1024 * 1024 * 1024);
                            bitmapUtils.display(noiv1, obj5.get("img").toString());
                        }else if(i==2)
                        {
                            notv2.setText(obj5.get("title").toString());
                            BitmapUtils bitmapUtils = new BitmapUtils(MainActivity.this, Environment.getExternalStorageDirectory().getAbsolutePath(), 1 / 8.0f, 1024 * 1024 * 1024);
                            bitmapUtils.display(noiv2, obj5.get("img").toString());
                        }



                    }

                    JSONArray hotContent = data.getJSONArray("hotContent");
                   HashMap<String,Object>map6;
                    for(int i=0;i<hotContent.length();i++)
                    {
                        JSONObject obj6 = hotContent.getJSONObject(i);
                        map6=new HashMap<>();
                        map6.put("name",obj6.get("name"));
                        map6.put("type",obj6.get("type"));
                        //?
                        map6.put("imgs",obj6.get("imgs"));
                        map6.put("title",obj6.get("title"));
                        map6.put("url",obj6.get("url"));
                        map6.put("content",obj6.get("content"));
                        list6.add(map6);


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        requestQueue.add(request);


    }

    public void btnonClick(View view)//选择菜谱
    {
        switch (view.getId())
        {
            case R.id.zhu_caipu://集合cai
                ArrayAdapter<String>adapter1=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,cai);
                gridview.setAdapter(adapter1);
                break;
            case R.id.zhu_yangsheng://集合jian
                ArrayAdapter<String>adapter2=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,jian);
                gridview.setAdapter(adapter2);
                break;
        }
    }
    //定义一个类
    public class AddpageAdapter3 extends FragmentPagerAdapter{

        public AddpageAdapter3(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return AddPageFragment3.getData(position);
        }

        @Override
        public int getCount() {


           return 2;
        }
    }
    //定义一个类
    public class AddpageAdapter2 extends FragmentPagerAdapter{

        public AddpageAdapter2(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return AddPageFragment2.getData(position);
        }

        @Override
        public int getCount() {

            return  list6.size();
        }
    }


}
