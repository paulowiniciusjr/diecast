package com.pjrminis.diecast.repository;

import com.pjrminis.diecast.model.DiecastVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiecastVehicleRepository extends JpaRepository<DiecastVehicle,Long> {

}
