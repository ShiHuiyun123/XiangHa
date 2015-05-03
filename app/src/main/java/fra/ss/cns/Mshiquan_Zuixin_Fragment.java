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

import adapter.cn.ss.Meishiquan_list2_adapter;
import adapter.cn.ss.Meishiquan_zuixin_list1_Adapter;
import bean.Meishiquan;
import ss.simple.zi.BitmapHelper;

/**
 * Created by Administrator on 2015/5/2.
 */
public class Mshiquan_Zuixin_Fragment extends Fragment {
    private RadioButton zuixin, zuire, guanzhu, shafa, huoyuebang;
    private View view;
    private Meishiquan_zuixin_list1_Adapter adapter1;
    private Meishiquan_list2_adapter adapter2;
    private ListView msq_listview1,msq_listview2;
//    private List<Meishiquan> meishiquans;
    private String str = null;
    public static Mshiquan_Zuixin_Fragment getMshiquan_Zuixin_Fragment() {
        Mshiquan_Zuixin_Fragment mshiquan_zuixin_fragment = new Mshiquan_Zuixin_Fragment();
        return mshiquan_zuixin_fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BitmapHelper.init(getActivity());
        view = View.inflate(getActivity(), R.layout.meishiquan_zuixin, null);
        init();
        zuixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  str = "http://api.xiangha.com/quan5/getSubjectList/?type=new&pageTime=&page=1";
                getIntent(str);
//                meishiquans=new ArrayList<>();
//                System.out.print("1111111111111111111111111111110"+meishiquans);

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
                    System.out.print("////////////////////"+responseInfo.result);
                    JSONArray top = object.getJSONArray("data");
                    List<Meishiquan> meishiquans = new ArrayList<>();
                    for (int i = 0; i < top.length(); i++) {
                        Meishiquan meishi = new Meishiquan();
                        JSONObject item = top.getJSONObject(i);
                        JSONObject item1=item.getJSONObject("customer");
                        meishi.setTitle(item.getString("title"));
                        meishi.setContent(item.getString("content"));
                        meishi.setAddTime(item.getString("addTime"));
                        meishi.setCommentNum(item.getString("commentNum"));
                        meishi.setFloorTime(item.getString("floorTime"));
//                        JSONArray array=item.getJSONArray("imags");
//                        System.out.print(item.getJSONArray("imags")+"4454546567576879879756");
//                        meishi.setImags(item.getString("imags"));
//                        Log.d("0000000000000000000000000000000",item.getString("imags"));
                        meishi.setImgShow(item1.getString("imgShow"));
                        meishi.setIsDish(item.getString("isDish"));
                        meishi.setIsLike(item.getString("isLike"));
                        meishi.setNickName(item1.getString("nickName"));
                        meishi.setTimeShow(item.getString("timeShow"));
                        meishi.setLikeNum(item.getString("likeNum"));
                        meishiquans.add(meishi);

                    }
                    //   DbHelper.getUtils().saveOrUpdateAll(jingxuanList);
//                    adapter1.addAll(meishiquans);
//                    adapter2.addAll(meishiquans);
                    for (int i=0;i<meishiquans.size();i++)
                    {
                        if (i<3){
                            adapter1 = new Meishiquan_zuixin_list1_Adapter(getActivity(), meishiquans);
                            adapter1.notifyDataSetChanged();
                            msq_listview1.setAdapter(adapter1);
                        }else {
                            adapter2=new Meishiquan_list2_adapter(getActivity(),meishiquans);
                            adapter2.notifyDataSetChanged();
                            msq_listview2.setAdapter(adapter2);
                        }
                    }
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
        zuixin = (RadioButton) view.findViewById(R.id.zuixin);
        zuire = (RadioButton) view.findViewById(R.id.zuire);
        guanzhu = (RadioButton) view.findViewById(R.id.guanzhu);
        shafa = (RadioButton) view.findViewById(R.id.shafa);
        huoyuebang = (RadioButton) view.findViewById(R.id.huoyuebang);
        msq_listview1 = (ListView) view.findViewById(R.id.msq_zx_listview1);
        msq_listview2= (ListView) view.findViewById(R.id.msq_zx_listview2);
    }
}
