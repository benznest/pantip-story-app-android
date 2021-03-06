package com.benznestdeveloper.pantipstory.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.benznestdeveloper.pantipstory.MyTopicOffline;
import com.benznestdeveloper.pantipstory.R;
import com.benznestdeveloper.pantipstory.activity.MainActivity;
import com.benznestdeveloper.pantipstory.adapter.MyTopicRecyclerViewAdapter;
import com.benznestdeveloper.pantipstory.dao.TopicDataDao;
import com.benznestdeveloper.pantipstory.dao.topic.TopicDao;
import com.benznestdeveloper.pantipstory.utils.MyUtils;
import com.benznestdeveloper.pantipstory.view.MyTagView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopicOfflineListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MyTopicRecyclerViewAdapter adapter;
    private LinearLayoutManager layoutManager;
    private TextView tvNoData;
    private ArrayList<TopicDataDao> listTopic;

    public static TopicOfflineListFragment newInstance() {

        TopicOfflineListFragment fragment = new TopicOfflineListFragment();
        return fragment;
    }

    public TopicOfflineListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(false);
        getActivity().setTitle("กระทู้ออฟไลน์");
        View v = inflater.inflate(R.layout.fragment_topic_offline, container, false);
        initView(v);

        return v;
    }


    private void initView(View v) {
        tvNoData = (TextView) v.findViewById(R.id.tv_no_data);
        listTopic = MyTopicOffline.getTopicList();

        if(listTopic.size() == 0){
            tvNoData.setVisibility(View.VISIBLE);
        }else{
            tvNoData.setVisibility(View.GONE);
        }

        layoutManager = new LinearLayoutManager(getContext());

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new MyTopicRecyclerViewAdapter();
        adapter.setListOfflineData(listTopic);
        MyUtils.log("list offline = " + MyTopicOffline.getTopicList().size());

        adapter.setOnTopicListener(new MyTopicRecyclerViewAdapter.OnTopicListener() {
            @Override
            public void onTopicSelected(TopicDao topic, int index) {
                openTopicFragment(topic);
            }
        });
        adapter.setOnTagListener(new MyTagView.OnTagListener() {
            @Override
            public void onTagSelected(String tagName) {
                ((MainActivity) getActivity()).showTagTopicFragment(tagName);
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    private void openTopicFragment(TopicDao topic) {
        ((MainActivity) getActivity()).showTopicFragment(topic.getId(), true);
    }


}
