package com.benzneststudios.pantipstory2.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benzneststudios.pantipstory2.MyTopicType;
import com.benzneststudios.pantipstory2.R;
import com.benzneststudios.pantipstory2.dao.topic.TagDao;
import com.benzneststudios.pantipstory2.dao.topic.TopicDao;

import java.util.List;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyRowTopicView extends FrameLayout {

    private TopicDao mTopicDao;
    private ImageView imgType;
    private TextView tvTopicTitle;
    private TextView tvOwner;
    private TextView tvCountComment;
    private LinearLayout llTagContainer;
    private LinearLayout llCountCommentContainer;
    private TextView tvDate;
    private LinearLayout llCountVoteContainer;
    private TextView tvCountVote;
    private MyTagView.OnTagListener mOnTagListener;

    public MyRowTopicView(Context context) {
        super(context);
        init();
    }

    public MyRowTopicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRowTopicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyRowTopicView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.row_topic, this);
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
        llCountVoteContainer = (LinearLayout) findViewById(R.id.ll_count_vote_container);
        tvCountVote = (TextView) findViewById(R.id.tv_count_vote);
    }

    public void setTopicDao(TopicDao topicDao) {
        mTopicDao = topicDao;
        update();
    }

    public void setTopicType(Integer type) {

        if (type != null && type == MyTopicType.HIT_TOPIC) {
            llTagContainer.setVisibility(GONE);
            tvOwner.setVisibility(GONE);
            llCountCommentContainer.setVisibility(GONE);
        } else if (type != null && type == MyTopicType.TREND_TOPIC) {
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
        String title = mTopicDao.getDispTopic();
        if(title == null){
            title = "";
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvTopicTitle.setText(Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvTopicTitle.setText(Html.fromHtml(title));
        }


        tvOwner.setText(mTopicDao.getAuthor());
        tvCountComment.setText("" + mTopicDao.getComments());
        imgType.setImageResource(MyTopicType.getIcon((mTopicDao.getTopicType())));

        tvDate.setText(mTopicDao.getAbbrTitle());
        Integer vote = mTopicDao.getVotes();
        if (vote != null && vote > 0) {
            llCountVoteContainer.setVisibility(VISIBLE);
            tvCountVote.setText("" + vote);
        } else {
            llCountVoteContainer.setVisibility(GONE);
        }

        llTagContainer.removeAllViews();
        List<TagDao> listTag = mTopicDao.getTags();
        if (listTag != null) {
            for (TagDao tag : listTag) {
                MyTagView row = new MyTagView(getContext());
                row.setTagName(tag.getTag());
                row.setOnTagListener(mOnTagListener);
                llTagContainer.addView(row);
            }
        }
    }

    public void setOnTagListener(MyTagView.OnTagListener onTagListener) {
        mOnTagListener = onTagListener;
    }
}
