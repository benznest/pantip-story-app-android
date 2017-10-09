package com.benzneststudios.pantipstory2.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benzneststudios.pantipstory2.MyPantipEmotion;
import com.benzneststudios.pantipstory2.R;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyEmoCountView extends FrameLayout {

    private int total = 0;
    private LinearLayout llEmoLikeContainer;
    private TextView tvEmoLike;
    private LinearLayout llEmoLolContainer;
    private TextView tvEmoLol;
    private LinearLayout llEmoLoveContainer;
    private TextView tvEmoLove;
    private LinearLayout llEmoCryContainer;
    private TextView tvEmoCry;
    private LinearLayout llEmoVomitedContainer;
    private TextView tvEmoVomited;
    private LinearLayout llEmoSurprisedContainer;
    private TextView tvEmoSurprised;
    private FrameLayout flEmoCountContainer;
    private int like;
    private int lol;
    private int crying;
    private int love;
    private int surprised;
    private int vomited;

    public MyEmoCountView(Context context) {
        super(context);
        init();
    }

    public MyEmoCountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEmoCountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyEmoCountView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.emo_count_view, this);
        initView();
    }

    private void initView() {
        llEmoLikeContainer = (LinearLayout) findViewById(R.id.ll_emo_like_container);
        tvEmoLike = (TextView) findViewById(R.id.tv_emo_like);
        llEmoLolContainer = (LinearLayout) findViewById(R.id.ll_emo_lol_container);
        tvEmoLol = (TextView) findViewById(R.id.tv_emo_lol);
        llEmoLoveContainer = (LinearLayout) findViewById(R.id.ll_emo_love_container);
        tvEmoLove = (TextView) findViewById(R.id.tv_emo_love);
        llEmoCryContainer = (LinearLayout) findViewById(R.id.ll_emo_cry_container);
        tvEmoCry = (TextView) findViewById(R.id.tv_emo_cry);
        llEmoVomitedContainer = (LinearLayout) findViewById(R.id.ll_emo_vomited_container);
        tvEmoVomited = (TextView) findViewById(R.id.tv_emo_vomited);
        llEmoSurprisedContainer = (LinearLayout) findViewById(R.id.ll_emo_surprised_container);
        tvEmoSurprised = (TextView) findViewById(R.id.tv_emo_surprised);
        flEmoCountContainer = (FrameLayout) findViewById(R.id.fl_emo_count_container);
    }

    public void setEmotion(int like, int lol, int love, int crying, int vomited, int surprised) {
        this.like = like;
        this.lol = lol;
        this.love = love;
        this.crying = crying;
        this.vomited = vomited;
        this.surprised = surprised;
        total = like + lol + love + crying + vomited + surprised;
        setLike(like);
        setLol(lol);
        setLove(love);
        setCrying(crying);
        setVomited(vomited);
        setSurprised(surprised);
    }

    public void increaseEmotion(String emotionType, int value) {
        if (emotionType.equals(MyPantipEmotion.LIKE)) {
            setLike(like + value);
        } else if (emotionType.equals(MyPantipEmotion.LAUGH)) {
            setLol(lol + value);
        } else if (emotionType.equals(MyPantipEmotion.LOVE)) {
            setLol(love + value);
        } else if (emotionType.equals(MyPantipEmotion.IMPRESS)) {
            setLol(crying + value);
        } else if (emotionType.equals(MyPantipEmotion.SCARY)) {
            setLol(vomited + value);
        } else if (emotionType.equals(MyPantipEmotion.SURPRISED)) {
            setLol(surprised + value);
        }
    }

    private void setLike(int like) {
        if (like > 0) {
            llEmoLikeContainer.setVisibility(VISIBLE);
            tvEmoLike.setText("" + like);
        } else {
            llEmoLikeContainer.setVisibility(GONE);
        }
    }

    private void setLol(int lol) {
        if (lol > 0) {
            llEmoLolContainer.setVisibility(VISIBLE);
            tvEmoLol.setText("" + lol);
        } else {
            llEmoLolContainer.setVisibility(GONE);
        }
    }

    private void setLove(int love) {
        if (love > 0) {
            llEmoLoveContainer.setVisibility(VISIBLE);
            tvEmoLove.setText("" + love);
        } else {
            llEmoLoveContainer.setVisibility(GONE);
        }
    }

    private void setCrying(int cry) {
        if (cry > 0) {
            llEmoCryContainer.setVisibility(VISIBLE);
            tvEmoCry.setText("" + cry);
        } else {
            llEmoCryContainer.setVisibility(GONE);
        }
    }

    private void setVomited(int vomited) {
        if (vomited > 0) {
            llEmoVomitedContainer.setVisibility(VISIBLE);
            tvEmoVomited.setText("" + vomited);
        } else {
            llEmoVomitedContainer.setVisibility(GONE);
        }
    }

    private void setSurprised(int surprised) {
        if (surprised > 0) {
            llEmoSurprisedContainer.setVisibility(VISIBLE);
            tvEmoSurprised.setText("" + surprised);
        } else {
            llEmoSurprisedContainer.setVisibility(GONE);
        }
    }
}
