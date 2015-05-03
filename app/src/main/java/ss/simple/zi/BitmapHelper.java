package ss.simple.zi;

import android.content.Context;

import com.lidroid.xutils.BitmapUtils;

/**
 * Created by jash on 15-4-14.
 */
public class BitmapHelper {
    private static BitmapUtils utils;
    public static void init(Context context){
        utils = new BitmapUtils(context);
        utils.configDefaultBitmapMaxSize(100,100);
    }

    public static BitmapUtils getUtils() {
        return utils;
    }
}
