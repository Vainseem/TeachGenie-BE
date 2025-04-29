package com.ai.service;

import com.ai.entity.dto.Account;
import com.ai.entity.dto.Detail;
import com.ai.mapper.AccountMapper;
import com.ai.repo.detailRepo.DetailRepository;
import jakarta.annotation.Resource;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Service;

@Service
public class DetailService {
    @Resource
    DetailRepository detailRepository;

    @Resource
    AccountMapper accountMapper;

    public Detail getDetail(String uid) {
        if (!detailRepository.existsByUid(uid)) {
            Account account = accountMapper.findByUid(uid);
            Detail detail = new Detail(uid,account.getUsername(),null);
            detailRepository.save(detail);
            return detail;
        }
        return detailRepository.findByUid(uid);
    }

    public void updateDetail(String uid, Detail detail) {
        Detail findDetail = detailRepository.findByUid(uid);
        if (findDetail != null) {
            detail.setId(findDetail.getId());
        }
        Account byUid = accountMapper.findByUid(uid);
        byUid.setUsername(detail.getUsername());
        accountMapper.updateById(byUid);
        detailRepository.save(detail);
    }
}
