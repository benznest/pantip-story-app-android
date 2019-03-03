package com.benznestdeveloper.pantipstory.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.benznestdeveloper.pantipstory.MyGlide;
import com.benznestdeveloper.pantipstory.R;
import com.benznestdeveloper.pantipstory.dao.pick.Pick3Dao;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyRowPantipPickTopicView extends FrameLayout {

    private ImageView imgPreview;
    private TextView tvTopicTitle;
    private TextView tvMessage;

    public MyRowPantipPickTopicView(Context context) {
        super(context);
        init();
    }

    public MyRowPantipPickTopicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRowPantipPickTopicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyRowPantipPickTopicView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.row_pantip_pick_topic, this);
        initView();
    }

    public void setPantipPick(Pick3Dao pantipPick) {
        String urlImg = pantipPick.getCoverImg();
        if (!urlImg.equals("")) {
            MyGlide.load(getContext(), imgPreview, urlImg);
        } else {
            imgPreview.setImageResource(R.mipmap.ic_app);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvTopicTitle.setText(Html.fromHtml(pantipPick.getDispTopic(), Html.FROM_HTML_MODE_LEGACY));
            tvMessage.setText(Html.fromHtml(pantipPick.getDispMsg(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvTopicTitle.setText(Html.fromHtml(pantipPick.getDispTopic()));
            tvMessage.setText(Html.fromHtml(pantipPick.getDispMsg()));
        }

    }

    private void initView() {
        imgPreview = (ImageView) findViewById(R.id.img_preview);
        tvTopicTitle = (TextView) findViewById(R.id.tv_topic_title);
        tvMessage = (TextView) findViewById(R.id.tv_message);
    }
}
