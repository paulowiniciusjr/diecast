package com.pjrminis.diecast.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleFrequencyDto {
    private String brand;
    private Long count;
}
