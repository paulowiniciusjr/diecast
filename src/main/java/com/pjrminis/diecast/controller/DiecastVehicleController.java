package com.pjrminis.diecast.controller;

import com.pjrminis.diecast.model.DiecastVehicle;
import com.pjrminis.diecast.model.DiecastVehicleKt;
import com.pjrminis.diecast.service.DiecastVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class DiecastVehicleController {

    @Autowired
    private DiecastVehicleService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DiecastVehicle> createVehicle(@RequestBody DiecastVehicle vehicle) {
        service.createVehicle(vehicle);
        return ResponseEntity.status(201).body(vehicle);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<DiecastVehicle> getVehicle(@PathVariable Long id) {
        return service.getVehicle(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        service.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<DiecastVehicle>> getAllVehicles() {
        List<DiecastVehicle> vehicles = service.getAll();
        return ResponseEntity.ok(vehicles);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DiecastVehicle> updateVehicle(@PathVariable Long id, @RequestBody DiecastVehicle updatedVehicle) {
        DiecastVehicle vehicle = service.updateVehicle(id, updatedVehicle);
        return ResponseEntity.ok(vehicle);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/me")
    public ResponseEntity<List<DiecastVehicle>> getMyVehicles() {
        List<DiecastVehicle> vehicles = service.listMyVehicles();
        return ResponseEntity.ok(vehicles);
    }


}