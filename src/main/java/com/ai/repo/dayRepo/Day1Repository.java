package com.ai.repo.dayRepo;

import com.ai.entity.dto.day.Day1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Day1Repository extends JpaRepository<Day1,Integer> {
    List<Day1> findByUid(String uid);
    Optional<Day1> findByUidAndSeq(String uid, int seq);
}
