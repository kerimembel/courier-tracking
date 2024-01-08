package com.migrosone.couriertracking.util.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.locationtech.jts.geom.Point;

import java.io.IOException;

public class PointToJsonSerializer extends JsonSerializer<Point> {

    @Override
    public void serialize(Point value, JsonGenerator jgen,
                          SerializerProvider provider) throws IOException {
        String jsonValue = serializePoint(value);
        jgen.writeString(jsonValue);
    }

    protected String serializePoint(Point value) {
        try {
            if (value != null) {
                double lat = value.getY();
                double lon = value.getX();
                return String.format("POINT (%s %s)", lat, lon);
            }
        } catch (Exception ignored) {
        }
        return "null";
    }
}
