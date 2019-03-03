package com.benznestdeveloper.pantipstory;

/**
 * Created by benznest on 08-Oct-17.
 */

public class MyRoomManager {
    public static final int DISPLAY_ROOM_ENABLED = 0;
    public static final int DISPLAY_ROOM_SORT_USAGE = 1;
    public static final int DISPLAY_ROOM_ALL = 2;

    public static final String[] DISPLAY_ROOM_NAME = {"ห้องโปรด", "ห้องจากจำนวนการใช้งาน", "ห้องทั้งหมด"};

    public static String[] getDisplayRoomName() {
        return DISPLAY_ROOM_NAME;
    }
}
