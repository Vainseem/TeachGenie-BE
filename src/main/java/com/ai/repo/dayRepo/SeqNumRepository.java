package com.ai.repo.dayRepo;

import com.ai.entity.dto.SeqNum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeqNumRepository extends JpaRepository<SeqNum,Integer> {
    SeqNum findByUid(String uid);

    boolean existsByUid(String uid);
}
