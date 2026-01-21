package com.business.backend.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.backend.model.Shift;
import com.business.backend.repository.ShiftRepository;

@Service
public class AttendanceService {
    @Autowired
    private ShiftRepository shiftRepository;

    public Shift startShift(String userId, String username) {
        Optional<Shift> ongoingShift = shiftRepository.findTopByUserIdAndEndTimeIsNullOrderByStartTimeDesc(userId);
        if (ongoingShift.isPresent()) {
            throw new RuntimeException("Shift already started!");
        }
        Shift shift = new Shift(userId, username);
        return shiftRepository.save(shift);
    }

    public Shift endShift(String userId) {
        Shift shift = shiftRepository.findTopByUserIdAndEndTimeIsNullOrderByStartTimeDesc(userId)
            .orElseThrow(() -> new RuntimeException("No active shift found"));
        
        shift.setEndTime(LocalDateTime.now());
        long seconds = Duration.between(shift.getStartTime(), shift.getEndTime()).getSeconds();
        shift.setHoursWorked(seconds / 3600.0);
        
        return shiftRepository.save(shift);
    }

    public List<Shift> getUserShifts(String userId) {
        return shiftRepository.findByUserId(userId);
    }
}
