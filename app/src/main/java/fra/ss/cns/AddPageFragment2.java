package fra.ss.cns;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.xiangha.R;
import com.lidroid.xutils.BitmapUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by aaa on 15-5-1.
 */
public class AddPageFragment2 extends Fragment {
    private  List<HashMap<String,String>>imagepath;
    private List<HashMap<String,Object>> list1=new ArrayList<>();

    public static  AddPageFragment2 getData(int position)
    {
        AddPageFragment2 addmy=new AddPageFragment2();
        Bundle bundle=new Bundle();
        bundle.putInt("ss", position);
        addmy.setArguments(bundle);
        return addmy;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        String PA="http://api.xiangha.com/home5/getIndexData/?type=newData";
        View view=inflater.inflate(R.layout.view02,container,false);
        final LinearLayout re_lin= (LinearLayout) view.findViewById(R.id.re_linear);
        //找到控件，下载数据
        final TextView tv1= (TextView) view.findViewById(R.id.re_tv1);
        final TextView tv2= (TextView) view.findViewById(R.id.re_tv2);
        final TextView tv3= (TextView) view.findViewById(R.id.re_tv3);
        final ImageView iv1= (ImageView) view.findViewById(R.id.re_image1);
        final ImageView iv2= (ImageView) view.findViewById(R.id.re_image2);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest request=new JsonObjectRequest(PA,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {


                try {
                    JSONObject data = jsonObject.getJSONObject("data");

                    JSONArray hotContent = data.getJSONArray("hotContent");
                    HashMap<String,Object> map6;
                    for(int i=0;i<hotContent.length();i++)
                    {
                        JSONObject obj6 = hotContent.getJSONObject(i);
                        map6=new HashMap<>();
                        map6.put("name",obj6.get("name"));
                        map6.put("type",obj6.get("type"));
                        map6.put("title",obj6.get("title"));
                        map6.put("url",obj6.get("url"));
                        map6.put("content",obj6.get("content"));
                        list1.add(map6);


                    }

                    imagePath();

                    //下载完数据了
                    Bundle bundle=getArguments();

                    int a=  bundle.getInt("ss");

                    //进行判断
                    if(a==0)
                    {
                       HashMap<String,String>map= imagepath.get(a);
                        //设置图片
                        BitmapUtils bitmapUtils = new BitmapUtils(getActivity(), Environment.getExternalStorageDirectory().getAbsolutePath(), 1 / 8.0f, 1024 * 1024 * 1024);
                         bitmapUtils.display(re_lin,map.get("imags"));
                        tv1.setText("热门活动");
                    }else if(a==1)
                    { HashMap<String,String>map= imagepath.get(a);
                        //设置图片
                        BitmapUtils bitmapUtils = new BitmapUtils(getActivity(), Environment.getExternalStorageDirectory().getAbsolutePath(), 1 / 8.0f, 1024 * 1024 * 1024);
                         bitmapUtils.display(iv1, map.get("image1"));
                        BitmapUtils bitmapUtils1 = new BitmapUtils(getActivity(), Environment.getExternalStorageDirectory().getAbsolutePath(), 1 / 8.0f, 1024 * 1024 * 1024);
                         bitmapUtils1.display(iv2, map.get("image2"));
                        HashMap<String,Object>map2= list1.get(a-1);
                        tv1.setText( map2.get("name").toString());
                        tv2.setText( map2.get("title").toString());
                        tv3.setText( map2.get("content").toString());

                    }else if(a==2)
                    {
                        HashMap<String,String>map= imagepath.get(a);
                        //设置图片
                        BitmapUtils bitmapUtils = new BitmapUtils(getActivity(), Environment.getExternalStorageDirectory().getAbsolutePath(), 1 / 8.0f, 1024 * 1024 * 1024);
                         bitmapUtils.display(iv1, map.get("image1"));
                        BitmapUtils bitmapUtils1 = new BitmapUtils(getActivity(), Environment.getExternalStorageDirectory().getAbsolutePath(), 1 / 8.0f, 1024 * 1024 * 1024);
                          bitmapUtils1.display(iv2, map.get("image2"));
                        HashMap<String,Object>map2= list1.get(a-1);
                        tv1.setText( map2.get("name").toString());
                        tv2.setText( map2.get("title").toString());
                        tv3.setText( map2.get("content").toString());


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


        return view;
    }

    //定义一个方法
    public void  imagePath()
    {
       imagepath=new ArrayList<>();
        HashMap<String,String>map=new HashMap<>();
        map.put("imags","http://f1.xiangha.com/activity/201504/27112823242.jpg?imageMogr2/gravity/Center/quality/100/crop/960x300");
        imagepath.add(map);
        HashMap<String,String>map1=new HashMap<>();

        map1.put("image1","http://static.xiangha.com/caipu/201206/2418/1000_0/241857474237.jpg/500x0");
        map1.put("image2","http://static.xiangha.com/caipu/201206/2722/1000_0/272245561505.jpg/500x0");
        imagepath.add(map1);
        HashMap<String,String>map2=new HashMap<>();
        map2.put("image1","http://static.xiangha.com/quan/201505/0407/1000_1000/5546af19d74bf.jpg/300x300");
        map2.put("image2","http://static.xiangha.com/quan/201505/0407/1000_1000/5546af1b2d602.jpg/300x300");
        imagepath.add(map2);
    }

}
