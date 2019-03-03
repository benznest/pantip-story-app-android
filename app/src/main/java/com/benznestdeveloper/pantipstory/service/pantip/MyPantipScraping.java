package com.benznestdeveloper.pantipstory.service.pantip;

import android.util.Log;

import com.benznestdeveloper.pantipstory.MyTopicType;
import com.benznestdeveloper.pantipstory.dao.topic.TagDao;
import com.benznestdeveloper.pantipstory.dao.topic.TopicDao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyPantipScraping {

    public static boolean isSuccessLogin(String html) {
        Document doc = Jsoup.parse(html);
        try {
            Element e = doc.select(".profile-menu").first();
            return true;
        } catch (Exception e) {
            Log.d("BENZNEST LOG", "isSuccessLogin ERROR ");
        }
        return false;
    }

    public static String getName(String html) {
        Document doc = Jsoup.parse(html);
        try {
            Element e = doc.select(".profile-menu").first();
            String name = e.select("img.mini-avatar").attr("title");
            return name;
        } catch (Exception e) {
            Log.d("BENZNEST LOG", "getName ERROR ");
        }
        return "";
    }

    public static String getUrlBigAvartar(String html) {
        Document doc = Jsoup.parse(html);
        try {
            Element e = doc.select(".big-avatar").first();
            String url = e.attr("src");
            return url;
        } catch (Exception e) {
            Log.d("BENZNEST LOG", "getUrlBigAvartar ERROR ");
        }
        return "";
    }

    public static long getUserId(String html) {
        Document doc = Jsoup.parse(html);
        try {
            Element e = doc.select("div.profile-follow").first();
            long id = Long.parseLong(e.attr("id").replaceAll("[^0-9.]", ""));
            return id;
        } catch (Exception e) {
            Log.d("BENZNEST LOG", "getUserId ERROR ");
        }
        return -1;
    }

    public static String getTopicTitle(String html) {
        Document doc = Jsoup.parse(html);
        Element e = doc.select("h2.display-post-title").first();
        return e.text();
    }

    public static String getAvartarOwnerTopic(String html) {
        Document doc = Jsoup.parse(html);
        Element e = doc.select("div.display-post-avatar").first();
        String url = e.select("img").first().attr("src");
        return url;
    }

    public static int getTopicVote(String html) {
        Document doc = Jsoup.parse(html);
        Element e = doc.select("span.like-score").first();
        return Integer.parseInt(e.text());

    }

    public static String getTopicOwner(String html) {
        Document doc = Jsoup.parse(html);
        Element e = doc.select("a.display-post-name.owner").first();
        return e.text();
    }

    public static String getTopicTime(String html) {
        Document doc = Jsoup.parse(html);
        return doc.select("abbr[data-utime]").attr("data-utime");
    }

    public static String getTopicBody(String html) {
        Document doc = Jsoup.parse(html);
        Element e = doc.select("div.display-post-story").first();
        return e.outerHtml();
    }

    public static List<TagDao> getTopicTag(String html) {
        Document doc = Jsoup.parse(html);
        Element e = doc.select(".display-post-tag-wrapper").first();
        List<String> listTopicName = e.select("a").eachText();
        List<TagDao> listTag = new ArrayList<>();
        for (int i = 0; i < listTopicName.size(); i++) {
            TagDao tag = new TagDao();
            tag.setTag(listTopicName.get(i));
            tag.setUrl("/tag/" + listTopicName.get(i));
            listTag.add(tag);
        }
        return listTag;
    }

    public static List<TopicDao> getHitTopic(String html) {
        try {
            Document doc = Jsoup.parse(html);
            Element e = doc.select("#item_pantip-best_room").first();
            List<String> listTopicName = e.select("a").eachText();
            List<String> listTopicId = e.select("a").eachAttr("href");
            for (String s : listTopicName) {
                Log.d("BENZNESTLOG", "TITLE=" + s);
            }
            for (String s : listTopicId) {
                Log.d("BENZNESTLOG", "ID=" + s);
            }

            List<TopicDao> listTopic = new ArrayList<>();
            for (int i = 0; i < listTopicId.size(); i++) {
                TopicDao topic = new TopicDao();
                topic.setTopicType(MyTopicType.HIT_TOPIC);
                topic.setDispTopic(listTopicName.get(i));

                String id = listTopicId.get(i).replaceAll("[^0-9.]", "");
                topic.setId(Integer.parseInt(id));

                listTopic.add(topic);
            }
            return listTopic;
        } catch (Exception e) {

        }
        List<TopicDao> listTopic = new ArrayList<>();
        return listTopic;
    }
}
