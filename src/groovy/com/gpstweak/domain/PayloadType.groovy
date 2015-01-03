package com.gpstweak.domain

public enum PayloadType {

    GPX("GPX"),
    TCX("TCX")

    public String type

    public PayloadType(String type) {
        this.type = type
    }

    public String getType() {
      type
    }

    public PayloadType getByType(String type) {
        values().each {
            if (it.type == type) {
                return it
            }
        }
        null
    }
}