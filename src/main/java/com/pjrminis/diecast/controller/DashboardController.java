package com.pjrminis.diecast.controller;

import com.pjrminis.diecast.dto.AdminDashboardDataDto;
import com.pjrminis.diecast.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<AdminDashboardDataDto> getAdminDashboard() {
        AdminDashboardDataDto data = dashboardService.getAdminDashboard();
        return ResponseEntity.ok(data);
    }

}
