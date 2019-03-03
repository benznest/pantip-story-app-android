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
import com.benznestdeveloper.pantipstory.dao.callbackComment.MessageCallbackCommentDao;
import com.benznestdeveloper.pantipstory.service.pantip.MyPantipManager;
import com.benznestdeveloper.pantipstory.utils.MyUtils;

/**
 * Created by benznest on 02-Oct-17.
 */

public class CommentDialog extends Dialog {
    private long topicId;
    private TextView tvDetail;
    private EditText edtComment;
    private Button btnSendComment;
    private OnCommentListener mOnCommentListener;

    public CommentDialog(Context context, long topicId) {
        super(context);
        this.topicId = topicId;
        init();
    }

    private void init() {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_comment);
        this.setCancelable(true);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        initView();
    }

    private void initView() {
        tvDetail = (TextView) findViewById(R.id.tv_detail);
        tvDetail.setText("กระทู้หมายเลข " + topicId);

        edtComment = (EditText) findViewById(R.id.edt_comment);
        btnSendComment = (Button) findViewById(R.id.btn_send_comment);
        btnSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendComment();
            }
        });
    }

    private void sendComment() {
        String message = edtComment.getText().toString().trim();
        MyPantipManager.comment(topicId, message, new MyPantipManager.OnCommentCallbackListener() {
            @Override
            public void onSuccess(MessageCallbackCommentDao msg) {
                if(mOnCommentListener != null){
                    mOnCommentListener.onCommentCreated(msg);
                }
                dismiss();
            }

            @Override
            public void onFail() {
                dismiss();
                MyUtils.showSnackBarFail(getContext(),btnSendComment,"บางอย่างผิดพลาด");
            }
        });
    }

    public void setOnCommentListener(OnCommentListener onCommentListener) {
        mOnCommentListener = onCommentListener;
    }

    public interface OnCommentListener{
        void onCommentCreated(MessageCallbackCommentDao msg);
    }
}
