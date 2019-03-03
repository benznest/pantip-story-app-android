package com.benznestdeveloper.pantipstory.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benznestdeveloper.pantipstory.MyCache;
import com.benznestdeveloper.pantipstory.MyConstant;
import com.benznestdeveloper.pantipstory.R;
import com.benznestdeveloper.pantipstory.activity.MainActivity;
import com.benznestdeveloper.pantipstory.adapter.MyUserTopicMainViewPagerAdapter;
import com.benznestdeveloper.pantipstory.adapter.MyUserTopicRecyclerViewAdapter;
import com.benznestdeveloper.pantipstory.dao.userTopic.MessageUserTopic;
import com.benznestdeveloper.pantipstory.dao.userTopic.TopicResultDao;
import com.benznestdeveloper.pantipstory.service.pantip.MyPantipManager;
import com.benznestdeveloper.pantipstory.view.MyTagView;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserTopicListFragment extends Fragment {

    private int mode;
    private int currentPage = 1;
    private int maxPage = 1;
    private long firstId = 0;
    private long lastId = 0;
    private RecyclerView mRecyclerView;
    private MyUserTopicRecyclerViewAdapter adapter;
    private boolean loading = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private LinearLayoutManager layoutManager;
    private List<TopicResultDao> listTopic = new ArrayList<>();
    private CircularProgressBar progress;

    public static UserTopicListFragment newInstance(int mode) {

        Bundle args = new Bundle();
        args.putInt(MyConstant.KEY_MODE, mode);
        UserTopicListFragment fragment = new UserTopicListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public UserTopicListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_topic_list, container, false);
        init(v);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mode = bundle.getInt(MyConstant.KEY_MODE, 0);
        }

        loadData();

        return v;
    }

    private void loadData() {
        if (mode == MyUserTopicMainViewPagerAdapter.MODE_USER_TOPIC_OWNER) {
            loadDataUserTopicOwner();
        } else if (mode == MyUserTopicMainViewPagerAdapter.MODE_USER_TOPIC_COMMENT) {
            loadDataUserTopicComment();
        } else if (mode == MyUserTopicMainViewPagerAdapter.MODE_USER_TOPIC_FAVORITE) {
            loadDataUserTopicFavorite();
        }
    }

    private void loadDataUserTopicFavorite() {
        long userId = MyCache.getUser(getContext()).getId();
        MyPantipManager.getUserTopicFavorite(userId,
                currentPage,
                firstId,
                lastId,
                new MyPantipManager.OnLoadUserTopicListener() {
                    @Override
                    public void onSuccess(MessageUserTopic msg) {
                        maxPage = msg.getMaxPage();
                        UserTopicListFragment.this.firstId = msg.getFirstId();
                        UserTopicListFragment.this.lastId = msg.getLastId();
                        listTopic.addAll(msg.getResult());
                        adapter.setListData(listTopic);
                        adapter.notifyDataSetChanged();
                        onLoadFinish();
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }

    private void loadDataUserTopicComment() {
        long userId = MyCache.getUser(getContext()).getId();
        MyPantipManager.getUserTopicComment(userId,
                currentPage,
                firstId,
                lastId,
                new MyPantipManager.OnLoadUserTopicListener() {
                    @Override
                    public void onSuccess(MessageUserTopic msg) {
                        maxPage = msg.getMaxPage();
                        UserTopicListFragment.this.firstId = msg.getFirstId();
                        UserTopicListFragment.this.lastId = msg.getLastId();
                        listTopic.addAll(msg.getResult());
                        adapter.setListData(listTopic);
                        adapter.notifyDataSetChanged();
                        onLoadFinish();
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }

    private void loadDataUserTopicOwner() {
        long userId = MyCache.getUser(getContext()).getId();
        MyPantipManager.getUserTopicOwner(userId,
                currentPage,
                firstId,
                lastId,
                new MyPantipManager.OnLoadUserTopicListener() {
            @Override
            public void onSuccess(MessageUserTopic msg) {
                maxPage = msg.getMaxPage();
                UserTopicListFragment.this.firstId = msg.getFirstId();
                UserTopicListFragment.this.lastId = msg.getLastId();
                listTopic.addAll(msg.getResult());
                adapter.setListData(listTopic);
                adapter.notifyDataSetChanged();
                onLoadFinish();
            }

            @Override
            public void onFail() {

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
                            if (currentPage < maxPage) {
                                currentPage++;
                                loadData();
                            }
                        }
                    }
                }
            }
        });

        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new MyUserTopicRecyclerViewAdapter();
        adapter.setOnTopicListener(new MyUserTopicRecyclerViewAdapter.OnTopicListener() {
            @Override
            public void onTopicSelected(TopicResultDao topic, int index) {
                ((MainActivity) getActivity()).showTopicFragment(topic.getTopicId());
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
}
