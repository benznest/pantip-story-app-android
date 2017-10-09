package com.benzneststudios.pantipstory2.service.pantip;

import android.util.Log;

import com.benzneststudios.pantipstory2.MyCustomPantipFactory;
import com.benzneststudios.pantipstory2.MyPantipEmotion;
import com.benzneststudios.pantipstory2.MyPantipVote;
import com.benzneststudios.pantipstory2.MyTopicType;
import com.benzneststudios.pantipstory2.dao.RoomDao;
import com.benzneststudios.pantipstory2.dao.UserDao;
import com.benzneststudios.pantipstory2.dao.callbackComment.MessageCallbackCommentDao;
import com.benzneststudios.pantipstory2.dao.callbackEmotion.MessageCallbackEmotionDao;
import com.benzneststudios.pantipstory2.dao.callbackReply.MessageReplyCallback;
import com.benzneststudios.pantipstory2.dao.comment.CommentDao;
import com.benzneststudios.pantipstory2.dao.comment.MessageCommentListDao;
import com.benzneststudios.pantipstory2.dao.pick.MessagePantipPickDao;
import com.benzneststudios.pantipstory2.dao.pick.Pick3Dao;
import com.benzneststudios.pantipstory2.dao.tag.MessageTagTopicDao;
import com.benzneststudios.pantipstory2.dao.topic.MessageTopicListDao;
import com.benzneststudios.pantipstory2.dao.topic.TagDao;
import com.benzneststudios.pantipstory2.dao.topic.TopicDao;
import com.benzneststudios.pantipstory2.dao.trend.MessageTrendDao;
import com.benzneststudios.pantipstory2.dao.trend.TrendDao;
import com.benzneststudios.pantipstory2.dao.userTopic.MessageUserTopic;
import com.benzneststudios.pantipstory2.dao.vote.MessageVoteCommentCallbackDao;
import com.benzneststudios.pantipstory2.dao.vote.MessageVoteTopicCallbackDao;
import com.benzneststudios.pantipstory2.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyPantipManager {
    public static void emotionToReply(int topicId, int commentId, int replyId, String emo, int commentNo, int replyNo, final OnEmotionCallbackListener listener) {
        Call call = MyPantipService.getService().emotionToReply(replyId, commentId, topicId, MyPantipEmotion.EMOTION_REPLY, emo, commentNo, replyNo);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessageCallbackEmotionDao msg = (MessageCallbackEmotionDao) response.body();
                if (listener != null) {
                    listener.onSuccess(msg);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static void emotionToComment(int topicId, int commentId, String emo, final OnEmotionCallbackListener listener) {
        Call call = MyPantipService.getService().emotionToComment(commentId, topicId, MyPantipEmotion.EMOTION_COMMENT, emo);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessageCallbackEmotionDao msg = (MessageCallbackEmotionDao) response.body();
                if (listener != null) {
                    listener.onSuccess(msg);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static void emotionToTopic(int topicId, String emo, final OnEmotionCallbackListener listener) {
        Call call = MyPantipService.getService().emotionToTopic(topicId, topicId, MyPantipEmotion.EMOTION_TOPIC, emo);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessageCallbackEmotionDao msg = (MessageCallbackEmotionDao) response.body();
                if (listener != null) {
                    listener.onSuccess(msg);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static void voteReply(long topicId, long commentId, int commentNo, int replyId, int replyNo, final OnVoteCommentListener listener) {
        Call call = MyPantipService.getService().voteReply(MyPantipVote.GOOD,
                MyPantipVote.VOTE_REPLY,
                topicId,
                commentId,
                commentNo,
                replyId,
                replyNo);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessageVoteCommentCallbackDao msg = (MessageVoteCommentCallbackDao) response.body();
                if (listener != null) {
                    listener.onSuccess(msg);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }


    public static void voteComment(long topicId, long commentId, int commentNo, final OnVoteCommentListener listener) {
        Call call = MyPantipService.getService().voteComment(MyPantipVote.GOOD,
                MyPantipVote.VOTE_COMMENT,
                topicId,
                commentId,
                commentNo);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessageVoteCommentCallbackDao msg = (MessageVoteCommentCallbackDao) response.body();
                if (listener != null) {
                    listener.onSuccess(msg);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }


    public static void loadTagTopic(String tag, int lastId, int page, final OnLoadTopicListListener listener) {
        Call call = MyPantipService.getService().getTagTopic(lastId, tag, 0, 1, false, page);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessageTagTopicDao msg = (MessageTagTopicDao) response.body();
                if (listener != null) {
                    listener.onLoaded(msg.getItem().getTopic(), msg.getItem().getLastIdCurrentPage());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static void voteTopic(long topicId, final OnVoteTopicListener listener) {
        Call call = MyPantipService.getService().voteTopic(MyPantipVote.GOOD, MyPantipVote.VOTE_TOPIC, topicId);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessageVoteTopicCallbackDao msg = (MessageVoteTopicCallbackDao) response.body();
                if (listener != null) {
                    listener.onSuccess(msg);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static void getUserTopicFavorite(long userId, int page, long firstId, long latestId, final OnLoadUserTopicListener listener) {
        long time = System.currentTimeMillis();
        long _time = System.currentTimeMillis() - 1;

        int latestPage = 1;
        if (page > 1) {
            latestPage = page - 1;
        }

        Call call = MyPantipService.getService().getUserTopicFavorite("bookmark", userId, page, latestPage, firstId, latestId, time, _time);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessageUserTopic msg = (MessageUserTopic) response.body();
                if (msg != null) {
                    if (listener != null) {
                        listener.onSuccess(msg);
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static void getUserTopicComment(long userId, int page, long firstId, long latestId, final OnLoadUserTopicListener listener) {
        long time = System.currentTimeMillis();
        long _time = System.currentTimeMillis() - 1;

        int latestPage = 1;
        if (page > 1) {
            latestPage = page - 1;
        }

        Call call = MyPantipService.getService().getUserTopicComment("comment", userId, page, latestPage, firstId, latestId, time, _time);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessageUserTopic msg = (MessageUserTopic) response.body();
                if (listener != null) {
                    listener.onSuccess(msg);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static void getUserTopicOwner(long userId, int page, long firstId, long latestId, final OnLoadUserTopicListener listener) {
        long time = System.currentTimeMillis();
        long _time = System.currentTimeMillis() - 1;

        int latestPage = 1;
        if (page > 1) {
            latestPage = page - 1;
        }

        Call call = MyPantipService.getService().getUserTopicOwner("topic", userId, page, latestPage, firstId, latestId, time, _time);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessageUserTopic msg = (MessageUserTopic) response.body();
                if (listener != null) {
                    listener.onSuccess(msg);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static void reply(long topicId,
                             String message,
                             String timestamp,
                             long commentId,
                             int commentNo,
                             final OnReplyCallbackListener listener) {
        Call call = MyPantipService.getService()
                .reply(topicId, message, message, timestamp, commentId + "pantip3g", "comment" + commentNo, 1);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessageReplyCallback msg = (MessageReplyCallback) response.body();
                if (listener != null) {
                    listener.onSuccess(msg);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
                MyUtils.log("ERROR " + t.getMessage());
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static void comment(long topicId, String message, final OnCommentCallbackListener listener) {
        Call call = MyPantipService.getService().comment(topicId, message, message, 1);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessageCallbackCommentDao msg = (MessageCallbackCommentDao) response.body();
                MyUtils.log("CALLBACK " + msg.getCommentId());
                if (listener != null) {
                    listener.onSuccess(msg);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
                MyUtils.log("ERROR " + t.getMessage());
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static void loadPantipPick(long time, final OnLoadPantipPickListener listener) {
        Call call = MyPantipService.getService(MyCustomPantipFactory.getPantipPickFactory()).getPantipPickTopic(time);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessagePantipPickDao msg = (MessagePantipPickDao) response.body();
                long lastTime = (long) Double.parseDouble(msg.getLastTime());

                Log.d("BENZNESTLOG", "pantippick msg = " + msg.getPick3());
                if (listener != null) {
                    listener.onLoaded(msg.getPick3(), lastTime);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
                Log.d("BENZNESTLOG", "pantippick FAIL " + t.getMessage());
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static void loadTopic(long id, final OnLoadTopicBodyListener listener) {
        Call call = MyPantipService.getService(MyPantipService.CONVERTER_SCALAR).getTopic(id);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String html = response.body().toString();

                String title = MyPantipScraping.getTopicTitle(html);
                String body = MyPantipScraping.getTopicBody(html);
                List<TagDao> listTag = MyPantipScraping.getTopicTag(html);
                int vote = MyPantipScraping.getTopicVote(html);
                String owner = MyPantipScraping.getTopicOwner(html);
                String time = MyPantipScraping.getTopicTime(html);
                String avartarOwner = MyPantipScraping.getAvartarOwnerTopic(html);

                if (listener != null) {
                    listener.onLoaded(title, listTag, body, vote, owner, time, avartarOwner);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("BENZNEST LOG", "ERROR " + t.getMessage());
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static void loadHitTopic(RoomDao roomDao, final OnLoadHitTopicListListener listener) {
        Call call = MyPantipService.getService(MyPantipService.CONVERTER_SCALAR).getHitTopic(roomDao.getValue());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String str = response.body().toString();
                List<TopicDao> listTopic = MyPantipScraping.getHitTopic(str);
                if (listener != null) {
                    listener.onLoaded(listTopic);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("BENZNEST LOG", "ERROR " + t.getMessage());
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static void loadTrendTopic(final OnLoadTrendTopicListListener listener) {

        Call call = MyPantipService.getService().getTrendTopic(1);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessageTrendDao msg = (MessageTrendDao) response.body();
                if (listener != null) {
                    List<TopicDao> listTopic = new ArrayList<TopicDao>();
                    List<TrendDao> listTrend = msg.getTrend();
                    for (int i = 0; i < listTrend.size(); i++) {
                        TopicDao topic = new TopicDao();
                        topic.setTopicType(MyTopicType.TREND_TOPIC);
                        topic.setId(Integer.parseInt(listTrend.get(i).getTopicId()));
                        topic.setDispTopic(listTrend.get(i).getDispTopic());
                        listTopic.add(topic);
                    }
                    listener.onLoaded(listTopic);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("BENZNEST LOG", "ERROR " + t.getMessage());
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static void loadTrendTopic(RoomDao room, final OnLoadTrendTopicListListener listener) {

        Call call = MyPantipService.getService().getTrendTopic(1, room.getValue());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessageTrendDao msg = (MessageTrendDao) response.body();
                if (listener != null) {
                    List<TopicDao> listTopic = new ArrayList<TopicDao>();
                    List<TrendDao> listTrend = msg.getTrend();
                    for (int i = 0; i < listTrend.size(); i++) {
                        TopicDao topic = new TopicDao();
                        topic.setTopicType(MyTopicType.TREND_TOPIC);
                        topic.setId(Integer.parseInt(listTrend.get(i).getTopicId()));
                        topic.setDispTopic(listTrend.get(i).getDispTopic());
                        listTopic.add(topic);
                    }
                    listener.onLoaded(listTopic);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("BENZNEST LOG", "ERROR " + t.getMessage());
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static void loadTopicListInRoom(RoomDao room, int currentPage, int lastIdCurrentPage, final OnLoadTopicListListener listener) {
        Call call = MyPantipService.getService().getTopicInRoom(room.getValue(),
                MyTopicType.All_EXCEPT_SELL,
                MyTopicType.CHAT,
                true,
                currentPage,
                lastIdCurrentPage);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessageTopicListDao msg = (MessageTopicListDao) response.body();
                List<TopicDao> list = msg.getItem().getTopic();
                if (listener != null) {
                    listener.onLoaded(list, msg.getItem().getLastIdCurrentPage());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static void loadCommentListInTopic(long id, final OnLoadCommentListListener listener) {
        Call call = MyPantipService.getService().getComment(id);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessageCommentListDao msg = (MessageCommentListDao) response.body();
                if (msg != null) {
                    if (listener != null) {
                        listener.onLoaded(msg.getComments());
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("BENZNEST LOG", "ERROR " + t.getMessage());
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static void login(String email, String password, final OnPantipLoginListener listener) {
        Call call = MyPantipService.getService(MyPantipService.CONVERTER_SCALAR).login(email, password, "login", "", 1);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                String html = response.body().toString();
                if (MyPantipScraping.isSuccessLogin(html)) {
                    String session = getSession(response.headers());
                    MyUtils.log("SESSION = " + session);

                    String name = MyPantipScraping.getName(html);
                    String urlAvartar = MyPantipScraping.getUrlBigAvartar(html);
                    long id = MyPantipScraping.getUserId(html);
                    if (id != -1) {
                        if (listener != null) {
                            UserDao user = new UserDao();
                            user.setName(name);
                            user.setId(id);
                            user.setUrlAvartar(urlAvartar);
                            user.setSession(session);
                            listener.onSuccess(user);
                        }
                        return;
                    }
                }

                if (listener != null) {
                    listener.onFail();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("BENZNEST LOG", "ERROR " + t.getMessage());
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static String getSession(okhttp3.Headers header) {
        List<String> list = header.values("set-cookie");
        for (String s : list) {
            if (s.contains("session")) {
                return s;
            }
        }
        return "";
    }

    public interface OnLoadHitTopicListListener {
        void onLoaded(List<TopicDao> listTopic);

        void onFail();
    }

    public interface OnLoadTrendTopicListListener {
        void onLoaded(List<TopicDao> listTopic);

        void onFail();
    }

    public interface OnLoadTopicListListener {
        void onLoaded(List<TopicDao> listTopic, int lastIdCurrentPage);

        void onFail();
    }

    public interface OnLoadCommentListListener {
        void onLoaded(List<CommentDao> listComment);

        void onFail();
    }

    public interface OnLoadTopicBodyListener {
        void onLoaded(String title, List<TagDao> listTag, String htmlBody, int vote, String owner, String time, String avartarOwner);

        void onFail();
    }

    public interface OnLoadPantipPickListener {
        void onLoaded(List<Pick3Dao> listPantipPick, long lastTime);

        void onFail();
    }

    public interface OnPantipLoginListener {
        void onSuccess(UserDao user);

        void onFail();
    }

    public interface OnCommentCallbackListener {
        void onSuccess(MessageCallbackCommentDao msg);

        void onFail();
    }

    public interface OnReplyCallbackListener {
        void onSuccess(MessageReplyCallback msg);

        void onFail();
    }

    public interface OnLoadUserTopicListener {
        void onSuccess(MessageUserTopic msg);

        void onFail();
    }

    public interface OnVoteTopicListener {
        void onSuccess(MessageVoteTopicCallbackDao msg);

        void onFail();
    }

    public interface OnVoteCommentListener {
        void onSuccess(MessageVoteCommentCallbackDao msg);

        void onFail();
    }

    public interface OnEmotionCallbackListener {
        void onSuccess(MessageCallbackEmotionDao msg);

        void onFail();
    }
}
