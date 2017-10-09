package com.benzneststudios.pantipstory2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.benzneststudios.pantipstory2.MyCache;
import com.benzneststudios.pantipstory2.R;
import com.benzneststudios.pantipstory2.dao.UserDao;
import com.benzneststudios.pantipstory2.service.pantip.MyPantipManager;
import com.benzneststudios.pantipstory2.utils.MyUtils;

/**
 * Created by benznest on 02-Oct-17.
 */

public class LoginDialog extends Dialog {
    private ImageView imageView;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private OnLoginListener mOnLoginListener;

    public LoginDialog(Context context) {
        super(context);
        init();
    }

    public LoginDialog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected LoginDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_login);
        this.setCancelable(true);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        initView();
    }

    private void initView() {
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    private void login() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if(email.isEmpty() || password.isEmpty()){
            MyUtils.showSnackBarFail(getContext(),btnLogin,"อีเมลหรือรหัสผ่านไม่ถูกต้อง");
            return;
        }

        MyPantipManager.login(email, password, new MyPantipManager.OnPantipLoginListener() {
            @Override
            public void onSuccess(UserDao user) {
                Log.d("BENZNESTLOG", "Login USER = " + user.getName());
                Log.d("BENZNESTLOG", "Login ID = " + user.getId());
                Log.d("BENZNESTLOG", "Login AVATAR = " + user.getUrlAvartar());

                MyCache.setUser(getContext(), user);
                dismiss();

                if (mOnLoginListener != null) {
                    mOnLoginListener.onLogin(user);
                }
            }

            @Override
            public void onFail() {
                Log.d("BENZNESTLOG", "Login ERROR");
                MyUtils.showSnackBarFail(getContext(),btnLogin,"อีเมลหรือรหัสผ่านไม่ถูกต้อง");
            }
        });
    }

    public void setOnLoginListener(OnLoginListener onLoginListener) {
        mOnLoginListener = onLoginListener;
    }

    public interface OnLoginListener {
        void onLogin(UserDao user);
    }
}
