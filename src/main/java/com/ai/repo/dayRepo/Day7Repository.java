package com.ai.repo.dayRepo;

import com.ai.entity.dto.day.Day1;
import com.ai.entity.dto.day.Day2;
import com.ai.entity.dto.day.Day7;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Day7Repository extends JpaRepository<Day7, Integer> {
    List<Day7> findByUid(String uid);

    Optional<Day7> findByUidAndSeq(String uid, int seq);
}
