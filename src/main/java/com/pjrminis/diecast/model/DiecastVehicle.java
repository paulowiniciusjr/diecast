package com.pjrminis.diecast.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "diecast_vehicle")
@SequenceGenerator(
        name = "diecast_vehicle_seq",
        sequenceName = "diecast_vehicle_seq",
        allocationSize = 1,
        initialValue = 1
)

public class DiecastVehicle implements Serializable {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diecast_vehicle_seq")
    @Id
    private Long id;

    private String name;
    private String scale;

    @Column(name = "vehicle_brand")
    private String vehicleBrand;

    @Column(name = "vehicle_diecast_brand")
    private String vehicleDiecastBrand;
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

}