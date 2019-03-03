package com.benznestdeveloper.pantipstory.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.benznestdeveloper.pantipstory.MyRoom;
import com.benznestdeveloper.pantipstory.MyRoomManager;
import com.benznestdeveloper.pantipstory.MySetting;
import com.benznestdeveloper.pantipstory.R;
import com.benznestdeveloper.pantipstory.activity.MainActivity;
import com.benznestdeveloper.pantipstory.adapter.MyRoomRecyclerViewAdapter;
import com.benznestdeveloper.pantipstory.dao.RoomDao;
import com.benznestdeveloper.pantipstory.sort.MySortManager;
import com.benznestdeveloper.pantipstory.sort.MySortRoom;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoomListFragment extends Fragment {

    RecyclerView mRecyclerView;
    MyRoomRecyclerViewAdapter adapter;
    private ArrayList<RoomDao> listRoom;

    public static RoomListFragment newInstance() {

        Bundle args = new Bundle();

        RoomListFragment fragment = new RoomListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public RoomListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_room_list, container, false);
        init(v);

        int displayDefaultRoom = MySetting.getDisplayDefaultRoom(getContext());
        if (displayDefaultRoom == MyRoomManager.DISPLAY_ROOM_ENABLED) {
            setDataFromMenu(R.id.action_room_enabled);
        } else if (displayDefaultRoom == MyRoomManager.DISPLAY_ROOM_SORT_USAGE) {
            setDataFromMenu(R.id.action_room_usage);
        } else if (displayDefaultRoom == MyRoomManager.DISPLAY_ROOM_ALL) {
            setDataFromMenu(R.id.action_room_all);
        }
        return v;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_room, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setDataFromMenu(item.getItemId());
        return true;
    }

    private void setDataFromMenu(int id) {
        if (id == R.id.action_room_enabled) {
            listRoom = MyRoom.getRoomEnabled();
            adapter.setData(listRoom);
            adapter.notifyDataSetChanged();
        } else if (id == R.id.action_room_all) {
            listRoom = MyRoom.getAll();
            adapter.setData(listRoom);
            adapter.notifyDataSetChanged();
        } else if (id == R.id.action_room_usage) {
            listRoom = MyRoom.getAll();
            Collections.sort(listRoom, new MySortRoom.CompareCount(MySortManager.ORDER_BY_DESCENDING)); // sort
            adapter.setData(listRoom);
            adapter.notifyDataSetChanged();
        }
    }

    private void init(View v) {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new MyRoomRecyclerViewAdapter();
        adapter.setOnRoomListener(new MyRoomRecyclerViewAdapter.OnRoomListener() {
            @Override
            public void onRoomSelected(RoomDao room, int index) {
                ((MainActivity) getActivity()).showTopicMainFragment(room);
            }
        });
        adapter.setData(listRoom);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
