package com.sheenjoshuaamol.parkingmanagerdrawer.ui.parklist;

public class Spot {
    String code, name;
    String time;
    Boolean Occupied;

    Spot() {
        //EMPTY
    }
    Spot(String code, String name, String time, Boolean Occupied) {
        this.code = code;
        this.name = name;
        this.time = time;
        this.Occupied = Occupied;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }
    public Boolean getOccupied() {
        return Occupied;
    }

}
