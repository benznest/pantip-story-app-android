package com.benzneststudios.pantipstory2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benzneststudios.pantipstory2.MyCache;
import com.benzneststudios.pantipstory2.R;
import com.benzneststudios.pantipstory2.service.pantip.MyPantipManager;
import com.benzneststudios.pantipstory2.utils.MyUtils;
import com.benzneststudios.pantipstory2.view.MyEmoView;

/**
 * Created by benznest on 02-Oct-17.
 */

public class TopicMoreDialog extends Dialog {

    private int topicId;
    private TextView tvTitle;
    private TextView tvDetail;
    private LinearLayout llCommentContainer;
    private LinearLayout llVoteContainer;
    private LinearLayout llFeelContainer;
    private CommentDialog.OnCommentListener mOnCommentListener;
    private MyPantipManager.OnVoteTopicListener mOnVoteTopicListener;
    private MyPantipManager.OnEmotionCallbackListener mOnEmotionCallbackListener;
    private MyEmoView emoView;

    public TopicMoreDialog(Context context) {
        super(context);
        this.topicId = topicId;
        init();
    }

    public TopicMoreDialog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected TopicMoreDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_topic_more);
        this.setCancelable(true);
        this.getWindow().setBackgroundDrawableResource(R.color.colorBackgroundPrimaryDialog);

        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        initView();
    }

    private void initView() {

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvDetail = (TextView) findViewById(R.id.tv_detail);

        llCommentContainer = (LinearLayout) findViewById(R.id.ll_comment_container);
        llCommentContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCommentDialog();
            }
        });

        llVoteContainer = (LinearLayout) findViewById(R.id.ll_vote_container);
        llVoteContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voteTopic();
            }
        });

        llFeelContainer = (LinearLayout) findViewById(R.id.ll_feel_container);

        llFeelContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleEmoContainer();
            }
        });

        emoView = (MyEmoView) findViewById(R.id.emo_view);
        emoView.setOnEmotionSelectedListener(new MyEmoView.OnEmotionSelectedListener() {
            @Override
            public void onEmotionSelected(String emotion) {
                sendEmotion(emotion);
            }
        });
    }

    private void sendEmotion(String emotion) {
        if (MyCache.isLogin()) {
            MyPantipManager.emotionToTopic(topicId, emotion, mOnEmotionCallbackListener);
        } else {
            MyUtils.showSnackBarFail(getContext(), llCommentContainer, "คุณจำเป็นต้องเข้าสู่ระบบก่อน");
        }
    }

    private void toggleEmoContainer() {
        if (emoView.getVisibility() == View.GONE) {
            emoView.setVisibility(View.VISIBLE);
        } else {
            emoView.setVisibility(View.GONE);
        }
    }

    private void voteTopic() {
        if (MyCache.isLogin()) {
            MyPantipManager.voteTopic(topicId, mOnVoteTopicListener);
            dismiss();
        } else {
            MyUtils.showSnackBarFail(getContext(), llCommentContainer, "คุณจำเป็นต้องเข้าสู่ระบบก่อน");
        }
    }

    public void setTopic(int topicId, String topicTitle) {
        this.topicId = topicId;

        tvTitle.setText(topicTitle);
        tvDetail.setText("กระทู้หมายเลข " + topicId);
    }

    private void openCommentDialog() {
        if (MyCache.isLogin()) {
            CommentDialog dialog = new CommentDialog(getContext(), topicId);
            dialog.setOnCommentListener(mOnCommentListener);
            dialog.show();
            dismiss();
        } else {
            MyUtils.showSnackBarFail(getContext(), llCommentContainer, "คุณจำเป็นต้องเข้าสู่ระบบก่อน");
        }
    }

    public void setOnCommentListener(CommentDialog.OnCommentListener onCommentListener) {
        mOnCommentListener = onCommentListener;
    }

    public void setOnVoteTopicListener(MyPantipManager.OnVoteTopicListener onVoteTopicListener) {
        mOnVoteTopicListener = onVoteTopicListener;
    }

    public void setOnEmotionCallbackListener(MyPantipManager.OnEmotionCallbackListener onEmotionCallbackListener) {
        mOnEmotionCallbackListener = onEmotionCallbackListener;
    }
}
