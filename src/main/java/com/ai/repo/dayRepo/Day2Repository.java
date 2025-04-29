package com.ai.repo.dayRepo;

import com.ai.entity.dto.day.Day1;
import com.ai.entity.dto.day.Day2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Day2Repository extends JpaRepository<Day2,Integer> {
    List<Day2> findByUid(String uid);
    Optional<Day2> findByUidAndSeq(String uid, int seq);
}
