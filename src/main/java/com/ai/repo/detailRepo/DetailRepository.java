package com.ai.repo.detailRepo;

import com.ai.entity.dto.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends JpaRepository<Detail,Integer> {
    Detail findByUid(String uid);
    boolean existsByUid(String uid);

}
