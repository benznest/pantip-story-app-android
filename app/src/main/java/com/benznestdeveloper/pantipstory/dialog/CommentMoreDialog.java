package com.benznestdeveloper.pantipstory.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benznestdeveloper.pantipstory.MyCache;
import com.benznestdeveloper.pantipstory.R;
import com.benznestdeveloper.pantipstory.dao.comment.CommentDao;
import com.benznestdeveloper.pantipstory.service.pantip.MyPantipManager;
import com.benznestdeveloper.pantipstory.utils.MyUtils;
import com.benznestdeveloper.pantipstory.view.MyEmoView;

/**
 * Created by benznest on 02-Oct-17.
 */

public class CommentMoreDialog extends Dialog {

    private int topicId;
    private TextView tvTitle;
    private TextView tvDetail;
    private LinearLayout llCommentContainer;
    private LinearLayout llVoteContainer;
    private LinearLayout llFeelContainer;
    private CommentDialog.OnCommentListener mOnCommentListener;
    private MyPantipManager.OnVoteCommentListener mOnVoteCommentListener;
    private CommentDao comment;
    private ReplyDialog.OnReplyListener mOnReplyListener;
    private MyEmoView emoView;
    private MyPantipManager.OnEmotionCallbackListener mOnEmotionCallbackListener;

    public CommentMoreDialog(Context context) {
        super(context);
        init();
    }

    public CommentMoreDialog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected CommentMoreDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_comment_more);
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
                openReplyDialog();
            }
        });

        llVoteContainer = (LinearLayout) findViewById(R.id.ll_vote_container);
        llVoteContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voteComment();
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
            MyPantipManager.emotionToComment(topicId, comment.getId(), emotion, mOnEmotionCallbackListener);
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

    private void voteComment() {
        if (MyCache.isLogin()) {
            MyPantipManager.voteComment(topicId, comment.getId(), comment.getCommentNo(), mOnVoteCommentListener);
            dismiss();
        } else {
            MyUtils.showSnackBarFail(getContext(), llCommentContainer, "คุณจำเป็นต้องเข้าสู่ระบบก่อน");
        }
    }

    public void setComment(int topicId, CommentDao comment) {
        this.topicId = topicId;
        this.comment = comment;
        tvTitle.setText("แสดงความคิดเห็น");
        tvDetail.setText("ตอบกลับความคิดเห็นที่ " + comment.getCommentNo());
    }

    private void openReplyDialog() {
        if (MyCache.isLogin()) {
            ReplyDialog dialog = new ReplyDialog(getContext(), topicId, comment);
            dialog.setOnReplyListener(mOnReplyListener);
            dialog.show();
        } else {
            MyUtils.showSnackBarFail(getContext(), tvTitle, "คุณจำเป็นต้องเข้าสู่ระบบก่อน");
        }
    }

    public void setOnReplyListener(ReplyDialog.OnReplyListener onReplyListener) {
        mOnReplyListener = onReplyListener;
    }

    public void setOnVoteCommentListener(MyPantipManager.OnVoteCommentListener onVoteCommentListener) {
        mOnVoteCommentListener = onVoteCommentListener;
    }

    public void setOnEmotionCallbackListener(MyPantipManager.OnEmotionCallbackListener onEmotionCallbackListener) {
        mOnEmotionCallbackListener = onEmotionCallbackListener;
    }
}
