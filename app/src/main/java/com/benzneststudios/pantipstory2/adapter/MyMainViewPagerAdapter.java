package com.benzneststudios.pantipstory2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.benzneststudios.pantipstory2.fragment.PantipPickTopicListFragment;
import com.benzneststudios.pantipstory2.fragment.RoomListFragment;
import com.benzneststudios.pantipstory2.fragment.TopicTrendListFragment;

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
