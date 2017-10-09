package com.benzneststudios.pantipstory2.service.pantip;

import com.benzneststudios.pantipstory2.dao.callbackComment.MessageCallbackCommentDao;
import com.benzneststudios.pantipstory2.dao.callbackEmotion.MessageCallbackEmotionDao;
import com.benzneststudios.pantipstory2.dao.callbackReply.MessageReplyCallback;
import com.benzneststudios.pantipstory2.dao.comment.MessageCommentListDao;
import com.benzneststudios.pantipstory2.dao.pick.MessagePantipPickDao;
import com.benzneststudios.pantipstory2.dao.tag.MessageTagTopicDao;
import com.benzneststudios.pantipstory2.dao.topic.MessageTopicListDao;
import com.benzneststudios.pantipstory2.dao.trend.MessageTrendDao;
import com.benzneststudios.pantipstory2.dao.userTopic.MessageUserTopic;
import com.benzneststudios.pantipstory2.dao.vote.MessageVoteCommentCallbackDao;
import com.benzneststudios.pantipstory2.dao.vote.MessageVoteTopicCallbackDao;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by benznest on 25/4/2559.
 */
public interface MyPantipAPI {

    @FormUrlEncoded
    @POST("forum/topic/ajax_json_all_topic_info_loadmore")
    Call<MessageTopicListDao> getTopicInRoom(@Field("dataSend[room]") String roomName,
                                             @Field("dataSend[topic_type][type]") int topicType,
                                             @Field("dataSend[topic_type][default_type]") int defaultTopicType,
                                             @Field("thumbnailview") boolean isThumbnail,
                                             @Field("current_page") int currentPage,
                                             @Field("last_id_current_page") int last_id_current_page);

    @GET("topic/{topicId}")
    Call<String> getTopic(@Path("topicId") long topicId);

    @GET("forum/topic/render_comments")
    Call<MessageCommentListDao> getComment(@Query("tid") long topicId);

    @GET("forum/{room}")
    Call<String> getHitTopic(@Path("room") String room);

    @GET("forum/topic/ajax_room_pantip_trend")
    Call<MessageTrendDao> getTrendTopic(@Query("p") long page, @Query("r") String room);

    @GET("home/ajax_pantip_trend")
    Call<MessageTrendDao> getTrendTopic(@Query("p") long page);


    @GET("home/ajax_loadmore")
    Call<MessagePantipPickDao> getPantipPickTopic(@Query("p") long time);


    @FormUrlEncoded
    @POST("login/authentication")
    Call<String> login(@Field("member[email]") String email,
                       @Field("member[crypted_password]") String password,
                       @Field("action") String action,
                       @Field("redirect") String redirect,
                       @Field("persistent[remember]") int persistent);

    @FormUrlEncoded
    @POST("forum/topic/save_comment")
    Call<MessageCallbackCommentDao> comment(@Field("topic_id") long topicId,
                                            @Field("msg[raw]") String raw,
                                            @Field("msg[disp]") String disp,
                                            @Field("type") int type);

    @FormUrlEncoded
    @POST("forum/topic/save_reply")
    Call<MessageReplyCallback> reply(@Field("topic_id") long topicId,
                                     @Field("msg[raw]") String raw,
                                     @Field("msg[disp]") String disp,
                                     @Field("msg[time]") String time,
                                     @Field("msg[ref_id]") String ref_id,
                                     @Field("msg[ref_comment]") String ref_comment,
                                     @Field("type") int type);


    @GET("profile/me/ajax_my_topic")
    Call<MessageUserTopic> getUserTopicOwner(@Query("type") String type,
                                             @Query("mid") long userId,
                                             @Query("p") int page,
                                             @Query("ltpage") long latestPage,
                                             @Query("ftid") long firstId,
                                             @Query("ltid") long lastId,
                                             @Query("t") long time,
                                             @Query("_") long _time);

    @GET("profile/me/ajax_my_comment")
    Call<MessageUserTopic> getUserTopicComment(@Query("type") String type,
                                               @Query("mid") long userId,
                                               @Query("p") int page,
                                               @Query("ltpage") long latestPage,
                                               @Query("ftid") long firstId,
                                               @Query("ltid") long lastId,
                                               @Query("t") long time,
                                               @Query("_") long _time);

    @GET("profile/me/ajax_my_bookmarks")
    Call<MessageUserTopic> getUserTopicFavorite(@Query("type") String type,
                                                @Query("mid") long userId,
                                                @Query("p") int page,
                                                @Query("ltpage") long latestPage,
                                                @Query("ftid") long firstId,
                                                @Query("ltid") long lastId,
                                                @Query("t") long time,
                                                @Query("_") long _time);


    @FormUrlEncoded
    @POST("vote1/cal_like")
    Call<MessageVoteTopicCallbackDao> voteTopic(@Field("vote_status") int voteStatus,
                                                @Field("vote_type") int voteType,
                                                @Field("topic_id") long topic_id);


    @FormUrlEncoded
    @POST("vote1/cal_like")
    Call<MessageVoteCommentCallbackDao> voteComment(@Field("vote_status") int voteStatus,
                                                    @Field("vote_type") int voteType,
                                                    @Field("topic_id") long topicId,
                                                    @Field("comment_id") long commentId,
                                                    @Field("comment_no") int commentNo);


    @FormUrlEncoded
    @POST("vote1/cal_like")
    Call<String> voteReply(@Field("vote_status") int voteStatus,
                           @Field("vote_type") int voteType,
                           @Field("topic_id") long topicId,
                           @Field("cid") long commentId,
                           @Field("comment_no") int commentNo,
                           @Field("rp_id") long replyId,
                           @Field("rp_no") int replyNo);

    @FormUrlEncoded
    @POST("forum/topic/ajax_json_all_topic_tag")
    Call<MessageTagTopicDao> getTagTopic(@Field("last_id_current_page") int last_id_current_page,
                                         @Field("dataSend[tag]") String tag,
                                         @Field("dataSend[topic_type][type]") int type,
                                         @Field("dataSend[topic_type][default_type]") int default_type,
                                         @Field("thumbnailview") boolean thumbnailView,
                                         @Field("current_page") int currentPage);

    @FormUrlEncoded
    @POST("forum/topic/express_emotion")
    Call<MessageCallbackEmotionDao> emotionToTopic(
            @Field("id") int id,
            @Field("topic_id") int topic_id,
            @Field("type") String type,
            @Field("emo") String emo);

    @FormUrlEncoded
    @POST("forum/topic/express_emotion")
    Call<MessageCallbackEmotionDao> emotionToComment(
            @Field("id") int commentId,
            @Field("topic_id") int topic_id,
            @Field("type") String type,
            @Field("emo") String emo);

    @FormUrlEncoded
    @POST("forum/topic/express_emotion")
    Call<MessageCallbackEmotionDao> emotionToReply(
            @Field("id") int replyId,
            @Field("rid") int commentId,
            @Field("topic_id") int topic_id,
            @Field("type") String type,
            @Field("emo") String emo,
            @Field("comment_no") int commentNo,
            @Field("no") int replyNo);
}
