package com.benznestdeveloper.pantipstory;

import android.content.Context;
import android.content.SharedPreferences;

import com.benznestdeveloper.pantipstory.dao.RoomDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyRoom {

    private static final String SHARED_PREFERENCES_NAME = "PANTIP_STORY_ROOM";
    private static SharedPreferences sp;

    public static String KEY_ROOM = "KEY_ROOM";

    private static void setContext(Context context) {
        if (context == null) {
            context = MyContextor.getInstance();
        }
        sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }


    public static ArrayList<RoomDao> getAll() {
        ArrayList<RoomDao> listRoom = getRoomList();
        if (listRoom == null) {
            listRoom = initRoomList();
        }

        return listRoom;
    }

    private static ArrayList<RoomDao> initRoomList() {
        ArrayList<RoomDao> listRoom = new ArrayList<>();
        listRoom.add(new RoomDao(1, "ก้นครัว", "ร้านอาหาร สูตรอาหาร อาหารคาว อาหารหวาน เบเกอรี่ ไอศกรีม", "ic_food", "food"));
        listRoom.add(new RoomDao(2, "กล้อง", "กล้องถ่ายรูป กล้อง DSLR กล้องวิดีโอ เทคนิคการถ่ายรูป", "ic_camera", "camera"));
        listRoom.add(new RoomDao(3, "แกลเลอรี่", "ภาพถ่ายบุคคล ภาพถ่ายทิวทัศน์ ภาพถ่ายมาโคร", "ic_picture", "gallery"));
        listRoom.add(new RoomDao(4, "จตุจักร", "สัตว์เลี้ยง สุนัข แมว ต้นไม้ จัดสวน ของสะสม งานฝีมือ เกษตรกรรม", "ic_dog", "jatujak"));
        listRoom.add(new RoomDao(5, "เฉลิมไทย", "ภาพยนตร์ ดาราภาพยนตร์ ค่ายหนัง เทศกาลหนัง หนังสั้น", "ic_film", "chalermthai"));
        listRoom.add(new RoomDao(6, "ชายคา", "บ้าน คอนโดมิเนียม ตกแต่งบ้าน เฟอร์นิเจอร์ เครื่องใช้ไฟฟ้า เครื่องครัว", "ic_house", "home"));
        listRoom.add(new RoomDao(7, "ดิโอลด์สยาม", "ผู้สูงอายุ สุขภาพผู้สูงอายุ ชีวิตหลังเกษียณ สิทธิผู้สูงอายุ ท่องเที่ยวผู้สูงอายุ", "ic_old_woman", "theoldsiam"));
        listRoom.add(new RoomDao(8, "ถนนนักเขียน", "แต่งนิยาย เรื่องสั้น กลอน นิทาน", "ic_pen", "writer"));
        listRoom.add(new RoomDao(9, "บางขุนพรหม", "ละคร นักแสดง ซีรี่ส์ รายการโทรทัศน์ สถานีโทรทัศน์", "ic_tv", "tvshow"));
        listRoom.add(new RoomDao(10, "พรหมชาติ", "ดูดวง ฮวงจุ้ย ไพ่ยิปซี ทำนายฝัน พระเครื่อง", "ic_cards", "horoscope"));
        listRoom.add(new RoomDao(11, "ภูมิภาค", "ภาคเหนือ ภาคอีสาน ภาคกลาง ภาคตะวันออก ภาคตะวันตก ภาคใต้", "ic_thailand", "region"));
        listRoom.add(new RoomDao(12, "รวมมิตร", "รวมกระทู้จากทุกห้อง", "ic_star_filled", ""));
        listRoom.add(new RoomDao(13, "ราชดำเนิน", "การเมือง รัฐศาสตร์ กฎหมาย สภาผู้แทน รัฐบาล ฝ่ายค้าน พรรคการเมือง", "ic_parliament", "rajdumnern"));
        listRoom.add(new RoomDao(14, "ศาลาประชาคม", "กฎหมาย ปัญหาสังคม ปัญหาชีวิต เศรษฐกิจ คุ้มครองผู้บริโภค", "ic_pavilion", "social"));
        listRoom.add(new RoomDao(15, "ศุภชลาศัย", "กีฬา ฟุตบอล บาสเกตบอล มวยสากล กอล์ฟ จักรยาน นักกีฬา", "ic_valley", "supachalasai"));
        listRoom.add(new RoomDao(16, "สวนลุมพินี", "สุขภาพกาย สุขภาพจิต โรคมะเร็ง โรคไมเกรน โรคภูมิแพ้ ปวดประจำเดือน", "ic_health", "lumpini"));
        listRoom.add(new RoomDao(17, "สีลม", "การบริหารจัดการ การตลาด ทรัพยากรบุคคล งานขาย SME ภาษีนิติบุคคล", "ic_management", "silom"));
        listRoom.add(new RoomDao(18, "ห้องสมุด", "หนังสือ หนังสือนิยาย ภาษาไทย ภาษาจีน ภาษาอังกฤษ ปรัชญา ประวัติศาสตร์", "ic_books", "library"));
        listRoom.add(new RoomDao(19, "กรีนโซน", "อนุรักษ์สิ่งแวดล้อม อนุรักษ์พลังงาน Green Living การออกแบบเพื่อสิ่งแวดล้อม ผลิตภัณฑ์รักษ์โลก เกษตรอินทรีย์", "ic_leaf", "greenzone"));
        listRoom.add(new RoomDao(20, "การ์ตูน", "การ์ตูนญี่ปุ่น การ์ตูนไทย การ์ตูนฝรั่ง อนิเมะ วาดการ์ตูน ของสะสมจากการ์ตูน คอสเพลย์", "ic_cartoon", "cartoon"));
        listRoom.add(new RoomDao(21, "ไกลบ้าน", "เรียนต่อต่างประเทศ ทำงานต่างประเทศ วีซ่า", "ic_plane", "klaibann"));
        listRoom.add(new RoomDao(22, "เฉลิมกรุง", "นักร้องนักดนตรี เพลง เครื่องดนตรี คอนเสิร์ต มิวสิควิดีโอ", "ic_music", "chalermkrung"));
        listRoom.add(new RoomDao(23, "ชานเรือน", "ครอบครัว ตั้งครรภ์ ตั้งชื่อลูก การเลี้ยงลูก การสอนลูก", "ic_family", "family"));
        listRoom.add(new RoomDao(24, "ซิลิคอนวัลเลย์", "คอมมือใหม่ อินเทอร์เน็ต ซอฟต์แวร์ ฮาร์ดแวร์ เกม เขียนโปรแกรม Gadget", "ic_code", "siliconvalley"));
        listRoom.add(new RoomDao(25, "โต๊ะเครื่องแป้ง", "เครื่องสำอาง เสริมสวย แฟชั่น เครื่องประดับ ลดความอ้วน", "ic_lipstick", "beauty"));
        listRoom.add(new RoomDao(26, "บลูแพลนเน็ต", "เที่ยวไทย เที่ยวต่างประเทศ ทะเล ภูเขา เกาะ น้ำตก ดำน้ำ สายการบิน", "ic_location", "blueplanet"));
        listRoom.add(new RoomDao(27, "บางรัก", "ความรัก แต่งงาน พรีเวดดิ้ง ปัญหาชีวิตคู่", "ic_love", "bangrak"));
        listRoom.add(new RoomDao(28, "มาบุญครอง", "โทรศัพท์มือถือ Smartphone Tablet iOS Android", "ic_phone", "mbk"));
        listRoom.add(new RoomDao(29, "รัชดา", "รถยนต์ มอเตอร์ไซค์ เครื่องเสียงรถยนต์ แต่งรถ การจราจร", "ic_cars", "ratchada"));
        listRoom.add(new RoomDao(30, "ไร้สังกัด", "กระทู้อื่นๆ ที่ไม่สังกัดห้องไหนเลย", "ic_no_room", "isolate"));
        listRoom.add(new RoomDao(31, "ศาสนา", "ศาสนาพุทธ ศาสนาคริสต์ ศาสนาอิสลาม เที่ยววัด ทำบุญ", "ic_religion", "religious"));
        listRoom.add(new RoomDao(32, "สยามสแควร์", "ชีวิตวัยรุ่น การเรียน โรงเรียน มหาวิทยาลัย ความรักวัยรุ่น เกม", "ic_chat", "siam"));
        listRoom.add(new RoomDao(33, "สินธร", "หุ้น เศรษฐกิจ การลงทุน LTF RMF ธนาคาร เงินตราต่างประเทศ", "ic_stock", "sinthorn"));
        listRoom.add(new RoomDao(34, "หว้ากอ", "วิทยาศาสตร์ วิศวกรรม เทคโนโลยี ฟิสิกส์ ดาราศาสตร์ อวกาศ", "ic_science", "wahkor"));
        listRoom.add(new RoomDao(35, "หอศิลป์", "ศิลปะ ภาพวาด ประวัติศาสตร์ศิลป์ สื่อประสม Graphic Design", "ic_art", "art"));
        listRoom.add(new RoomDao(36, "เกาหลี", "เค-ป็อบ ซีรีส์เกาหลี นักแสดงเกาหลี อาหารเกาหลี เที่ยวเกาหลี แฟชั่นเกาหลี ภาษาเกาหลี", "ic_south_korea", "korea"));
        setRoomList(listRoom);
        return listRoom;
    }

    public static void update(RoomDao roomDao) {
        ArrayList<RoomDao> list = getAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == roomDao.getId()) {
                list.set(i, roomDao);
            }
        }

        setRoomList(list);
    }

    public static ArrayList<RoomDao> getRoomEnabled() {
        ArrayList<RoomDao> listRoomEnabled = new ArrayList<>();
        ArrayList<RoomDao> listRoom = getAll();
        for (int i = 0; i < listRoom.size(); i++) {
            RoomDao room = listRoom.get(i);
            if (room.isEnable()) {
                listRoomEnabled.add(room);
            }
        }

        return listRoomEnabled;
    }

    public static void setEnableAll(boolean isEnable) {
        ArrayList<RoomDao> list = getAll();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setEnable(isEnable);
        }

        setRoomList(list);
    }

    public static ArrayList<RoomDao> getRoomList() {
        setContext(MyContextor.getInstance());
        String json = sp.getString(KEY_ROOM, "");

        ArrayList<RoomDao> list =
                new Gson().fromJson(json, new TypeToken<ArrayList<RoomDao>>() {
                }.getType());

        return list;
    }

    public static void setRoomList(ArrayList<RoomDao> list) {
        setContext(MyContextor.getInstance());
        String json = new Gson().toJson(list);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_ROOM, json);
        editor.commit();
    }
}
