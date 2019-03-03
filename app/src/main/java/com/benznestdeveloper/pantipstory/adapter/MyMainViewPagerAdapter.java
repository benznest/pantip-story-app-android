package com.benznestdeveloper.pantipstory.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.benznestdeveloper.pantipstory.fragment.PantipPickTopicListFragment;
import com.benznestdeveloper.pantipstory.fragment.RoomListFragment;
import com.benznestdeveloper.pantipstory.fragment.TopicTrendListFragment;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyMainViewPagerAdapter extends FragmentStatePagerAdapter {

    public MyMainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return RoomListFragment.newInstance();
        }else  if(position == 1){
            return PantipPickTopicListFragment.newInstance();
        }else{
            return TopicTrendListFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "เลือกห้อง";
        }else  if(position == 1){
            return "Pantip pick";
        }else{
            return "Pantip trend";
        }
    }
}
