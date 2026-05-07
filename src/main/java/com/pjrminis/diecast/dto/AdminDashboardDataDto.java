package com.pjrminis.diecast.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDashboardDataDto {
    private AdminStatsDto stats;
    private List<UserWithVehiclesDto> users;
    private VehicleStatsDto vehicleStats;
}
