package com.ai.repo.alertRepo;

import com.ai.entity.dto.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert,Integer> {
    @Query("select max (c.seq) from Alert c where c.uid=:uid")
    Integer findMaxSeqByUid(String uid);
    Alert findByUidAndSeq(String uid, int seq);

    List<Alert> findByUidOrderBySeq(String uid);
    boolean existsByUid(String uid);
}
