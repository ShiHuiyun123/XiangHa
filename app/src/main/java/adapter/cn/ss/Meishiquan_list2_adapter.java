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

import bean.Meishiquan;

/**
 * Created by Administrator on 2015/5/3.
 */
public class Meishiquan_list2_adapter extends BaseAdapter {
    private Context context;
    private List<Meishiquan> meishiquanList;
    public Meishiquan_list2_adapter(Context context, List<Meishiquan> meishiquanList) {
        this.context=context;
        this.meishiquanList=meishiquanList;
    }

    @Override
    public int getCount() {
        return meishiquanList.size();
    }

    @Override
    public Object getItem(int position) {
        return meishiquanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.meishiquan_list2_item1, parent, false);
            view.setTag(new ViewHolder(view));
        }
        ViewHolder holder= (ViewHolder) view.getTag();
        Meishiquan meishiquan = meishiquanList.get(position);
        holder.title1.setText(meishiquan.getTitle());
        holder.nickname1.setText(meishiquan.getNickName());
        holder.commentNum1.setText(meishiquan.getCommentNum());
        holder.likeNum1.setText(meishiquan.getLikeNum());
//        BitmapHelper.getUtils().display(holder.image, jingxuan.getImg());
        return view;
    }
    public static class ViewHolder{
        @ViewInject(R.id.title1)
        private TextView title1;
        @ViewInject(R.id.nickname1)
        private TextView nickname1;
        @ViewInject(R.id.commentNum1)
        private TextView commentNum1;
        @ViewInject(R.id.likeNum1)
        private TextView likeNum1;

        @ViewInject(R.id.caipu_idtem_image)
        private ImageView image;
        public ViewHolder(View itemView){
            ViewUtils.inject(this, itemView);

        }
    }
    public void addAll(List<Meishiquan> list){
        // this.jingxuanList.clear();
        for (int i=0;i<list.size();i++){
            if (i>2){
                this.meishiquanList.addAll(list);
                notifyDataSetChanged();
            }
        }
    }
}
