package com.ai.repo.chatRepo;

import com.ai.entity.dto.Chat;
import com.ai.entity.dto.ChatNameInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatNameInfoRepository extends JpaRepository<ChatNameInfo, Integer> {
    List<ChatNameInfo> findNameByUidOrderBySeqAsc(String uid);

    boolean existsByUidAndName(String uid, String name);

    @Query("select max (c.seq) from ChatNameInfo c where c.uid=:uid")
    Integer findMaxSeqByUid(String uid);
}
