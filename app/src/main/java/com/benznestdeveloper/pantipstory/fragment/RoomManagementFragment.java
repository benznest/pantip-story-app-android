package com.benznestdeveloper.pantipstory.fragment;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.benznestdeveloper.pantipstory.MyRoom;
import com.benznestdeveloper.pantipstory.R;
import com.benznestdeveloper.pantipstory.adapter.MyManageRoomAdapter;
import com.benznestdeveloper.pantipstory.dao.RoomDao;
import com.benznestdeveloper.pantipstory.utils.MyUtils;
import com.woxthebox.draglistview.DragItem;
import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomManagementFragment extends Fragment {

    private DragListView dragView;
    private MyManageRoomAdapter adapter;

    public static RoomManagementFragment newInstance() {

        Bundle args = new Bundle();

        RoomManagementFragment fragment = new RoomManagementFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public RoomManagementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("จัดการห้อง");
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_room_management, container, false);
        initView(v);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_room_management, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_enable_all_room) {
            MyRoom.setEnableAll(true);
            adapter.setItemList(MyRoom.getAll());
            adapter.notifyDataSetChanged();
        } else if (item.getItemId() == R.id.action_disable_all_room) {
            MyRoom.setEnableAll(false);
            adapter.setItemList(MyRoom.getAll());
            adapter.notifyDataSetChanged();
        }
        return true;
    }

    private void initView(View v) {
        dragView = (DragListView) v.findViewById(R.id.drag_view_room);
        dragView.setLayoutManager(new LinearLayoutManager(getContext()));
        dragView.setLongClickable(true);
        dragView.setCanDragHorizontally(false);
        dragView.setDragListListener(new DragListView.DragListListener() {
            @Override
            public void onItemDragStarted(int position) {
            }

            @Override
            public void onItemDragging(int itemPosition, float x, float y) {

            }

            @Override
            public void onItemDragEnded(int fromPosition, int toPosition) {
                saveRoom();
                MyUtils.showSnackBarSuccess(getContext(), dragView, "บันทึกแล้ว");
            }
        });
        dragView.setSnapDragItemToTouch(true);
        dragView.setCustomDragItem(new MyDragItem(getActivity(), R.layout.row_room_manage));

        adapter = new MyManageRoomAdapter(
                getContext(),
                MyRoom.getAll(),
                R.layout.row_room_manage,
                R.id.ll_room_container,
                true);
        dragView.setAdapter(adapter, true);
    }

    private void saveRoom() {
        ArrayList<RoomDao> listRoom = new ArrayList<>(adapter.getItemList());
        MyRoom.setRoomList(listRoom);
    }

    private static class MyDragItem extends DragItem {

        public MyDragItem(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void onBindDragView(View clickedView, View dragView) {
            String title = ((TextView) clickedView.findViewById(R.id.tv_title)).getText().toString();
            ((TextView) dragView.findViewById(R.id.tv_title)).setText(title);

            String detail = ((TextView) clickedView.findViewById(R.id.tv_detail)).getText().toString();
            ((TextView) dragView.findViewById(R.id.tv_detail)).setText(detail);

            Drawable drawable = ((ImageView) clickedView.findViewById(R.id.img)).getDrawable();
            ImageView img = ((ImageView) dragView.findViewById(R.id.img));
            img.setImageDrawable(drawable);

            ((SwitchCompat) dragView.findViewById(R.id.switch_enable_room)).setVisibility(View.GONE);
        }

        @Override

        public void onMeasureDragView(View clickedView, View dragView) {
            FrameLayout dragCard = ((FrameLayout) dragView.findViewById(R.id.ll_room_container));
            FrameLayout clickedCard = ((FrameLayout) clickedView.findViewById(R.id.ll_room_container));
            int widthDiff = dragCard.getPaddingLeft() - clickedCard.getPaddingLeft() + dragCard.getPaddingRight() -
                    clickedCard.getPaddingRight();
            int heightDiff = dragCard.getPaddingTop() - clickedCard.getPaddingTop() + dragCard.getPaddingBottom() -
                    clickedCard.getPaddingBottom();
            int width = clickedView.getMeasuredWidth() + widthDiff;
            int height = clickedView.getMeasuredHeight() + heightDiff;
            dragView.setLayoutParams(new FrameLayout.LayoutParams(width, height));

            int widthSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
            dragView.measure(widthSpec, heightSpec);
        }

        @Override
        public void onStartDragAnimation(View dragView) {
//            MyCategoryCardView dragCard = ((MyCategoryCardView) dragView.findViewById(R.id.cardView));
//            ObjectAnimator anim = ObjectAnimator.ofFloat(dragCard, "CardElevation", dragCard.getCardElevation(), 40);
//            anim.setInterpolator(new DecelerateInterpolator());
//            anim.setDuration(ANIMATION_DURATION);
//            anim.start();
        }

        @Override
        public void onEndDragAnimation(View dragView) {
//            MyCategoryCardView dragCard = ((MyCategoryCardView) dragView.findViewById(R.id.cardView));
//            ObjectAnimator anim = ObjectAnimator.ofFloat(dragCard, "CardElevation", dragCard.getCardElevation(), 6);
//            anim.setInterpolator(new DecelerateInterpolator());
//            anim.setDuration(ANIMATION_DURATION);
//            anim.start();
        }
    }
}
