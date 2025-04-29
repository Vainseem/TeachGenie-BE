package com.ai.repo.dayRepo;

import com.ai.entity.dto.day.Day1;
import com.ai.entity.dto.day.Day2;
import com.ai.entity.dto.day.Day6;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Day6Repository extends JpaRepository<Day6, Integer> {

    List<Day6> findByUid(String uid);

    Optional<Day6> findByUidAndSeq(String uid, int seq);
}
