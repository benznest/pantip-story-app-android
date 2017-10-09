package com.benzneststudios.pantipstory2.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.benzneststudios.pantipstory2.MyConstant;
import com.benzneststudios.pantipstory2.MyTheme;
import com.benzneststudios.pantipstory2.R;
import com.benzneststudios.pantipstory2.utils.MyUtils;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setTheme(MyTheme.getCurrentTheme().getTheme());
        setContentView(R.layout.activity_splash_screen);

        Uri uri = getIntent().getData();
        if (uri != null) {
            String path = uri.toString();
            MyUtils.log("URI = " + path);

            if (path.contains("/topic/")) {
                // Example : https://pantip.com/topic/36936146?x=...
                // Topic ID = 36936146
                String[] str = path.split("/");
                String topic = str[str.length - 1];
                String[] str2 = topic.split("\\?");
                int topicId = Integer.parseInt(str2[0]);
                MyUtils.log("TOPIC ID = " + topicId);

                navigateToMainActivityWithTopic(topicId);
            } else if (path.contains("/tag/")) {
                // Example : https://pantip.com/tag/Google_Drive?x=...
                // TAG = Google_Drive
                String[] str = path.split("/");
                String topic = str[str.length - 1];
                String[] str2 = topic.split("\\?");
                String tag = str2[0];
                MyUtils.log("TAG = " + tag);

                navigateToMainActivityWithTag(tag);
            } else {
                navigateToMainActivity();
            }
        } else {
            navigateToMainActivity();
        }
    }

    private void navigateToMainActivityWithTag(String tag) {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        intent.putExtra(MyConstant.KEY_TOPIC_TAG, tag);
        startActivity(intent);
        finish();
    }

    private void navigateToMainActivityWithTopic(int topicId) {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        intent.putExtra(MyConstant.KEY_TOPIC, topicId);
        startActivity(intent);
        finish();
    }

    private void navigateToMainActivity() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 500);
    }
}
