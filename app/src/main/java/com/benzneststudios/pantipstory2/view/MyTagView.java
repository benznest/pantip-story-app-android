package com.benzneststudios.pantipstory2.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.benzneststudios.pantipstory2.R;
import com.benzneststudios.pantipstory2.dao.topic.TagDao;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyTagView extends FrameLayout {

    private TextView tvTagName;
    private OnTagListener mOnTagListener;
    private String tagName;

    public MyTagView(Context context) {
        super(context);
        init();
    }

    public MyTagView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyTagView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.tv_tag, this);
        initView();
    }

    private void initView() {
        tvTagName = (TextView) findViewById(R.id.tv_tag_name);
    }

    public void setTagDao(TagDao tag) {
        this.tagName = tag.getTag();
        tvTagName.setText(tag.getTag());
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
        tvTagName.setText(tagName);
    }

    public void setTagBackground(int res) {
        tvTagName.setBackgroundResource(res);
    }

    public void setOnTagListener(OnTagListener onTagListener) {
        mOnTagListener = onTagListener;
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnTagListener.onTagSelected(tagName);
            }
        });
    }

    public interface OnTagListener {
        void onTagSelected(String tagName);
    }

}
