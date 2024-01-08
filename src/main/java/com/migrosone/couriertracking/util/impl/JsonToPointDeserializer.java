package com.migrosone.couriertracking.util.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

@RequiredArgsConstructor
public class JsonToPointDeserializer extends JsonDeserializer<Point> {

    private final GeometryFactory geometryFactory;

    @Override
    public Point deserialize(JsonParser jp, DeserializationContext ctxt) {
        try {
            String text = jp.getText();
            return parseToPoint(text);
        } catch (Exception e) {
            return null;
        }
    }

    protected Point parseToPoint(String text) {
        if (text == null || text.length() == 0)
            return null;

        String[] coordinates = text.replaceFirst("POINT ?\\(", "").replaceFirst("\\)", "").split(" ");
        double lat = Double.parseDouble(coordinates[0]);
        double lon = Double.parseDouble(coordinates[1]);

        return geometryFactory.createPoint(new Coordinate(lat, lon));
    }
}
