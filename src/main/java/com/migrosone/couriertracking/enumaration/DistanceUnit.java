package com.migrosone.couriertracking.enumaration;

import lombok.Getter;

@Getter
public enum DistanceUnit {
    KM("Kilometer"),
    MILE("Mile");

    private final String label;

    DistanceUnit(String label) {
        this.label = label;
    }
}
