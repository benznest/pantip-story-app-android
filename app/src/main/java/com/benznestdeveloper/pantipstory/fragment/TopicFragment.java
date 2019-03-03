package com.benznestdeveloper.pantipstory.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benznestdeveloper.pantipstory.MyCache;
import com.benznestdeveloper.pantipstory.MyConstant;
import com.benznestdeveloper.pantipstory.MyGlide;
import com.benznestdeveloper.pantipstory.MyTheme;
import com.benznestdeveloper.pantipstory.MyTopicHistory;
import com.benznestdeveloper.pantipstory.MyTopicOffline;
import com.benznestdeveloper.pantipstory.MyTopicType;
import com.benznestdeveloper.pantipstory.R;
import com.benznestdeveloper.pantipstory.activity.MainActivity;
import com.benznestdeveloper.pantipstory.dao.TopicDataDao;
import com.benznestdeveloper.pantipstory.dao.callbackComment.MessageCallbackCommentDao;
import com.benznestdeveloper.pantipstory.dao.callbackEmotion.MessageCallbackEmotionDao;
import com.benznestdeveloper.pantipstory.dao.callbackReply.MessageReplyCallback;
import com.benznestdeveloper.pantipstory.dao.comment.CommentDao;
import com.benznestdeveloper.pantipstory.dao.topic.TagDao;
import com.benznestdeveloper.pantipstory.dao.topic.TopicDao;
import com.benznestdeveloper.pantipstory.dao.vote.MessageVoteTopicCallbackDao;
import com.benznestdeveloper.pantipstory.dialog.CommentDialog;
import com.benznestdeveloper.pantipstory.dialog.ReplyDialog;
import com.benznestdeveloper.pantipstory.dialog.TopicMoreDialog;
import com.benznestdeveloper.pantipstory.service.pantip.MyPantipManager;
import com.benznestdeveloper.pantipstory.utils.MyUtils;
import com.benznestdeveloper.pantipstory.view.MyEmoCountView;
import com.benznestdeveloper.pantipstory.view.MyRowCommentView;
import com.benznestdeveloper.pantipstory.view.MyTagView;
import com.benznestdeveloper.pantipstory.web.MyPantipWeb;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicFragment extends Fragment {

    private int topicId;
    private String title;
    private String htmlBody = "";
    private int voteTopic;
    private String owner;
    private String timeTopic;
    private List<TagDao> listTag;
    private String urlAvartarOwner;

    private WebView webViewBody;
    private TextView tvTopicTitle;
    private LinearLayout llTagContainer;
    private TextView tvVote;
    private TextView tvOwner;
    private TextView tvTime;
    private LinearLayout llCommentContainer;
    private List<CommentDao> listComment;
    private TextView tvCountComment;
    private ImageView imgAvartarOwner;
    private CircularProgressBar progressBody;
    private boolean bodyLoaded = false;
    private NestedScrollView scrollViewTopic;
    private FloatingActionButton fabComment;
    private LinearLayout llTopicBodyFooterContainer;
    private ImageView icMore;
    private MyEmoCountView emoCountView;
    private boolean loadOffline;

    private AdView adViewTopic1;
    private AdView adViewTopicMain;
    private AdView adViewTopicFooter;

    public static TopicFragment newInstance(int topicId) {

        Bundle args = new Bundle();
        args.putInt(MyConstant.KEY_TOPIC, topicId);
        args.putBoolean(MyConstant.KEY_LOAD_OFFLINE, false);

        TopicFragment fragment = new TopicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static TopicFragment newInstance(int topic, boolean isLoadOffline) {

        Bundle args = new Bundle();
        args.putInt(MyConstant.KEY_TOPIC, topic);
        args.putBoolean(MyConstant.KEY_LOAD_OFFLINE, isLoadOffline);

        TopicFragment fragment = new TopicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public TopicFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.app_name);
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_topic, container, false);
        initView(v);
        initAdsTopic1(v);
        initAdsTopicMain(v);
        initAdsTopicFooter(v);

        Bundle bundle = getArguments();
        if (bundle != null) {
            topicId = bundle.getInt(MyConstant.KEY_TOPIC);
            loadOffline = bundle.getBoolean(MyConstant.KEY_LOAD_OFFLINE);
        }

        loadTopicData();
        return v;
    }

    private void initAdsTopic1(View v) {
        AdRequest.Builder adBuilder = new AdRequest.Builder();
        adViewTopic1 = (AdView) v.findViewById(R.id.adView_topic_1);
        adViewTopic1.loadAd(adBuilder.build());
        adViewTopic1.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                adViewTopic1.setVisibility(View.GONE);
            }
        });
    }

    private void initAdsTopicMain(View v) {
        AdRequest.Builder adBuilder = new AdRequest.Builder();
        adViewTopicMain = (AdView) v.findViewById(R.id.adView_topic_main);
        adViewTopicMain.loadAd(adBuilder.build());
        adViewTopicMain.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                adViewTopicMain.setVisibility(View.GONE);
            }
        });
    }

    private void initAdsTopicFooter(View v) {
        AdRequest.Builder adBuilder = new AdRequest.Builder();
        adViewTopicFooter= (AdView) v.findViewById(R.id.adView_topic_footer);
        adViewTopicFooter.loadAd(adBuilder.build());
        adViewTopicFooter.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                adViewTopicFooter.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_topic, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String url = "https://pantip.com/topic/" + topicId;
        if (item.getItemId() == R.id.action_open_browser) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        } else if (item.getItemId() == R.id.action_copy_link) {
            MyUtils.copyToClipboard(url);
            MyUtils.showSnackBarSuccess(getContext(), tvTopicTitle, "คัดลอกแล้ว");
        } else if (item.getItemId() == R.id.action_share) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Sharing");
            i.putExtra(Intent.EXTRA_TEXT, url);
            startActivity(Intent.createChooser(i, "Share URL"));
        } else if (item.getItemId() == R.id.action_save_topic_offline) {
            saveTopicOffline();
        }
        return true;
    }

    private void loadTopicData() {
        if (loadOffline) {
            TopicDataDao topicOffline = MyTopicOffline.get(topicId);
            if (topicOffline != null) {
                TopicFragment.this.title = topicOffline.getTopic().getDispTopic();
                TopicFragment.this.htmlBody = topicOffline.getHtmlBody();
                TopicFragment.this.listTag = topicOffline.getTopic().getTags();
                TopicFragment.this.owner = topicOffline.getTopic().getAuthor();
                TopicFragment.this.voteTopic = topicOffline.getTopic().getVotes();
                TopicFragment.this.timeTopic = topicOffline.getTopic().getAbbrTitle();
                TopicFragment.this.urlAvartarOwner = null;
                TopicFragment.this.listComment = topicOffline.getComments();
                updateTopicBody();
                updateTopicTag();
                updateComment();
                saveHistory();
            }
        } else {
            loadTopicBody();
            loadComment();
        }
    }

    private void loadTopicBody() {
        MyPantipManager.loadTopic(topicId, new MyPantipManager.OnLoadTopicBodyListener() {
            @Override
            public void onLoaded(String title, List<TagDao> listTag, String htmlBody, int vote, String owner, String time, String avartarOwner) {
                TopicFragment.this.title = title;
                TopicFragment.this.htmlBody = htmlBody;
                TopicFragment.this.listTag = listTag;
                TopicFragment.this.owner = owner;
                TopicFragment.this.voteTopic = vote;
                TopicFragment.this.timeTopic = time;
                TopicFragment.this.urlAvartarOwner = avartarOwner;
                updateTopicBody();
                updateTopicTag();
                saveHistory();
            }

            @Override
            public void onFail() {

            }
        });
    }

    private void saveHistory() {
        TopicDataDao topicData = new TopicDataDao();
        TopicDao topic = createTopicDao(false);
        topicData.setTopic(topic);
        MyTopicHistory.add(topicData);
    }

    private void saveTopicOffline() {
        TopicDataDao topicOffline = new TopicDataDao();
        TopicDao topic = createTopicDao(true);
        topicOffline.setHtmlBody(htmlBody);
        topicOffline.setTopic(topic);
        topicOffline.setComments(listComment);
        MyTopicOffline.add(topicOffline);
        MyUtils.showSnackBarSuccess(getContext(), tvTopicTitle, "บันทึกกระทู้แบบออฟไลน์แล้ว");
    }

    private TopicDao createTopicDao(boolean getComment) {
        TopicDao topic = new TopicDao();
        topic.setId(topicId);
        topic.setDispTopic(title);
        topic.setVotes(voteTopic);
        topic.setAuthor(owner);
        topic.setAbbrTitle(timeTopic);
        topic.setTopicType(MyTopicType.CHAT);
        topic.setTags(listTag);
        if (getComment) {
            topic.setComments(listComment.size());
        }
        return topic;
    }

    private void loadComment() {
        MyPantipManager.loadCommentListInTopic(topicId, new MyPantipManager.OnLoadCommentListListener() {
            @Override
            public void onLoaded(List<CommentDao> listComment) {
                TopicFragment.this.listComment = listComment;
                updateComment();
            }

            @Override
            public void onFail() {

            }
        });
    }

    private void updateComment() {
        try {
            llCommentContainer.removeAllViews();
            if (listComment != null) {
                for (CommentDao cm : listComment) {
                    MyRowCommentView view = new MyRowCommentView(getContext());
                    view.setOnReplyListener(new ReplyDialog.OnReplyListener() {
                        @Override
                        public void onReplySuccess(MessageReplyCallback msg) {
                            MyUtils.showSnackBarSuccess(getContext(), tvTopicTitle, "สำเร็จแล้ว กำลังโหลดใหม่..");
                            loadTopicData();
                        }
                    });
                    view.setComment(topicId, cm);
                    llCommentContainer.addView(view);
                }

                tvCountComment.setText(listComment.size() + " ความคิดเห็น");
            } else {
                tvCountComment.setText("ไม่มีความคิดเห็น");
            }
        } catch (Exception e) {

        }
    }

    private void updateTopicBody() {
        try {
            String mime = "text/html";
            String encoding = "utf-8";

            // For fir image on screen.
            htmlBody = "" +
                    MyPantipWeb.jsImportJquery +
                    MyPantipWeb.cssSpoil +
                    MyPantipWeb.getCssPantipMain(true) +
                    MyPantipWeb.trimBody(htmlBody) +
                    MyPantipWeb.jsEnableSpoil + " ";
            webViewBody.getSettings().setJavaScriptEnabled(true);
            webViewBody.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                webViewBody.getSettings().setAllowUniversalAccessFromFileURLs(true);
                webViewBody.getSettings().setAllowFileAccessFromFileURLs(true);
            }

            webViewBody.loadDataWithBaseURL(null, htmlBody, mime, encoding, null);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvTopicTitle.setText(Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY));
            } else {
                tvTopicTitle.setText(Html.fromHtml(title));
            }

            tvVote.setText("+" + voteTopic);
            tvOwner.setText(owner);
            tvTime.setText(timeTopic);
            MyGlide.load(getContext(), imgAvartarOwner, urlAvartarOwner);

            progressBody.setVisibility(View.GONE);
            bodyLoaded = true;
        } catch (Exception e) {
            //
        }


    }

    private void updateTopicTag() {
        try {
            llTagContainer.removeAllViews();
            if (listTag != null) {
                for (final TagDao tag : listTag) {
                    MyTagView view = new MyTagView(getContext());
                    view.setTagDao(tag);
                    view.setOnTagListener(new MyTagView.OnTagListener() {
                        @Override
                        public void onTagSelected(String tagName) {
                            ((MainActivity) getActivity()).showTagTopicFragment(tagName);
                        }
                    });
                    view.setTagBackground(MyTheme.getResourceIdOnTheme(R.attr.colorOwnerDark));
                    llTagContainer.addView(view);
                }
            }
        } catch (Exception e) {
            //
        }
    }

    private void initView(View v) {
        webViewBody = (WebView) v.findViewById(R.id.webView_body);
        tvTopicTitle = (TextView) v.findViewById(R.id.tv_topic_title);
        llTagContainer = (LinearLayout) v.findViewById(R.id.ll_tag_container);
        tvVote = (TextView) v.findViewById(R.id.tv_vote);
        tvOwner = (TextView) v.findViewById(R.id.tv_owner);
        tvTime = (TextView) v.findViewById(R.id.tv_time);
        llCommentContainer = (LinearLayout) v.findViewById(R.id.ll_comment_container);
        tvCountComment = (TextView) v.findViewById(R.id.tv_count_comment);
        imgAvartarOwner = (ImageView) v.findViewById(R.id.img_avartar_owner);
        progressBody = (CircularProgressBar) v.findViewById(R.id.progress_body);
        scrollViewTopic = (NestedScrollView) v.findViewById(R.id.scroll_view_topic);
        llTopicBodyFooterContainer = (LinearLayout) v.findViewById(R.id.ll_topic_body_footer_container);

        fabComment = (FloatingActionButton) v.findViewById(R.id.fab_comment);
        fabComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCommentDialog();
            }
        });

        scrollViewTopic.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int dx = scrollX - oldScrollX;
                int dy = scrollY - oldScrollY;
                if (dy > 0) {
                    fabComment.hide();
                } else {
                    fabComment.show();
                }
            }
        });

        icMore = (ImageView) v.findViewById(R.id.ic_more);
        icMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bodyLoaded) {
                    TopicMoreDialog dialog = new TopicMoreDialog(getContext());
                    dialog.setOnCommentListener(new CommentDialog.OnCommentListener() {
                        @Override
                        public void onCommentCreated(MessageCallbackCommentDao msg) {
                            MyUtils.showSnackBarSuccess(getContext(), tvTopicTitle, "สำเร็จแล้ว กำลังโหลดใหม่..");
                            loadTopicData();
                        }
                    });
                    dialog.setOnVoteTopicListener(new MyPantipManager.OnVoteTopicListener() {
                        @Override
                        public void onSuccess(MessageVoteTopicCallbackDao msg) {
                            tvVote.setText("+" + (voteTopic + 1));
                            MyUtils.showSnackBarSuccess(getContext(), tvTopicTitle, "โหวตแล้ว");
                        }

                        @Override
                        public void onFail() {
                            MyUtils.showSnackBarFail(getContext(), tvTopicTitle, "ล้มเหลว");
                        }
                    });
                    dialog.setOnEmotionCallbackListener(new MyPantipManager.OnEmotionCallbackListener() {
                        @Override
                        public void onSuccess(MessageCallbackEmotionDao msg) {
                            MyUtils.showSnackBarSuccess(getContext(), tvTopicTitle, "ส่งแล้ว");
                        }

                        @Override
                        public void onFail() {
                            MyUtils.showSnackBarFail(getContext(), tvTopicTitle, "ล้มเหลว");
                        }
                    });
                    dialog.setTopic(topicId, title);
                    dialog.show();
                }
            }
        });

        emoCountView = (MyEmoCountView) v.findViewById(R.id.emo_count_view);
        emoCountView.setVisibility(View.GONE);
    }

    private void openCommentDialog() {
        if (MyCache.isLogin()) {
            CommentDialog dialog = new CommentDialog(getContext(), topicId);
            dialog.setOnCommentListener(new CommentDialog.OnCommentListener() {
                @Override
                public void onCommentCreated(MessageCallbackCommentDao msg) {
                    MyUtils.showSnackBarSuccess(getContext(), tvTopicTitle, "สำเร็จแล้ว กำลังโหลดใหม่..");
                    loadTopicData();
                }
            });
            dialog.show();
        } else {
            MyUtils.showSnackBarFail(getContext(), scrollViewTopic, "คุณจำเป็นต้องเข้าสู่ระบบก่อน");
        }
    }
}
