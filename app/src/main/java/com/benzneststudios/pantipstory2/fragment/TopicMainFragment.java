package com.benzneststudios.pantipstory2.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benzneststudios.pantipstory2.MyConstant;
import com.benzneststudios.pantipstory2.R;
import com.benzneststudios.pantipstory2.adapter.MyTopicMainViewPagerAdapter;
import com.benzneststudios.pantipstory2.dao.RoomDao;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopicMainFragment extends Fragment {

    private RoomDao room;
    private TabLayout tabLayout;
    private ViewPager viewpager;
    private MyTopicMainViewPagerAdapter adapter;

    public static TopicMainFragment newInstance(RoomDao room) {

        Bundle args = new Bundle();
        args.putParcelable(MyConstant.KEY_ROOM, room);
        TopicMainFragment fragment = new TopicMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public TopicMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            room = bundle.getParcelable(MyConstant.KEY_ROOM);
            getActivity().setTitle(room.getName());
        }

        View v = inflater.inflate(R.layout.fragment_topic_main, container, false);
        initView(v);

        return v;
    }

    private void initView(View v) {
        tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        viewpager = (ViewPager) v.findViewById(R.id.viewpager);
        viewpager.setOffscreenPageLimit(3);

        adapter = new MyTopicMainViewPagerAdapter(getChildFragmentManager(),room);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
    }


}
