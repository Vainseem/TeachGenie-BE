package com.ai.repo.dayRepo;

import com.ai.entity.dto.day.Day1;
import com.ai.entity.dto.day.Day2;
import com.ai.entity.dto.day.Day5;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Day5Repository extends JpaRepository<Day5, Integer> {

    List<Day5> findByUid(String uid);

    Optional<Day5> findByUidAndSeq(String uid, int seq);
}
