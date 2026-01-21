package com.business.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.business.backend.model.Shift;

public interface ShiftRepository extends MongoRepository<Shift, String> {
    List<Shift> findByUserId(String userId);
    Optional<Shift> findTopByUserIdAndEndTimeIsNullOrderByStartTimeDesc(String userId);
}
