package adapter.cn.ss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangha.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

import bean.JingXuan;
import ss.simple.zi.BitmapHelper;

/**
 * Created by Administrator on 2015/5/1.
 */
public class JingxuanAdapter extends BaseAdapter {
    private Context context;
    private List<JingXuan> jingxuanList;
    public JingxuanAdapter(Context context, List<JingXuan> jingxuanList) {
        this.context=context;
        this.jingxuanList=jingxuanList;
    }

    @Override
    public int getCount() {
        return jingxuanList.size();
    }

    @Override
    public Object getItem(int position) {
        return jingxuanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.caipulistview_listview_item, parent, false);
            view.setTag(new ViewHolder(view));
        }
        ViewHolder holder= (ViewHolder) view.getTag();
        JingXuan jingxuan = jingxuanList.get(position);
        holder.text1.setText(jingxuan.getTitle());
        holder.text2.setText(jingxuan.getContent());
        BitmapHelper.getUtils().display(holder.image, jingxuan.getImg());
        return view;
    }
    public static class ViewHolder{
        @ViewInject(R.id.caipu_item_textView1)
        private TextView text1;
        @ViewInject(R.id.caipu_item_textView2)
        private TextView text2;
        @ViewInject(R.id.caipu_idtem_image)
        private ImageView image;
        public ViewHolder(View itemView){
            ViewUtils.inject(this, itemView);

        }
    }
    public void addAll(List<JingXuan> list){
       // this.jingxuanList.clear();
        this.jingxuanList.addAll(list);
        notifyDataSetChanged();
    }
}
