package com.benzneststudios.pantipstory2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benzneststudios.pantipstory2.R;
import com.benzneststudios.pantipstory2.activity.MainActivity;
import com.benzneststudios.pantipstory2.adapter.MyPantipPickTopicRecyclerViewAdapter;
import com.benzneststudios.pantipstory2.dao.pick.Pick3Dao;
import com.benzneststudios.pantipstory2.service.pantip.MyPantipManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PantipPickTopicListFragment extends Fragment {

    private long time;
    private RecyclerView mRecyclerView;
    private MyPantipPickTopicRecyclerViewAdapter adapter;
    private boolean loading = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private LinearLayoutManager layoutManager;
    private List<Pick3Dao> listPantipPickTopic = new ArrayList<>();

    public static PantipPickTopicListFragment newInstance() {
        PantipPickTopicListFragment fragment = new PantipPickTopicListFragment();
        return fragment;
    }

    public PantipPickTopicListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        time = Calendar.getInstance().getTimeInMillis() / 1000;

        View v = inflater.inflate(R.layout.fragment_pantip_pick_topic_list, container, false);
        init(v);
        loadData();
        return v;
    }

    private void loadData() {
        MyPantipManager.loadPantipPick(time, new MyPantipManager.OnLoadPantipPickListener() {
            @Override
            public void onLoaded(List<Pick3Dao> listPantipPick, long lastTime) {
                time = lastTime;
                listPantipPickTopic.addAll(listPantipPick);
                adapter.setListData(listPantipPickTopic);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }

    private void init(View v) {

        layoutManager = new LinearLayoutManager(getContext());

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loadData();
                        }
                    }
                }
            }
        });

        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new MyPantipPickTopicRecyclerViewAdapter();
        adapter.setOnPantipPickTopicListener(new MyPantipPickTopicRecyclerViewAdapter.OnPantipPickTopicListener() {
            @Override
            public void onTopicSelected(Pick3Dao topic, int index) {
                openTopicFragment(topic.getId());
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    private void openTopicFragment(int topicId) {
        ((MainActivity) getActivity()).showTopicFragment(topicId);
    }


}
