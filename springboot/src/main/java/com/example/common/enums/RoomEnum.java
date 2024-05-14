package com.example.common.enums;

public enum RoomEnum {
    STATUS_NO("占用"),
    STATUS_OK("空闲"),
    ;

    public String status;

    RoomEnum(String status) {
        this.status = status;
    }
}
