package com.migrosone.couriertracking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * This class defines the Courier Entry Entity.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Entity
@Getter
@Setter
@Table(name = "tbl_courier_entry")
public class CourierEntry extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id")
    @JsonBackReference
    private Courier courier;

    @Column(name = "entry_time", nullable = false)
    private LocalDateTime entryTime;
}
