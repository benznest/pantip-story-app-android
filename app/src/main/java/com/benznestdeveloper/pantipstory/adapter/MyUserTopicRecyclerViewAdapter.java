package com.benznestdeveloper.pantipstory.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.benznestdeveloper.pantipstory.MyTopicType;
import com.benznestdeveloper.pantipstory.dao.userTopic.TopicResultDao;
import com.benznestdeveloper.pantipstory.view.MyRowUserTopicView;
import com.benznestdeveloper.pantipstory.view.MyTagView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benznest on 27-Nov-16.
 */

public class MyUserTopicRecyclerViewAdapter extends RecyclerView.Adapter<MyUserTopicRecyclerViewAdapter.MyViewHolder> {

    private List<TopicResultDao> listData = new ArrayList<>();
    private OnTopicListener mListener;
    private MyTagView.OnTagListener mOnTagListener;

    public void setListData(List<TopicResultDao> listData) {
        this.listData = listData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View row = new MyRowUserTopicView(viewGroup.getContext());
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {
        viewHolder.row.setTopicType(MyTopicType.getIndexFromStyleName(listData.get(position).getIconTopic()));
        viewHolder.row.setOnTagListener(mOnTagListener);
        viewHolder.row.setTopicDao(listData.get(position));
        viewHolder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onTopicSelected(listData.get(position), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private MyRowUserTopicView row;

        private MyViewHolder(View v) {
            super(v);
            this.row = (MyRowUserTopicView) v;

        }
    }

    public void setOnTagListener(MyTagView.OnTagListener onTagListener) {
        mOnTagListener = onTagListener;
    }

    public void setOnTopicListener(OnTopicListener listener) {
        mListener = listener;
    }

    public interface OnTopicListener {
        void onTopicSelected(TopicResultDao topic, int index);
    }

}