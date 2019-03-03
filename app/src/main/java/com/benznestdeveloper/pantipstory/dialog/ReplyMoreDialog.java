package com.benznestdeveloper.pantipstory.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benznestdeveloper.pantipstory.MyCache;
import com.benznestdeveloper.pantipstory.R;
import com.benznestdeveloper.pantipstory.dao.comment.CommentDao;
import com.benznestdeveloper.pantipstory.dao.comment.ReplyDao;
import com.benznestdeveloper.pantipstory.service.pantip.MyPantipManager;
import com.benznestdeveloper.pantipstory.utils.MyUtils;
import com.benznestdeveloper.pantipstory.view.MyEmoView;

/**
 * Created by benznest on 02-Oct-17.
 */

public class ReplyMoreDialog extends Dialog {

    private int topicId;
    private TextView tvTitle;
    private TextView tvDetail;
    private LinearLayout llCommentContainer;
    private LinearLayout llVoteContainer;
    private LinearLayout llFeelContainer;
    private CommentDialog.OnCommentListener mOnCommentListener;
    private MyPantipManager.OnVoteCommentListener mOnVoteCommentListener;
    private FrameLayout llEmoContainer;
    private CommentDao comment;
    private ReplyDao reply;
    private ReplyDialog.OnReplyListener mOnReplyListener;
    private MyEmoView emoView;
    private MyPantipManager.OnEmotionCallbackListener mOnEmotionCallbackListener;

    public ReplyMoreDialog(Context context) {
        super(context);
        init();
    }

    public ReplyMoreDialog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected ReplyMoreDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_reply_more);
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
                voteReply();
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
            MyPantipManager.emotionToReply(topicId, comment.getId(),reply.getReplyId(), emotion,comment.getCommentNo(),reply.getReplyNo(), mOnEmotionCallbackListener);
        } else {
            MyUtils.showSnackBarFail(getContext(), llCommentContainer, "คุณจำเป็นต้องเข้าสู่ระบบก่อน");
        }
    }

    private void toggleEmoContainer() {
        if (llEmoContainer.getVisibility() == View.GONE) {
            llEmoContainer.setVisibility(View.VISIBLE);
        } else {
            llEmoContainer.setVisibility(View.GONE);
        }
    }

    private void voteReply() {
        if (MyCache.isLogin()) {
            MyPantipManager.voteReply(topicId, comment.getId(), comment.getCommentNo(), reply.getReplyId(), reply.getReplyNo(), mOnVoteCommentListener);
            dismiss();
        } else {
            MyUtils.showSnackBarFail(getContext(), llCommentContainer, "คุณจำเป็นต้องเข้าสู่ระบบก่อน");
        }
    }

    public void setReply(int topicId, CommentDao comment, ReplyDao reply) {
        this.topicId = topicId;
        this.comment = comment;
        this.reply = reply;
        tvTitle.setText("ตอบกลับความคิดเห็น");
        tvDetail.setText("ตอบกลับความคิดเห็นที่ " + comment.getCommentNo() + "-" + reply.getReplyNo());
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
