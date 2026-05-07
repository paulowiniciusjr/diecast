package com.pjrminis.diecast.repository;

import com.pjrminis.diecast.dto.UserWithVehiclesDto;
import com.pjrminis.diecast.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT new com.pjrminis.diecast.dto.UserWithVehiclesDto(u.id, u.username, " +
           "CAST(COUNT(v) AS LONG), CAST(u.createdAt AS STRING)) " +
           "FROM User u LEFT JOIN u.vehicles v GROUP BY u.id, u.username, u.createdAt ORDER BY u.id")
    List<UserWithVehiclesDto> findAllUsersWithVehicleCounts();
}
