package com.pjrminis.diecast.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleStatsDto {
    private List<VehicleFrequencyDto> topBrands;
    private List<VehicleFrequencyDto> topColors;
    private List<VehicleWithOwnerDto> newestVehicles;
    private List<VehicleWithOwnerDto> oldestVehicles;
}
