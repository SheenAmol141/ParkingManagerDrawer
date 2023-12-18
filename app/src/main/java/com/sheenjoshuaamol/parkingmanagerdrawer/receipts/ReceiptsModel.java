package com.sheenjoshuaamol.parkingmanagerdrawer.receipts;

public class ReceiptsModel {
    String code, name, plate, time, search;
    public ReceiptsModel () {
    }

    public ReceiptsModel(String name, String code, String plate, String search, String time) {
        this.code = code;
        this.name = name;
        this.plate = plate;
        this.time = time;
        this.search = search;
    }

    public String getsearch() {
        return search;
    }

    public void setsearch(String code) {
        this.search = search;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getTimeEntered() {
        return time;
    }

    public void setTimeEntered(String time) {
        this.time = time;
    }


}
