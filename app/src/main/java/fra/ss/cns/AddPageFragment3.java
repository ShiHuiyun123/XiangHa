package fra.ss.cns;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.xiangha.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapter.cn.ss.ViewAdapter03;

/**
 * Created by aaa on 15-5-1.
 */
public class AddPageFragment3 extends Fragment {
    private List<HashMap<String,Object>> list1=new ArrayList<>();

    public static  AddPageFragment3 getData(int position)
    {
        AddPageFragment3 addmy=new AddPageFragment3();
        Bundle bundle=new Bundle();
        bundle.putInt("ss", position);
        addmy.setArguments(bundle);
        return addmy;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String PA="http://api.xiangha.com/home5/getIndexData/?type=newData";
     final View view=inflater.inflate(R.layout.viewpage03,container,false);
        final GridView gie    = (GridView) view.findViewById(R.id.viewpage03_gridview);
        //下载数据
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest request=new JsonObjectRequest(PA,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                try {
                    JSONObject data = jsonObject.getJSONObject("data");
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

                        list1.add(map4);
                    }
                     //下载完数据了
                    List<HashMap<String,Object>>list2=new ArrayList<>();
                    List<HashMap<String,Object>>list3=new ArrayList<>();

                    Bundle bundle=getArguments();

                    int a=  bundle.getInt("ss");
                    int count=0;
                   for(HashMap<String,Object>entry:list1)
                   {
                       if(count<5)
                       {
                           list2.add(entry);
                       }else {
                           list3.add(entry);
                       }
                       count++;
                   }

                    //获取完内容了

                    if(a==0)

                    {
                      gie.setAdapter(new ViewAdapter03(getActivity(),list2));

                    }else if(a==1)
                    {
                        gie.setAdapter(new ViewAdapter03(getActivity(),list3));

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


}
