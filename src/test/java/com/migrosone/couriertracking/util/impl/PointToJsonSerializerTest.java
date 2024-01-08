package com.migrosone.couriertracking.util.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
public class PointToJsonSerializerTest {

    @InjectMocks
    private PointToJsonSerializer pointToJsonSerializer;

    @Test
    public void testSerializePoint() {
        Point point = new GeometryFactory().createPoint(new Coordinate(29.1244229, 40.9923307));

        assertEquals("POINT (40.9923307 29.1244229)", pointToJsonSerializer.serializePoint(point));
    }
}