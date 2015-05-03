package fra.ss.cns;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.administrator.xiangha.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import adapter.cn.ss.JingxuanAdapter;
import bean.JingXuan;
import ss.simple.zi.BitmapHelper;


/**
 * Created by Administrator on 2015/5/1.
 */
public class CookBook_ListView_Fragment extends Fragment {
    private RadioButton jingxuan, jkys, prjq, msjx, mszt;
    private View view;
    private JingxuanAdapter adapter;
    private ListView caipuListView;
    private String str = null;
    public static CookBook_ListView_Fragment getCookBook_ListView_Fragment() {
        CookBook_ListView_Fragment cookBook_list_fragment = new CookBook_ListView_Fragment();
        return cookBook_list_fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BitmapHelper.init(getActivity());
        view = View.inflate(getActivity(), R.layout.caipulistview, null);
        init();
       String  str = "http://api.xiangha.com/zhishi5/getNousList/?type=new&page=1";
        getIntent(str);
        adapter = new JingxuanAdapter(getActivity(),  new ArrayList<JingXuan>());
        caipuListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        jingxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<JingXuan> jingxuanList = new ArrayList<>();
               String  str = "http://api.xiangha.com/zhishi5/getNousList/?type=new&page=1";
                getIntent(str);
                adapter = new JingxuanAdapter(getActivity(), new ArrayList<JingXuan>());
                adapter.notifyDataSetChanged();
                caipuListView.setAdapter(adapter);

            }
        });
        jkys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String  str = "http://api.xiangha.com/zhishi5/getNousList/?type=classify&pinyin=ys&page=1";
                getIntent(str);
                adapter = new JingxuanAdapter(getActivity(), new ArrayList<JingXuan>());
                adapter.notifyDataSetChanged();
                caipuListView.setAdapter(adapter);

            }
        });
        prjq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String  str = "http://api.xiangha.com/zhishi5/getNousList/?type=classify&pinyin=jq&page=1";
                getIntent(str);
                adapter = new JingxuanAdapter(getActivity(), new ArrayList<JingXuan>());
                adapter.notifyDataSetChanged();
                caipuListView.setAdapter(adapter);

            }
        });
        msjx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String  str = "http://api.xiangha.com/zhishi5/getNousList/?type=classify&pinyin=jx&page=1";
                getIntent(str);
                adapter = new JingxuanAdapter(getActivity(), new ArrayList<JingXuan>());
                caipuListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        mszt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String  str = "http://api.xiangha.com/zhishi5/getNousList/?type=classify&pinyin=zt&page=1";
                getIntent(str);
                adapter = new JingxuanAdapter(getActivity(), new ArrayList<JingXuan>());
                caipuListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    public void getIntent(String str) {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, str, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    JSONObject object = new JSONObject(responseInfo.result);
                    JSONObject data = object.getJSONObject("data");
                    JSONArray top = data.getJSONArray("nous");
                    List<JingXuan> jingxuanList = new ArrayList<>();
                    for (int i = 0; i < top.length(); i++) {
                        JingXuan jingxuan = new JingXuan();
                        JSONObject item = top.getJSONObject(i);
                        jingxuan.setImg(item.getString("img"));
                        jingxuan.setTitle(item.getString("title"));
                        jingxuan.setContent(item.getString("content"));
                        jingxuan.setCode(item.getString("code"));
                        jingxuanList.add(jingxuan);

                    }
                    //   DbHelper.getUtils().saveOrUpdateAll(jingxuanList);
                    adapter.addAll(jingxuanList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void init() {
        jingxuan = (RadioButton) view.findViewById(R.id.jingxuan);
        jkys = (RadioButton) view.findViewById(R.id.jkys);
        prjq = (RadioButton) view.findViewById(R.id.prjq);
        msjx = (RadioButton) view.findViewById(R.id.msjx);
        mszt = (RadioButton) view.findViewById(R.id.mszt);
        caipuListView = (ListView) view.findViewById(R.id.caipuListView_listView);
    }
}
