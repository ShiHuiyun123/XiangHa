package ss.simple.zi;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fra.ss.cns.AddPageFragment;

/**
 * Created by aaa on 15-5-1.
 */
public class AddPageAdapter extends FragmentPagerAdapter{


    public AddPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return AddPageFragment.getData(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
