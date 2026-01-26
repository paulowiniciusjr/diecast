package com.pjrminis.diecast.service;

import com.pjrminis.diecast.model.DiecastVehicle;
import com.pjrminis.diecast.model.User;
import com.pjrminis.diecast.repository.DiecastVehicleRepository;
import com.pjrminis.diecast.repository.UserRepository;
import com.pjrminis.diecast.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiecastVehicleService {

    @Autowired
    private DiecastVehicleRepository repository;

    @Autowired
    private UserRepository userRepository;

    public DiecastVehicle createVehicle(DiecastVehicle vehicle) {

        repository.save(vehicle);

        String username = SecurityUtils.getUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        vehicle.setUser(user);

        return repository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        repository.deleteById(id);
    }

    public Optional<DiecastVehicle> getVehicle(Long id) throws RuntimeException {
        return repository.findById(id);
    }

    public DiecastVehicle updateVehicle(Long id, DiecastVehicle updatedVehicle) {
        return repository.findById(id)
                .map(vehicle -> {
                    vehicle.setName(updatedVehicle.getName());
                    vehicle.setScale(updatedVehicle.getScale());
                    vehicle.setVehicleBrand(updatedVehicle.getVehicleBrand());
                    vehicle.setVehicleDiecastBrand(updatedVehicle.getVehicleDiecastBrand());
                    vehicle.setColor(updatedVehicle.getColor());
                    return repository.save(vehicle);
                })
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id " + id));
    }

    public List<DiecastVehicle> getAll() {
        return repository.findAll();
    }

    public List<DiecastVehicle> listMyVehicles() {
        String username = SecurityUtils.getUsername();
        return repository.findByUserUsername(username);
    }


}
