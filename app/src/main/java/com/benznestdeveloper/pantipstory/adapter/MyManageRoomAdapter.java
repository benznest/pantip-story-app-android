package com.benznestdeveloper.pantipstory.adapter;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.benznestdeveloper.pantipstory.MyRoom;
import com.benznestdeveloper.pantipstory.R;
import com.benznestdeveloper.pantipstory.dao.RoomDao;
import com.benznestdeveloper.pantipstory.utils.MyUtils;
import com.woxthebox.draglistview.DragItemAdapter;

import java.util.List;

/**
 * Created by benznest on 04-Oct-17.
 */

public class MyManageRoomAdapter extends DragItemAdapter<RoomDao, MyManageRoomAdapter.ViewHolder> {

    private int mLayoutId;
    private int mGrabHandleId;
    private boolean mDragOnLongPress;

    public MyManageRoomAdapter(Context context, List<RoomDao> list, int layoutId, int grabHandleId, boolean dragOnLongPress) {

        mLayoutId = layoutId;
        mGrabHandleId = grabHandleId;
        mDragOnLongPress = dragOnLongPress;
        setHasStableIds(true);
        setItemList(list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        final RoomDao roomDao = mItemList.get(position);
        MyUtils.log("ROOM id = " + roomDao.getId());

        holder.tvTitle.setText(roomDao.getName());
        holder.tvDetail.setText(roomDao.getDetail());
        holder.img.setImageResource(MyUtils.getDrawable(roomDao.getIcon()));
        holder.tvCount.setText("เข้าชม "+roomDao.getCount()+" ครั้ง");
        holder.switchEnableRoom.setChecked(roomDao.isEnable());
        holder.switchEnableRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomDao.setEnable(holder.switchEnableRoom.isChecked());
                MyRoom.update(roomDao);
            }
        });

    }


    @Override
    public long getItemId(int position) {
        return mItemList.get(position).getId();
    }

    class ViewHolder extends DragItemAdapter.ViewHolder {
        ImageView img;
        TextView tvTitle;
        TextView tvDetail;
        TextView tvCount;
        SwitchCompat switchEnableRoom;

        ViewHolder(final View itemView) {
            super(itemView, mGrabHandleId, mDragOnLongPress);
            img = (ImageView) itemView.findViewById(R.id.img);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDetail = (TextView) itemView.findViewById(R.id.tv_detail);
            tvCount = (TextView) itemView.findViewById(R.id.tv_count);
            switchEnableRoom = (SwitchCompat) itemView.findViewById(R.id.switch_enable_room);
        }

        @Override
        public void onItemClicked(View view) {
            //Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onItemLongClicked(View view) {
            //Toast.makeText(view.getContext(), "Item long clicked", Toast.LENGTH_SHORT).show();
            return true;
        }


    }
}
