package com.benznestdeveloper.pantipstory.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.benznestdeveloper.pantipstory.dao.RoomDao;
import com.benznestdeveloper.pantipstory.fragment.TopicHitListFragment;
import com.benznestdeveloper.pantipstory.fragment.TopicListFragment;
import com.benznestdeveloper.pantipstory.fragment.TopicTrendListFragment;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyTopicMainViewPagerAdapter extends FragmentStatePagerAdapter {

    private RoomDao mRoomDao;

    public MyTopicMainViewPagerAdapter(FragmentManager fm, RoomDao roomDao) {
        super(fm);
        this.mRoomDao = roomDao;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return TopicHitListFragment.newInstance(mRoomDao);
        } else if (position == 1) {
            return TopicListFragment.newInstance(mRoomDao);
        } else {
            return TopicTrendListFragment.newInstance(mRoomDao);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "กระทู้แนะนำ";
        } else if (position == 1) {
            return "กระทู้ล่าสุด";
        } else if (position == 2) {
            return "Trend";
        }

        return "";
    }
}
