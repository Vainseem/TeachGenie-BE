package com.ai.repo.taskRepo;

import com.ai.entity.dto.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    @Query("select max (c.seq) from Task c where c.uid=:uid")
    Integer findMaxSeqByUid(String uid);
    Task findByUidAndTitle(String uid, String title);

    List<Task> findByUidOrderBySeq(String uid);
    boolean existsByUid(String uid);
}
