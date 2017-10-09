package com.benzneststudios.pantipstory2.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.benzneststudios.pantipstory2.MyNavigationMenu;
import com.benzneststudios.pantipstory2.R;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyRowNavigationMenuView extends FrameLayout {

    private ImageView imgIcon;
    private TextView tvName;

    public MyRowNavigationMenuView(Context context) {
        super(context);
        init();
    }

    public MyRowNavigationMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRowNavigationMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyRowNavigationMenuView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.row_nav_menu, this);
        initView();
    }

    private void initView() {
        imgIcon = (ImageView) findViewById(R.id.img_icon);
        tvName = (TextView) findViewById(R.id.tv_name);
    }

    public void setNavigationMenu(MyNavigationMenu.MenuDao menu) {
        imgIcon.setImageResource(menu.getIcon());
        tvName.setText(menu.getName());
    }

}
