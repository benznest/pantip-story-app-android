package com.benznestdeveloper.pantipstory;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyTopicType {
    public static final int All_EXCEPT_SELL = 0;
    public static final int CHAT = 1;
    public static final int POLL = 2;
    public static final int QUESTIION = 3;
    public static final int REVIEW = 4;
    public static final int NEWS = 5;
    public static final int SELL = 6;
    public static final int HIT_TOPIC = 7;
    public static final int TREND_TOPIC = 8;

    public static final int[] resIcon = {
            R.drawable.ic_comment,
            R.drawable.ic_comment,
            R.drawable.ic_poll,
            R.drawable.ic_question,
            R.drawable.ic_review,
            R.drawable.ic_news,
            R.drawable.ic_shoping,
            R.drawable.ic_star_hit_topic,
            R.drawable.ic_idea
    };

    public static int getIcon(Integer index) {
        if(index == null){
            return resIcon[All_EXCEPT_SELL];
        }
        return resIcon[index];
    }


    public static int getIconFromStyleName(String str) {
        return getIcon(getIndexFromStyleName(str));
    }

    public static int getIndexFromStyleName(String str) {
        if (str.contains("chat")) {
            return CHAT;
        } else if (str.contains("que")) {
            return QUESTIION;
        } else if (str.contains("que")) {
            return QUESTIION;
        } else if (str.contains("review")) {
            return REVIEW;
        } else if (str.contains("poll")) {
            return POLL;
        } else if (str.contains("news")) {
            return NEWS;
        } else if (str.contains("shiop")) {
            return SELL;
        }

        return CHAT;
    }
}
