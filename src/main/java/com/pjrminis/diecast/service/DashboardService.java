package com.pjrminis.diecast.service;

import com.pjrminis.diecast.dto.*;
import com.pjrminis.diecast.repository.DiecastVehicleRepository;
import com.pjrminis.diecast.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DiecastVehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    public AdminDashboardDataDto getAdminDashboard() {
        AdminDashboardDataDto dashboard = new AdminDashboardDataDto();

        // Get statistics
        AdminStatsDto stats = new AdminStatsDto(
            userRepository.count(),
            vehicleRepository.count()
        );
        dashboard.setStats(stats);

        // Get users with vehicle counts
        List<UserWithVehiclesDto> users = userRepository.findAllUsersWithVehicleCounts();
        dashboard.setUsers(users);

        // Get vehicle statistics
        VehicleStatsDto vehicleStats = new VehicleStatsDto(
            vehicleRepository.findTopBrands(),
            vehicleRepository.findTopColors(),
            vehicleRepository.findNewestVehicles(),
            vehicleRepository.findOldestVehicles()
        );
        dashboard.setVehicleStats(vehicleStats);

        return dashboard;
    }

}
