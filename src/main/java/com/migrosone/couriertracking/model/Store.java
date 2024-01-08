package com.migrosone.couriertracking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

/**
 * This class defines the Store entity.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_store",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "location"})
        })
public class Store extends BaseEntity {

    private String name;

    @Column(columnDefinition = "geometry(Point,4326)")
    private Point location;
}
