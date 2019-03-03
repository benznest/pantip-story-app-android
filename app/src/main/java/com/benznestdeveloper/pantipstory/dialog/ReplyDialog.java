package com.benznestdeveloper.pantipstory.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.benznestdeveloper.pantipstory.R;
import com.benznestdeveloper.pantipstory.dao.callbackReply.MessageReplyCallback;
import com.benznestdeveloper.pantipstory.dao.comment.CommentDao;
import com.benznestdeveloper.pantipstory.service.pantip.MyPantipManager;
import com.benznestdeveloper.pantipstory.utils.MyUtils;

/**
 * Created by benznest on 02-Oct-17.
 */

public class ReplyDialog extends Dialog {
    private long topicId;
    private CommentDao comment;
    private TextView tvDetail;
    private EditText edtReply;
    private Button btnSendReply;
    private OnReplyListener mOnReplyListener;

    public ReplyDialog(Context context, long topicId, CommentDao comment) {
        super(context);
        this.topicId = topicId;
        this.comment = comment;
        init();
    }

    private void init() {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_reply);
        this.setCancelable(true);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        initView();
    }

    private void initView() {
        tvDetail = (TextView) findViewById(R.id.tv_detail);
        tvDetail.setText("ตอบกลับความคิดเห็นที่ " + comment.getCommentNo());

        edtReply = (EditText) findViewById(R.id.edt_reply);

        btnSendReply = (Button) findViewById(R.id.btn_send_reply);
        btnSendReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendReply();
            }
        });
    }

    private void sendReply() {
        String message = edtReply.getText().toString().trim();
        MyPantipManager.reply(topicId, message, comment.getCreatedTime(), comment.getId(), comment.getCommentNo(), new MyPantipManager.OnReplyCallbackListener() {
            @Override
            public void onSuccess(MessageReplyCallback msg) {
                if(mOnReplyListener != null){
                    mOnReplyListener.onReplySuccess(msg);
                }
                dismiss();
            }

            @Override
            public void onFail() {
                dismiss();
                MyUtils.showSnackBarFail(getContext(),btnSendReply,"บางอย่างผิดพลาด");
            }
        });
    }

    public void setOnReplyListener(OnReplyListener onReplyListener) {
        mOnReplyListener = onReplyListener;
    }

    public interface OnReplyListener {
        void onReplySuccess(MessageReplyCallback msg);
    }
}
