package com.pjrminis.diecast.controller

import com.pjrminis.diecast.model.DiecastVehicleKt
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DiecastVehicleControllerKt {

    @GetMapping("/vehiclesKt")
    fun getAllVehicles(): List<DiecastVehicleKt> {
        //val vehicles: MutableList<DiecastVehicleKt?>? = service.getAll()
        //return ResponseEntity.ok<MutableList<DiecastVehicleKt?>?>(vehicles)
        return listOf(
            DiecastVehicleKt(1L, "Car A"),
            DiecastVehicleKt(2L, "Car B")
        )
    }

}