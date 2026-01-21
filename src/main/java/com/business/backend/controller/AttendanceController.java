package com.business.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.backend.model.Shift;
import com.business.backend.security.UserDetailsImpl;
import com.business.backend.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/start")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Shift startShift() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return attendanceService.startShift(userDetails.getId(), userDetails.getUsername());
    }

    @PostMapping("/end")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Shift endShift() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return attendanceService.endShift(userDetails.getId());
    }

    @GetMapping("/my-shifts")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Shift> getMyShifts() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return attendanceService.getUserShifts(userDetails.getId());
    }
}
