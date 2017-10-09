package com.benzneststudios.pantipstory2;

import java.util.ArrayList;

/**
 * Created by benznest on 02-Oct-17.
 */

public class MyNavigationMenu {
    public static final int MENU_ID_HOME = 0;
    public static final int MENU_ID_LOGIN = 1;
    public static final int MENU_ID_PROFILE = 2;
    public static final int MENU_ID_ROOM_MANAGEMENT = 3;
    public static final int MENU_ID_TOPIC_OFFLINE = 4;
    public static final int MENU_ID_SETTING = 5;
    public static final int MENU_ID_ABOUT = 6;
    public static final int MENU_ID_LOGOUT = 7;
    public static final int MENU_ID_TOPIC_HISTORY = 8;

    public static ArrayList<MenuDao> listMenu;

    public static ArrayList<MenuDao> getListMenu() {
        if (listMenu == null) {
            listMenu = new ArrayList<>();
            listMenu.add(new MenuDao(MENU_ID_HOME, R.drawable.ic_home, "หน้าแรก"));
            listMenu.add(new MenuDao(MENU_ID_PROFILE, R.drawable.ic_user, "หน้าของฉัน"));
            listMenu.add(new MenuDao(MENU_ID_ROOM_MANAGEMENT, R.drawable.ic_room_list, "จัดการห้อง"));
            listMenu.add(new MenuDao(MENU_ID_TOPIC_OFFLINE, R.drawable.ic_offline, "กระทู้ออฟไลน์"));
            listMenu.add(new MenuDao(MENU_ID_TOPIC_HISTORY, R.drawable.ic_history, "ประวัติกระทู้"));
            listMenu.add(new MenuDao(MENU_ID_SETTING, R.drawable.ic_setting, "ตั้งค่า"));
            listMenu.add(new MenuDao(MENU_ID_ABOUT, R.drawable.ic_info, "เกี่ยวกับ"));
            listMenu.add(new MenuDao(MENU_ID_LOGOUT, R.drawable.ic_logout, "ออกจากระบบ"));
            listMenu.add(new MenuDao(MENU_ID_LOGIN, R.drawable.ic_login, "เข้าสู่ระบบ"));
        }
        return listMenu;
    }

    public static class MenuDao {
        int id;
        int icon;
        String name;

        public MenuDao(int id, int icon, String name) {
            this.id = id;
            this.icon = icon;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
