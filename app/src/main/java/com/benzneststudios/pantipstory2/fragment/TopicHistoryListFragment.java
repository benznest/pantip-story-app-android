package com.benzneststudios.pantipstory2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.benzneststudios.pantipstory2.MyTopicHistory;
import com.benzneststudios.pantipstory2.R;
import com.benzneststudios.pantipstory2.activity.MainActivity;
import com.benzneststudios.pantipstory2.adapter.MyTopicRecyclerViewAdapter;
import com.benzneststudios.pantipstory2.dao.TopicDataDao;
import com.benzneststudios.pantipstory2.dao.topic.TopicDao;
import com.benzneststudios.pantipstory2.view.MyTagView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopicHistoryListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MyTopicRecyclerViewAdapter adapter;
    private LinearLayoutManager layoutManager;
    private TextView tvNoData;
    private ArrayList<TopicDataDao> listTopic;

    public static TopicHistoryListFragment newInstance() {

        TopicHistoryListFragment fragment = new TopicHistoryListFragment();
        return fragment;
    }

    public TopicHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        getActivity().setTitle("ประวัติกระทู้");
        View v = inflater.inflate(R.layout.fragment_topic_history, container, false);
        initView(v);
        update();

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_topic_history, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_clear_topic_history) {
            MyTopicHistory.removeAll();
            listTopic = MyTopicHistory.getTopicList();
            adapter.setListOfflineData(listTopic);
            adapter.notifyDataSetChanged();
            update();
        }
        return true;
    }


    private void initView(View v) {
        tvNoData = (TextView) v.findViewById(R.id.tv_no_data);
        listTopic = MyTopicHistory.getTopicList();
        layoutManager = new LinearLayoutManager(getContext());

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new MyTopicRecyclerViewAdapter();
        adapter.setListOfflineData(listTopic);

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

    private void update() {
        if (listTopic.size() == 0) {
            tvNoData.setVisibility(View.VISIBLE);
        } else {
            tvNoData.setVisibility(View.GONE);
        }
    }

    private void openTopicFragment(TopicDao topic) {
        ((MainActivity) getActivity()).showTopicFragment(topic.getId());
    }


}
