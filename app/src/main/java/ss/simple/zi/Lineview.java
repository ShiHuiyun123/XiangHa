package ss.simple.zi;
//横线
import android.content.Context;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by aaa on 15-5-4.
 */
public class Lineview extends View {
    private Paint paint;

    public Lineview(Context context) {
        super(context);
        init();
    }

    public Lineview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
         paint = new Paint();
        //给画笔设置颜色
        paint.setColor(Color.parseColor("#B7B7B7"));
        //抗锯齿
        paint.setAntiAlias(true);
        //设置画笔的样式
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画线
        canvas.drawLine(0, 0, 800, 0, paint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
