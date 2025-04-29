package com.ai.repo.dayRepo;

import com.ai.entity.dto.day.Day1;
import com.ai.entity.dto.day.Day2;
import com.ai.entity.dto.day.Day4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Day4Repository extends JpaRepository<Day4, Integer> {
    List<Day4> findByUid(String uid);

    Optional<Day4> findByUidAndSeq(String uid, int seq);
}
