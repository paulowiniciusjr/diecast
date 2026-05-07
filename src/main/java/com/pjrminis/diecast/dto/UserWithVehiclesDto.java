package com.pjrminis.diecast.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithVehiclesDto {
    private Long id;
    private String username;
    private Long vehicleCount;
    private String registeredAt;
}
