package com.ai.repo.dayRepo;

import com.ai.entity.dto.day.Day1;
import com.ai.entity.dto.day.Day2;
import com.ai.entity.dto.day.Day3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Day3Repository extends JpaRepository<Day3, Integer> {
    List<Day3> findByUid(String uid);

    Optional<Day3> findByUidAndSeq(String uid, int seq);
}
