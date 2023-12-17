package com.sheenjoshuaamol.parkingmanagerdrawer.receipts;

public class ReceiptsModel {
    String code, name, plate, time;
    public ReceiptsModel () {
    }

    public ReceiptsModel(String code, String name, String plate, String time) {
        this.code = code;
        this.name = name;
        this.plate = plate;
        this.time = time;
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
