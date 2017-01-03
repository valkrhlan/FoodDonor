package com.coky.core.entities;

/**
 * Created by Valentina on 3.1.2017..
 */

public class Notifikacija {
    String title;
    String message;

    public Notifikacija(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
