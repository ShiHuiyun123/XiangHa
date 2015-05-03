package adapter.cn.ss;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangha.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

import java.util.HashMap;
import java.util.List;

/**
 * Created by aaa on 15-5-1.
 */
public class ViewAdapter03  extends BaseAdapter{
    private List<HashMap<String,Object>> list;
    private Context context;

    public ViewAdapter03(Context context, List<HashMap<String, Object>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view , ViewGroup parent) {

      if(view==null)
      {
          view= LayoutInflater.from(context).inflate(R.layout.view03,parent,false);
       view.setTag(new ViewHold(view));
      }
         ViewHold holder= (ViewHold) view.getTag();
        HashMap<String,Object>map=  list.get(position);
        holder.tv.setText(map.get("nickName").toString());

        //下载图片
        BitmapUtils bitmapUtils = new BitmapUtils(context, Environment.getExternalStorageDirectory().getAbsolutePath(), 1 / 8.0f, 1024 * 1024 * 1024);


        bitmapUtils.display(holder.iv, map.get("img").toString(),new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                Bitmap ret = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(ret); //画布
                Paint paint = new Paint(); //画笔
                paint.setAntiAlias(true);

                BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                paint.setShader(shader);
                canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
                imageView.setImageBitmap(ret);
            }

            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

            }
        });



        return view;
    }
    public class ViewHold
    {
        private ImageView iv;
        private TextView tv;
        public ViewHold(View itemview)
        {
            iv= (ImageView) itemview.findViewById(R.id.view03_imageview);
           tv= (TextView) itemview.findViewById(R.id.view03_textview);
        }
    }

}
