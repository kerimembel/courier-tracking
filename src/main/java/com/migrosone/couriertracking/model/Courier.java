package com.migrosone.couriertracking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.migrosone.couriertracking.enumaration.DistanceUnit;
import com.migrosone.couriertracking.util.impl.JsonToPointDeserializer;
import com.migrosone.couriertracking.util.impl.PointToJsonSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines the Courier Entity.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Entity
@Getter
@Setter
@Table(name = "tbl_courier")
public class Courier extends BaseEntity {

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "courier", fetch = FetchType.LAZY)
    private List<CourierEntry> courierEntries = new ArrayList<>();

    @JsonSerialize(using = PointToJsonSerializer.class)
    @JsonDeserialize(using = JsonToPointDeserializer.class)
    @Column(columnDefinition = "geometry(Point,4326)")
    private Point lastLocation;

    @Column(name = "total_travel_distance")
    private Double totalTravelDistance = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(name = "distance_unit", length = 4, nullable = false)
    private DistanceUnit distanceUnit = DistanceUnit.KM;
}
