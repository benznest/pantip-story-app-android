package com.benznestdeveloper.pantipstory.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;

import com.benznestdeveloper.pantipstory.MyCache;
import com.benznestdeveloper.pantipstory.MyRoomManager;
import com.benznestdeveloper.pantipstory.MySetting;
import com.benznestdeveloper.pantipstory.MyTheme;
import com.benznestdeveloper.pantipstory.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    private SwitchCompat switchEnableEmotionView;
    private Button btnTheme;
    private Button btnDefaultRoom;
    private SwitchCompat switchEnableRoomUsageCount;

    public static SettingFragment newInstance() {

        Bundle args = new Bundle();

        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(false);
        getActivity().setTitle("ตั้งค่า");
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        switchEnableEmotionView = (SwitchCompat) v.findViewById(R.id.switch_enable_emotion_view);
        switchEnableEmotionView.setChecked(MySetting.isEnableEmotionView(getContext()));
        switchEnableEmotionView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MySetting.setEnableEmotionView(getContext(), b);
            }
        });

        btnTheme = (Button) v.findViewById(R.id.btn_theme);
        btnTheme.setText(MyTheme.getCurrentTheme().getName());
        btnTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChooseThemeDialog();
            }
        });

        btnDefaultRoom = (Button) v.findViewById(R.id.btn_default_room);
        btnDefaultRoom.setText(MyRoomManager.getDisplayRoomName()[MySetting.getDisplayDefaultRoom(getContext())]);
        btnDefaultRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChooseDisplayDefaultRoomDialog();
            }
        });

        switchEnableRoomUsageCount = (SwitchCompat) v.findViewById(R.id.switch_enable_room_usage_count);
        switchEnableRoomUsageCount.setChecked(MySetting.isEnableDisplayRoomUsageCount(getContext()));
        switchEnableRoomUsageCount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MySetting.setEnableDisplayRoomUsageCount(getContext(), b);
            }
        });
    }

    private void openChooseDisplayDefaultRoomDialog() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setSingleChoiceItems(MyRoomManager.getDisplayRoomName(), MySetting.getDisplayDefaultRoom(getContext()), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                MySetting.setDisplayDefaultRoom(getContext(), which);
                btnDefaultRoom.setText(MyRoomManager.getDisplayRoomName()[which]);
            }
        });
        alert.show();
    }

    private void openChooseThemeDialog() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setSingleChoiceItems(MyTheme.getThemeName(), MyCache.getTheme(getContext()), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                MyCache.setTheme(getContext(), which);
                btnTheme.setText(MyTheme.getCurrentTheme().getName());
                getActivity().recreate();
            }
        });
        alert.show();
    }
}
