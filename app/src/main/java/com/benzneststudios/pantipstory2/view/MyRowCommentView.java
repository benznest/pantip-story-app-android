package com.benzneststudios.pantipstory2.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benzneststudios.pantipstory2.MyGlide;
import com.benzneststudios.pantipstory2.MySetting;
import com.benzneststudios.pantipstory2.MyTheme;
import com.benzneststudios.pantipstory2.R;
import com.benzneststudios.pantipstory2.dao.callbackEmotion.MessageCallbackEmotionDao;
import com.benzneststudios.pantipstory2.dao.callbackReply.MessageReplyCallback;
import com.benzneststudios.pantipstory2.dao.comment.CommentDao;
import com.benzneststudios.pantipstory2.dao.comment.ReplyDao;
import com.benzneststudios.pantipstory2.dao.vote.MessageVoteCommentCallbackDao;
import com.benzneststudios.pantipstory2.dialog.CommentMoreDialog;
import com.benzneststudios.pantipstory2.dialog.ReplyDialog;
import com.benzneststudios.pantipstory2.dialog.ReplyMoreDialog;
import com.benzneststudios.pantipstory2.service.pantip.MyPantipManager;
import com.benzneststudios.pantipstory2.utils.MyUtils;
import com.benzneststudios.pantipstory2.web.MyPantipWeb;

import java.util.List;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyRowCommentView extends FrameLayout {

    private Integer vote;
    private ImageView imgAvartarOwner;
    private ReplyDao reply;
    private CommentDao comment;
    private int topicId;
    private List<ReplyDao> listReply;

    private TextView tvCommentTitle;
    private WebView webViewComment;
    private TextView tvVote;
    private TextView tvOwner;
    private TextView tvTime;
    private String htmlBody;
    private Button btnShowSubComment;
    private LinearLayout llReplyContainer;
    private LinearLayout llMore;
    private ReplyDialog.OnReplyListener mOnReplyListener;
    private LinearLayout llCommentContainer;
    private LinearLayout llCommentFooterContainer;
    private MyEmoCountView emoCountView;

    public MyRowCommentView(Context context) {
        super(context);
        init();
    }

    public MyRowCommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRowCommentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyRowCommentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.row_comment, this);
        initView();
    }

    private void initView() {
        tvCommentTitle = (TextView) findViewById(R.id.tv_comment_title);
        webViewComment = (WebView) findViewById(R.id.webView_comment);
        tvVote = (TextView) findViewById(R.id.tv_vote);
        tvOwner = (TextView) findViewById(R.id.tv_owner);
        tvTime = (TextView) findViewById(R.id.tv_time);
        btnShowSubComment = (Button) findViewById(R.id.btn_show_sub_comment);
        btnShowSubComment.setVisibility(GONE);
        btnShowSubComment.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                displayCommentReply();
            }
        });
        imgAvartarOwner = (ImageView) findViewById(R.id.img_avartar_owner);
        llReplyContainer = (LinearLayout) findViewById(R.id.ll_reply_container);
        llMore = (LinearLayout) findViewById(R.id.ll_more);
        llMore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reply == null) {
                    openCommentMoreDialog();
                } else {
                    openReplyMoreDialog();
                }
            }
        });
        llCommentContainer = (LinearLayout) findViewById(R.id.ll_comment_container);
        llCommentFooterContainer = (LinearLayout) findViewById(R.id.ll_comment_footer_container);
        emoCountView = (MyEmoCountView) findViewById(R.id.emo_count_view);
    }

    public void setReply(int topicId, CommentDao comment, ReplyDao reply) {
        this.topicId = topicId;
        this.comment = comment;
        this.reply = reply;
        this.vote = comment.getGoodBadVote().getPoint();

        tvCommentTitle.setText("ความคิดเห็นที่ " + reply.getCommentNo() + "-" + reply.getReplyNo());
        tvVote.setText("+" + reply.getGoodBadVote().getPoint());
        tvOwner.setText("" + reply.getUser().getName());
        tvTime.setText("" + reply.getDataUtime());

        if (vote > 0) {
            tvVote.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        } else {
            tvVote.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey));
        }

        String mime = "text/html";
        String encoding = "utf-8";

        // For fir image on screen.
        htmlBody = "" +
                MyPantipWeb.jsImportJquery +
                MyPantipWeb.cssSpoil +
                MyPantipWeb.getCssPantipMain(reply.getOwnerTopic()) +
                MyPantipWeb.trimBody(reply.getMessage()) +
                MyPantipWeb.jsEnableSpoil + " ";
        webViewComment.loadDataWithBaseURL(null, htmlBody, mime, encoding, null);
        btnShowSubComment.setVisibility(GONE);

        if (reply.getEmotion().getSum() > 0 && MySetting.isEnableEmotionView(getContext())) {
            emoCountView.setVisibility(VISIBLE);
            emoCountView.setEmotion(
                    reply.getEmotion().getLike().getCount(),
                    reply.getEmotion().getLaugh().getCount(),
                    reply.getEmotion().getLove().getCount(),
                    reply.getEmotion().getImpress().getCount(),
                    reply.getEmotion().getScary().getCount(),
                    reply.getEmotion().getSurprised().getCount());
        } else {
            emoCountView.setVisibility(GONE);
        }

        if (reply.getOwnerTopic() != null && reply.getOwnerTopic()) {
            llCommentContainer.setBackgroundResource(MyTheme.getResourceIdOnTheme(R.attr.colorOwner));
            llCommentFooterContainer.setBackgroundResource(MyTheme.getResourceIdOnTheme(R.attr.colorOwnerDark));
            emoCountView.setBackgroundResource(MyTheme.getResourceIdOnTheme(R.attr.colorOwnerDark));
        } else {
            llCommentContainer.setBackgroundResource(MyTheme.getResourceIdOnTheme(R.attr.colorPrimary));
            llCommentFooterContainer.setBackgroundResource(MyTheme.getResourceIdOnTheme(R.attr.colorPrimaryDark));
            emoCountView.setBackgroundResource(MyTheme.getResourceIdOnTheme(R.attr.colorPrimaryDark));
        }
    }

    public void setComment(int topicId, CommentDao comment) {
        this.topicId = topicId;
        this.comment = comment;
        this.vote = comment.getGoodBadVote().getPoint();
        tvCommentTitle.setText("ความคิดเห็นที่ " + comment.getCommentNo());
        tvOwner.setText("" + comment.getUser().getName());
        tvTime.setText("" + comment.getDataUtime());
        tvVote.setText("+" + vote);

        try {
            MyGlide.load(getContext(), imgAvartarOwner, comment.getUser().getAvatar().getMedium());
        } catch (Exception e) {
            //
        }

        if (vote > 0) {
            tvVote.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        } else {
            tvVote.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey));
        }

        String mime = "text/html";
        String encoding = "utf-8";

        // For fir image on screen.
        htmlBody = "" +
                MyPantipWeb.jsImportJquery +
                MyPantipWeb.cssSpoil +
                MyPantipWeb.getCssPantipMain(comment.getOwnerTopic()) +
                comment.getMessage() +
                MyPantipWeb.jsEnableSpoil + " ";
        webViewComment.getSettings().setJavaScriptEnabled(true);
        webViewComment.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webViewComment.loadDataWithBaseURL(null, htmlBody, mime, encoding, null);

        listReply = comment.getReplies();
        if (listReply != null && listReply.size() > 0) {
            btnShowSubComment.setText("ดู " + listReply.size() + " ความเห็นย่อย");
            btnShowSubComment.setVisibility(VISIBLE);
        } else {
            btnShowSubComment.setVisibility(GONE);
        }

        if (comment.getEmotion().getSum() > 0 && MySetting.isEnableEmotionView(getContext())) {
            emoCountView.setVisibility(VISIBLE);
            emoCountView.setEmotion(
                    comment.getEmotion().getLike().getCount(),
                    comment.getEmotion().getLaugh().getCount(),
                    comment.getEmotion().getLove().getCount(),
                    comment.getEmotion().getImpress().getCount(),
                    comment.getEmotion().getScary().getCount(),
                    comment.getEmotion().getSurprised().getCount());
        } else {
            emoCountView.setVisibility(GONE);
        }

        if (comment.getOwnerTopic() != null && comment.getOwnerTopic()) {
            llCommentContainer.setBackgroundResource(MyTheme.getResourceIdOnTheme(R.attr.colorOwner));
            llCommentFooterContainer.setBackgroundResource(MyTheme.getResourceIdOnTheme(R.attr.colorOwnerDark));
            emoCountView.setBackgroundResource(MyTheme.getResourceIdOnTheme(R.attr.colorOwnerDark));
        } else {
            llCommentContainer.setBackgroundResource(MyTheme.getResourceIdOnTheme(R.attr.colorPrimary));
            llCommentFooterContainer.setBackgroundResource(MyTheme.getResourceIdOnTheme(R.attr.colorPrimaryDark));
            emoCountView.setBackgroundResource(MyTheme.getResourceIdOnTheme(R.attr.colorPrimaryDark));
        }
    }

    private void displayCommentReply() {
        llReplyContainer.removeAllViews();
        for (ReplyDao reply : listReply) {
            MyRowCommentView row = new MyRowCommentView(getContext());
            row.setOnReplyListener(mOnReplyListener);
            row.setReply(topicId, comment, reply);
            llReplyContainer.addView(row);
        }
        btnShowSubComment.setVisibility(GONE);
    }

    public void setOnReplyListener(ReplyDialog.OnReplyListener onReplyListener) {
        mOnReplyListener = onReplyListener;
    }

    public void openCommentMoreDialog() {
        final CommentMoreDialog dialog = new CommentMoreDialog(getContext());
        dialog.setOnReplyListener(new ReplyDialog.OnReplyListener() {
            @Override
            public void onReplySuccess(MessageReplyCallback msg) {
                if (mOnReplyListener != null) {
                    mOnReplyListener.onReplySuccess(msg);
                }
                dialog.dismiss();
            }
        });
        dialog.setOnVoteCommentListener(new MyPantipManager.OnVoteCommentListener() {
            @Override
            public void onSuccess(MessageVoteCommentCallbackDao msg) {
                tvVote.setText("+" + (comment.getGoodBadVote().getPoint() + 1));
                MyUtils.showSnackBarSuccess(getContext(), tvCommentTitle, "โหวตแล้ว");
            }

            @Override
            public void onFail() {
                MyUtils.showSnackBarFail(getContext(), tvCommentTitle, "ล้มเหลว");
            }
        });
        dialog.setOnEmotionCallbackListener(new MyPantipManager.OnEmotionCallbackListener() {
            @Override
            public void onSuccess(MessageCallbackEmotionDao msg) {
                if (MySetting.isEnableEmotionView(getContext())) {
                    emoCountView.setVisibility(VISIBLE);
                    emoCountView.increaseEmotion(msg.getEmotion().getEmoType(), 1);
                }

                MyUtils.showSnackBarSuccess(getContext(), tvCommentTitle, "ส่งแล้ว");
                dialog.dismiss();
            }

            @Override
            public void onFail() {
                MyUtils.showSnackBarFail(getContext(), tvCommentTitle, "ล้มเหลว");
                dialog.dismiss();
            }
        });
        dialog.setComment(topicId, comment);
        dialog.show();
    }

    public void openReplyMoreDialog() {
        final ReplyMoreDialog dialog = new ReplyMoreDialog(getContext());
        dialog.setOnReplyListener(mOnReplyListener);
        dialog.setOnVoteCommentListener(new MyPantipManager.OnVoteCommentListener() {
            @Override
            public void onSuccess(MessageVoteCommentCallbackDao msg) {
                tvVote.setText("+" + (comment.getGoodBadVote().getPoint() + 1));
                MyUtils.showSnackBarSuccess(getContext(), tvCommentTitle, "โหวตแล้ว");
            }

            @Override
            public void onFail() {
                MyUtils.showSnackBarFail(getContext(), tvCommentTitle, "ล้มเหลว");
            }
        });
        dialog.setOnEmotionCallbackListener(new MyPantipManager.OnEmotionCallbackListener() {
            @Override
            public void onSuccess(MessageCallbackEmotionDao msg) {
                if (MySetting.isEnableEmotionView(getContext())) {
                    emoCountView.setVisibility(VISIBLE);
                    emoCountView.increaseEmotion(msg.getEmotion().getEmoType(), 1);
                }

                MyUtils.showSnackBarSuccess(getContext(), tvCommentTitle, "ส่งแล้ว");
                dialog.dismiss();
            }

            @Override
            public void onFail() {
                MyUtils.showSnackBarFail(getContext(), tvCommentTitle, "ล้มเหลว");
                dialog.dismiss();
            }
        });
        dialog.setReply(topicId, comment, reply);
        dialog.show();
    }
}
