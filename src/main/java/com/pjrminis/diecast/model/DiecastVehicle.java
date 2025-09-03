package com.pjrminis.diecast.model;

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
        allocationSize = 50,
        initialValue = 1
)
public class DiecastVehicle implements Serializable {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diecast_vehicle_seq")
    @Id
    private Long id;

    private String name;
    private String scale;
    private String vehicleBrand;
    private String vehicleDiecastBrand;
    private String color;
}