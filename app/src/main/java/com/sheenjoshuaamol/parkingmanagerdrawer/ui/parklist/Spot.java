package com.sheenjoshuaamol.parkingmanagerdrawer.ui.parklist;

import java.util.Calendar;

public class Spot {
    String code, name;
    Calendar time;

    Spot() {
        //EMPTY
    }
    Spot(String code, String name, Calendar time) {
        this.code = code;
        this.name = name;
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Calendar getTime() {
        return time;
    }
}
