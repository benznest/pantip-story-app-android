package com.benznestdeveloper.pantipstory.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by benznest on 29-Sep-17.
 */

public class RoomDao implements Parcelable {
    private long id;
    private String icon;
    private String name = "";
    private String detail = "";
    private String value = "";
    private boolean enable = true;
    private int count = 0;

    public RoomDao(long id, String name, String detail, String icon, String value) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.detail = detail;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.icon);
        dest.writeString(this.name);
        dest.writeString(this.detail);
        dest.writeString(this.value);
        dest.writeByte(this.enable ? (byte) 1 : (byte) 0);
        dest.writeInt(this.count);
    }

    protected RoomDao(Parcel in) {
        this.id = in.readLong();
        this.icon = in.readString();
        this.name = in.readString();
        this.detail = in.readString();
        this.value = in.readString();
        this.enable = in.readByte() != 0;
        this.count = in.readInt();
    }

    public static final Creator<RoomDao> CREATOR = new Creator<RoomDao>() {
        @Override
        public RoomDao createFromParcel(Parcel source) {
            return new RoomDao(source);
        }

        @Override
        public RoomDao[] newArray(int size) {
            return new RoomDao[size];
        }
    };
}
