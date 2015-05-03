package adapter.cn.ss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.xiangha.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

import bean.Meishiquan;

/**
 * Created by Administrator on 2015/5/2.
 */
public class Meishiquan_zuixin_list1_Adapter extends BaseAdapter {
    private Context context;
    private List<Meishiquan> meishiquans;
    public Meishiquan_zuixin_list1_Adapter(Context context, List<Meishiquan> meishiquans) {
        this.context=context;
        this.meishiquans=meishiquans;
    }
    @Override
    public int getCount() {
        return meishiquans.size();
    }

    @Override
    public Object getItem(int position) {
//        if (position>3){
//
//        }
        return meishiquans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.meishiquan_zx_listview_item, parent, false);
            view.setTag(new Meishiquan_zuixin_list1_Adapter.ViewHolder(view));
        }
        ViewHolder holder= (ViewHolder) view.getTag();
        Meishiquan meishiquan = meishiquans.get(position);
        holder.text.setText(meishiquan.getTitle());
        return view;
    }
    public static class ViewHolder{
        @ViewInject(R.id.zx_listview1_text)
        private TextView text;

        public ViewHolder(View itemView){
            ViewUtils.inject(this, itemView);

        }
    }
    public void addAll(List<Meishiquan> list){
        // this.jingxuanList.clear();
        for (int i=0;i<list.size();i++){
            if (i>2){
                this.meishiquans.addAll(list);
                notifyDataSetChanged();
            }
        }
    }
}
