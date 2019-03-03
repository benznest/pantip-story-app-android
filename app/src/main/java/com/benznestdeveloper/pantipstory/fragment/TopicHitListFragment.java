package com.benznestdeveloper.pantipstory.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benznestdeveloper.pantipstory.MyConstant;
import com.benznestdeveloper.pantipstory.R;
import com.benznestdeveloper.pantipstory.activity.MainActivity;
import com.benznestdeveloper.pantipstory.adapter.MyTopicRecyclerViewAdapter;
import com.benznestdeveloper.pantipstory.dao.RoomDao;
import com.benznestdeveloper.pantipstory.dao.topic.TopicDao;
import com.benznestdeveloper.pantipstory.service.pantip.MyPantipManager;
import com.benznestdeveloper.pantipstory.view.MyTagView;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopicHitListFragment extends Fragment {

    private RoomDao room;
    private RecyclerView mRecyclerView;
    private MyTopicRecyclerViewAdapter adapter;
    private LinearLayoutManager layoutManager;
    private List<TopicDao> listTopic = new ArrayList<>();
    private CircularProgressBar progress;

    public static TopicHitListFragment newInstance(RoomDao room) {

        Bundle args = new Bundle();
        args.putParcelable(MyConstant.KEY_ROOM, room);

        TopicHitListFragment fragment = new TopicHitListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public TopicHitListFragment() {
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
            loadData();
        }

        return v;
    }

    private void loadData() {
        MyPantipManager.loadHitTopic(room, new MyPantipManager.OnLoadHitTopicListListener() {
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
