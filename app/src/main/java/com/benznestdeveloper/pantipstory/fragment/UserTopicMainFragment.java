package com.benznestdeveloper.pantipstory.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benznestdeveloper.pantipstory.R;
import com.benznestdeveloper.pantipstory.adapter.MyUserTopicMainViewPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserTopicMainFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewpager;
    private MyUserTopicMainViewPagerAdapter adapter;

    public static UserTopicMainFragment newInstance() {

        UserTopicMainFragment fragment = new UserTopicMainFragment();

        return fragment;
    }

    public UserTopicMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("หน้าของฉัน");
        View v = inflater.inflate(R.layout.fragment_user_topic_main, container, false);
        initView(v);

        return v;
    }

    private void initView(View v) {
        tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        viewpager = (ViewPager) v.findViewById(R.id.viewpager);
        viewpager.setOffscreenPageLimit(3);

        adapter = new MyUserTopicMainViewPagerAdapter(getChildFragmentManager());
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
    }
}
