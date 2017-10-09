package com.benzneststudios.pantipstory2.dao;

import com.benzneststudios.pantipstory2.dao.comment.CommentDao;
import com.benzneststudios.pantipstory2.dao.topic.TopicDao;

import java.util.List;

/**
 * Created by benznest on 05-Oct-17.
 */

public class TopicDataDao {
    private TopicDao topic;
    private String htmlBody;
    private List<CommentDao> comments;

    public TopicDao getTopic() {
        return topic;
    }

    public void setTopic(TopicDao topic) {
        this.topic = topic;
    }

    public List<CommentDao> getComments() {
        return comments;
    }

    public void setComments(List<CommentDao> comments) {
        this.comments = comments;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }
}
