package com.benznestdeveloper.pantipstory.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.benznestdeveloper.pantipstory.dao.TopicDataDao;
import com.benznestdeveloper.pantipstory.dao.topic.TopicDao;
import com.benznestdeveloper.pantipstory.view.MyRowTopicView;
import com.benznestdeveloper.pantipstory.view.MyTagView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benznest on 27-Nov-16.
 */

public class MyTopicRecyclerViewAdapter extends RecyclerView.Adapter<MyTopicRecyclerViewAdapter.MyViewHolder> {

    private static final int MODE_TOPIC = 0;
    private static final int MODE_TOPIC_OFFLINE = 1;
    private int mode = 0;
    private List<TopicDao> listData = new ArrayList<>();
    private List<TopicDataDao> listOfflineData = new ArrayList<>();
    private OnTopicListener mListener;
    private MyTagView.OnTagListener mOnTagListener;

    public void setListData(List<TopicDao> listData) {
        this.listData = listData;
        this.mode = MODE_TOPIC;
    }

    public void setListOfflineData(List<TopicDataDao> listData) {
        this.listOfflineData = listData;
        this.mode = MODE_TOPIC_OFFLINE;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View row = new MyRowTopicView(viewGroup.getContext());
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {

        TopicDao topic = null;
        if (mode == MODE_TOPIC) {
            topic = listData.get(position);
        } else {
            topic = listOfflineData.get(position).getTopic();
        }

        viewHolder.row.setTopicType(topic.getTopicType());
        viewHolder.row.setOnTagListener(mOnTagListener);
        viewHolder.row.setTopicDao(topic);
        final TopicDao finalTopic = topic;
        viewHolder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onTopicSelected(finalTopic, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mode == MODE_TOPIC) {
            return listData.size();
        } else {
            return listOfflineData.size();
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private MyRowTopicView row;

        private MyViewHolder(View v) {
            super(v);
            this.row = (MyRowTopicView) v;

        }
    }

    public void setOnTopicListener(OnTopicListener listener) {
        mListener = listener;
    }

    public void setOnTagListener(MyTagView.OnTagListener onTagListener) {
        mOnTagListener = onTagListener;
    }

    public interface OnTopicListener {
        void onTopicSelected(TopicDao topic, int index);
    }


}