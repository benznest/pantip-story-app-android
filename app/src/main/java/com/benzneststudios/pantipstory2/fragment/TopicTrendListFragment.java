package com.benzneststudios.pantipstory2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benzneststudios.pantipstory2.MyConstant;
import com.benzneststudios.pantipstory2.R;
import com.benzneststudios.pantipstory2.activity.MainActivity;
import com.benzneststudios.pantipstory2.adapter.MyTopicRecyclerViewAdapter;
import com.benzneststudios.pantipstory2.dao.RoomDao;
import com.benzneststudios.pantipstory2.dao.topic.TopicDao;
import com.benzneststudios.pantipstory2.service.pantip.MyPantipManager;
import com.benzneststudios.pantipstory2.view.MyTagView;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopicTrendListFragment extends Fragment {

    private RoomDao room;
    private RecyclerView mRecyclerView;
    private MyTopicRecyclerViewAdapter adapter;
    private LinearLayoutManager layoutManager;
    private List<TopicDao> listTopic = new ArrayList<>();
    private CircularProgressBar progress;

    public static TopicTrendListFragment newInstance() {
        TopicTrendListFragment fragment = new TopicTrendListFragment();
        return fragment;
    }

    public static TopicTrendListFragment newInstance(RoomDao room) {

        Bundle args = new Bundle();
        args.putParcelable(MyConstant.KEY_ROOM, room);

        TopicTrendListFragment fragment = new TopicTrendListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public TopicTrendListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_topic_list, container, false);
        init(v);

        Bundle bundle = getArguments();
        if (bundle != null) {
            room = bundle.getParcelable(MyConstant.KEY_ROOM);
            loadDataTrendTopicOnRoom(room);
        } else {
            loadDataTrendTopicOnHome();
        }

        return v;
    }

    private void loadDataTrendTopicOnHome() {
        MyPantipManager.loadTrendTopic(new MyPantipManager.OnLoadTrendTopicListListener() {
            @Override
            public void onLoaded(List<TopicDao> list) {
                adapter.setListData(list);
                adapter.notifyDataSetChanged();
                onLoadFinish();
            }

            @Override
            public void onFail() {
                onLoadFinish();
            }
        });
    }

    private void loadDataTrendTopicOnRoom(RoomDao room) {
        MyPantipManager.loadTrendTopic(room, new MyPantipManager.OnLoadTrendTopicListListener() {
            @Override
            public void onLoaded(List<TopicDao> list) {
                adapter.setListData(list);
                adapter.notifyDataSetChanged();
                onLoadFinish();
            }

            @Override
            public void onFail() {
                onLoadFinish();
            }
        });
    }

    private void onLoadFinish() {
        if (progress != null) {
            progress.setVisibility(View.GONE);
        }
    }

    private void init(View v) {
        progress = (CircularProgressBar) v.findViewById(R.id.progress);

        layoutManager = new LinearLayoutManager(getContext());

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new MyTopicRecyclerViewAdapter();
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
        ((MainActivity) getActivity()).showTopicFragment(topic.getId());


    }
}
