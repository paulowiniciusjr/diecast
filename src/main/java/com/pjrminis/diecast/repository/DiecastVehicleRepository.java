package com.pjrminis.diecast.repository;

import com.pjrminis.diecast.dto.VehicleFrequencyDto;
import com.pjrminis.diecast.dto.VehicleWithOwnerDto;
import com.pjrminis.diecast.model.DiecastVehicle;
import com.pjrminis.diecast.model.DiecastVehicleKt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiecastVehicleRepository extends JpaRepository<DiecastVehicle,Long> {

    List<DiecastVehicle> findByUserUsername(String username);

    @Query("SELECT new com.pjrminis.diecast.dto.VehicleFrequencyDto(d.vehicleBrand, COUNT(d)) " +
           "FROM DiecastVehicle d GROUP BY d.vehicleBrand ORDER BY COUNT(d) DESC LIMIT 10")
    List<VehicleFrequencyDto> findTopBrands();

    @Query("SELECT new com.pjrminis.diecast.dto.VehicleFrequencyDto(d.color, COUNT(d)) " +
           "FROM DiecastVehicle d GROUP BY d.color ORDER BY COUNT(d) DESC LIMIT 10")
    List<VehicleFrequencyDto> findTopColors();

    @Query("SELECT new com.pjrminis.diecast.dto.VehicleWithOwnerDto(d.id, d.name, d.vehicleBrand, d.color, " +
           "CAST(d.createdAt AS STRING), d.user.username) " +
           "FROM DiecastVehicle d ORDER BY d.createdAt DESC LIMIT 10")
    List<VehicleWithOwnerDto> findNewestVehicles();

    @Query("SELECT new com.pjrminis.diecast.dto.VehicleWithOwnerDto(d.id, d.name, d.vehicleBrand, d.color, " +
           "CAST(d.createdAt AS STRING), d.user.username) " +
           "FROM DiecastVehicle d ORDER BY d.createdAt ASC LIMIT 10")
    List<VehicleWithOwnerDto> findOldestVehicles();

}

