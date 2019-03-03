package com.benznestdeveloper.pantipstory.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.benznestdeveloper.pantipstory.MyPantipEmotion;
import com.benznestdeveloper.pantipstory.R;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyEmoView extends FrameLayout {

    private FrameLayout flEmoLike;
    private FrameLayout flEmoLol;
    private FrameLayout flEmoLove;
    private FrameLayout flEmoCrying;
    private FrameLayout flEmoVomited;
    private FrameLayout flEmoSurprised;
    private OnEmotionSelectedListener mOnEmotionSelectedListener;

    public MyEmoView(Context context) {
        super(context);
        init();
    }

    public MyEmoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEmoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyEmoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.emo_view, this);
        initView();
    }

    private void initView() {
        flEmoLike = (FrameLayout) findViewById(R.id.fl_emo_like);
        flEmoLike.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onEmotionSelected(MyPantipEmotion.LIKE);
            }
        });

        flEmoLol = (FrameLayout) findViewById(R.id.fl_emo_lol);
        flEmoLol.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onEmotionSelected(MyPantipEmotion.LAUGH);
            }
        });

        flEmoLove = (FrameLayout) findViewById(R.id.fl_emo_love);
        flEmoLove.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onEmotionSelected(MyPantipEmotion.LOVE);
            }
        });

        flEmoCrying = (FrameLayout) findViewById(R.id.fl_emo_crying);
        flEmoCrying.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onEmotionSelected(MyPantipEmotion.IMPRESS);
            }
        });

        flEmoVomited = (FrameLayout) findViewById(R.id.fl_emo_vomited);
        flEmoVomited.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onEmotionSelected(MyPantipEmotion.SCARY);
            }
        });

        flEmoSurprised = (FrameLayout) findViewById(R.id.fl_emo_surprised);
        flEmoSurprised.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onEmotionSelected(MyPantipEmotion.SURPRISED);
            }
        });
    }

    private void onEmotionSelected(String emo) {
        if (mOnEmotionSelectedListener != null) {
            mOnEmotionSelectedListener.onEmotionSelected(emo);
        }
    }

    public void setOnEmotionSelectedListener(OnEmotionSelectedListener onEmotionSelectedListener) {
        mOnEmotionSelectedListener = onEmotionSelectedListener;
    }

    public interface OnEmotionSelectedListener {
        void onEmotionSelected(String emotion);
    }
}
