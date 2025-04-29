package com.ai.repo.feedbackRepo;

import com.ai.entity.dto.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByUidOrderBySeq(String uid);

    @Query("select max(c.seq) from Feedback c where c.uid = :uid")
    Integer findMaxSeqByUid(String uid);

    Feedback findByUidAndSeq(String uid, int seq);

    boolean existsByUidAndSeq(String uid, int seq);

}
