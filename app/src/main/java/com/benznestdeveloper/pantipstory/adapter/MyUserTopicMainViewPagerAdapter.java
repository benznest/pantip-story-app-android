package com.benznestdeveloper.pantipstory.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.benznestdeveloper.pantipstory.fragment.UserTopicListFragment;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyUserTopicMainViewPagerAdapter extends FragmentStatePagerAdapter {

    public static final int MODE_USER_TOPIC_OWNER = 0;
    public static final int MODE_USER_TOPIC_COMMENT = 1;
    public static final int MODE_USER_TOPIC_FAVORITE = 2;

    public MyUserTopicMainViewPagerAdapter(FragmentManager fm ) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return UserTopicListFragment.newInstance(MODE_USER_TOPIC_OWNER);
        } else if (position == 1) {
            return UserTopicListFragment.newInstance(MODE_USER_TOPIC_COMMENT);
        } else {
            return UserTopicListFragment.newInstance(MODE_USER_TOPIC_FAVORITE);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "กระทู้ที่ฉันตั้ง";
        } else if (position == 1) {
            return "กระทู้ที่ฉันตอบ";
        } else if (position == 2) {
            return "กระทู้โปรดของฉัน";
        }

        return "";
    }
}
