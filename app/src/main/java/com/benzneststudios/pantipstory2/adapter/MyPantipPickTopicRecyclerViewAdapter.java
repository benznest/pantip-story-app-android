package com.benzneststudios.pantipstory2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.benzneststudios.pantipstory2.dao.pick.Pick3Dao;
import com.benzneststudios.pantipstory2.view.MyRowPantipPickTopicView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benznest on 27-Nov-16.
 */

public class MyPantipPickTopicRecyclerViewAdapter extends RecyclerView.Adapter<MyPantipPickTopicRecyclerViewAdapter.MyViewHolder> {

    private List<Pick3Dao> listData = new ArrayList<>();
    private OnPantipPickTopicListener mOnPantipPickTopicListener;

    public void setListData(List<Pick3Dao> listData) {
        this.listData = listData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View row = new MyRowPantipPickTopicView(viewGroup.getContext());
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {
        viewHolder.row.setPantipPick(listData.get(position));
        viewHolder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnPantipPickTopicListener != null) {
                    mOnPantipPickTopicListener.onTopicSelected(listData.get(position), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private MyRowPantipPickTopicView row;

        private MyViewHolder(View v) {
            super(v);
            this.row = (MyRowPantipPickTopicView) v;

        }
    }

    public void setOnPantipPickTopicListener(OnPantipPickTopicListener onPantipPickTopicListener) {
        mOnPantipPickTopicListener = onPantipPickTopicListener;
    }

    public interface OnPantipPickTopicListener {
        void onTopicSelected(Pick3Dao topic, int index);
    }

}