package com.benznestdeveloper.pantipstory.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benznestdeveloper.pantipstory.MyTopicType;
import com.benznestdeveloper.pantipstory.R;
import com.benznestdeveloper.pantipstory.dao.userTopic.TagDao;
import com.benznestdeveloper.pantipstory.dao.userTopic.TopicResultDao;

import java.util.List;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyRowUserTopicView extends FrameLayout {

    private TopicResultDao mTopicDao;
    private ImageView imgType;
    private TextView tvTopicTitle;
    private TextView tvOwner;
    private TextView tvCountComment;
    private LinearLayout llTagContainer;
    private LinearLayout llCountCommentContainer;
    private TextView tvDate;
    private TextView tvCountVote;
    private MyTagView.OnTagListener mOnTagListener;

    public MyRowUserTopicView(Context context) {
        super(context);
        init();
    }

    public MyRowUserTopicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRowUserTopicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyRowUserTopicView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.row_user_topic, this);
        initView();
    }

    private void initView() {
        imgType = (ImageView) findViewById(R.id.img_type);
        tvTopicTitle = (TextView) findViewById(R.id.tv_topic_title);
        tvOwner = (TextView) findViewById(R.id.tv_owner);
        tvCountComment = (TextView) findViewById(R.id.tv_count_comment);
        llTagContainer = (LinearLayout) findViewById(R.id.ll_tag_container);
        llCountCommentContainer = (LinearLayout) findViewById(R.id.ll_count_comment_container);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvCountVote = (TextView) findViewById(R.id.tv_count_vote);
    }

    public void setTopicDao(TopicResultDao topicDao) {
        mTopicDao = topicDao;
        update();
    }

    public void setTopicType(int type) {
        if (type == MyTopicType.HIT_TOPIC) {
            llTagContainer.setVisibility(GONE);
            tvOwner.setVisibility(GONE);
            llCountCommentContainer.setVisibility(GONE);
        } else if (type == MyTopicType.TREND_TOPIC) {
            llTagContainer.setVisibility(GONE);
            tvOwner.setVisibility(GONE);
            llCountCommentContainer.setVisibility(GONE);
        } else {
            llTagContainer.setVisibility(VISIBLE);
            tvOwner.setVisibility(VISIBLE);
            llCountCommentContainer.setVisibility(VISIBLE);
        }
    }

    private void update() {
        tvTopicTitle.setText(mTopicDao.getDispTopic());
        tvOwner.setText(mTopicDao.getAuthor());
        tvDate.setText(mTopicDao.getAbbrTitle());
        tvCountComment.setText("" + mTopicDao.getComments());
        tvCountVote.setText("" + mTopicDao.getVotes());
        imgType.setImageResource(MyTopicType.getIconFromStyleName(mTopicDao.getIconTopic()));

        llTagContainer.removeAllViews();
        List<TagDao> listTag = mTopicDao.getTags();
        for (TagDao tag : listTag) {
            MyTagView row = new MyTagView(getContext());
            row.setTagName(tag.getTag());
            row.setOnTagListener(mOnTagListener);
            llTagContainer.addView(row);
        }
    }

    public void setOnTagListener(MyTagView.OnTagListener onTagListener) {
        mOnTagListener = onTagListener;
    }
}
