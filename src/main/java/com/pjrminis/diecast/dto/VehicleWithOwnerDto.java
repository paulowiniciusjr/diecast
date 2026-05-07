package com.pjrminis.diecast.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleWithOwnerDto {
    private Long id;
    private String name;
    private String brand;
    private String color;
    private String registeredAt;
    private String owner;
}
