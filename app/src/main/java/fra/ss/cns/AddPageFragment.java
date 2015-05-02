package fra.ss.cns;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
public class AddPageFragment  extends Fragment{
private List<HashMap<String,Object>> list1=new ArrayList<>();
    public static  AddPageFragment getData(int position)
    {
        AddPageFragment addmy=new AddPageFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("ss", position);
        addmy.setArguments(bundle);
        return addmy;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         String PA="http://api.xiangha.com/home5/getIndexData/?type=newData";
           View view= inflater.inflate(R.layout.viewpage_01,container,false);
              final ImageView iv= (ImageView) view.findViewById(R.id.viewpage01_imageview);
              final TextView tv= (TextView) view.findViewById(R.id.viewpage01_textview);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        //开始解析
        JsonObjectRequest request=new JsonObjectRequest(PA,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                try {
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONArray recommend = data.getJSONArray("recommend");
                    HashMap<String,Object> map1;
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
                    //获取完内容了
                    Bundle bundle=getArguments();

                    int a=  bundle.getInt("ss");
                    //设置数据
                   HashMap<String,Object>map= list1.get(a);
                    tv.setText(map.get("title").toString());
                    //设置图片
                    BitmapUtils bitmapUtils = new BitmapUtils(getActivity(), Environment.getExternalStorageDirectory().getAbsolutePath(), 1 / 8.0f, 1024 * 1024 * 1024);
                    bitmapUtils.display(iv, map.get("img").toString());



                } catch (JSONException e) {
                    e.printStackTrace();

                }


            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), "下载失败", Toast.LENGTH_SHORT).show();
            }
        });
requestQueue.add(request);

        return view;
    }

}
