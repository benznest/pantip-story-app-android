package com.benzneststudios.pantipstory2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.benzneststudios.pantipstory2.MySetting;
import com.benzneststudios.pantipstory2.dao.RoomDao;
import com.benzneststudios.pantipstory2.view.MyRowRoomView;

import java.util.ArrayList;

/**
 * Created by benznest on 27-Nov-16.
 */

public class MyRoomRecyclerViewAdapter extends RecyclerView.Adapter<MyRoomRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<RoomDao> listData = new ArrayList<>();
    private OnRoomListener mOnRoomListener;

    public void setData(ArrayList<RoomDao> listRandomMethod) {
        this.listData = listRandomMethod;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View row = new MyRowRoomView(viewGroup.getContext());
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {
        viewHolder.row.setRoom(listData.get(position));
        viewHolder.row.setShowUsageCount(MySetting.isEnableDisplayRoomUsageCount(viewHolder.row.getContext()));
        viewHolder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnRoomListener != null) {
                    mOnRoomListener.onRoomSelected(listData.get(position), position);
                }
            }
        });
//        viewHolder.row.setOnWalletListener(mOnWalletListener);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private MyRowRoomView row;

        private MyViewHolder(View v) {
            super(v);
            this.row = (MyRowRoomView) v;

        }
    }

    public void setOnRoomListener(OnRoomListener onRoomListener) {
        mOnRoomListener = onRoomListener;
    }

    public interface OnRoomListener {
        void onRoomSelected(RoomDao room, int index);
    }

}