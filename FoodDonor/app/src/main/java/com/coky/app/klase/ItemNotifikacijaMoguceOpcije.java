package com.coky.app.klase;

/**
 * Created by Valentina on 30.12.2016..
 */

public class ItemNotifikacijaMoguceOpcije {
    /*
    klasa koja predstavlja pojedinu mogućnost u listi svih mogućnosti u klasi NotifikacijaMOgućeOpcije
     */

    public String title;
    public String id;

    public ItemNotifikacijaMoguceOpcije(String title, String id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
