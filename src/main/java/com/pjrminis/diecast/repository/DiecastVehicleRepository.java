package com.pjrminis.diecast.repository;

import com.pjrminis.diecast.model.DiecastVehicle;
import com.pjrminis.diecast.model.DiecastVehicleKt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiecastVehicleRepository extends JpaRepository<DiecastVehicle,Long> {

    List<DiecastVehicle> findByUserUsername(String username);

}
