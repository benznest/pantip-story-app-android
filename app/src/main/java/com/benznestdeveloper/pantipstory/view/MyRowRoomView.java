package com.benznestdeveloper.pantipstory.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.benznestdeveloper.pantipstory.R;
import com.benznestdeveloper.pantipstory.dao.RoomDao;
import com.benznestdeveloper.pantipstory.utils.MyUtils;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyRowRoomView extends FrameLayout {

    private RoomDao room;
    private ImageView img;
    private TextView tvTitle;
    private TextView tvDetail;
    private TextView tvCount;

    public MyRowRoomView(Context context) {
        super(context);
        init();
    }

    public MyRowRoomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRowRoomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyRowRoomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.row_room, this);
        initView();
    }

    public void setRoom(RoomDao room) {
        this.room = room;

        img.setImageResource(MyUtils.getDrawable(room.getIcon()));
        tvTitle.setText(room.getName());
        tvDetail.setText(room.getDetail());
        tvCount.setText("เข้าชม "+room.getCount()+" ครั้ง");
    }

    public void setShowUsageCount(boolean show){
        if(show){
            tvCount.setVisibility(VISIBLE);
        }else{
            tvCount.setVisibility(GONE);
        }
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvDetail = (TextView) findViewById(R.id.tv_detail);
        tvCount = (TextView) findViewById(R.id.tv_count);
    }
}
