package com.ai.repo.chatRepo;

import com.ai.entity.dto.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Integer> {
    List<Chat> findByUidAndNameOrderBySeq(String uid, String name);
    @Query("select max(c.seq) from Chat c where c.uid = :uid and c.name = :name")
    Integer findMaxSeqByUidAndName(String uid, String name);
}
