package com.benznestdeveloper.pantipstory.dao;

/**
 * Created by benznest on 02-Oct-17.
 */

public class UserDao {
    private String name;
    private long id;
    private String urlAvartar;
    private String session;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrlAvartar() {
        return urlAvartar;
    }

    public void setUrlAvartar(String urlAvartar) {
        this.urlAvartar = urlAvartar;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
