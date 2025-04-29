package com.ai.service;

import com.ai.entity.dto.Account;
import com.ai.entity.dto.Feedback;
import com.ai.entity.vo.request.FeedbackReqVO;
import com.ai.entity.vo.response.FeedbackResVO;
import com.ai.mapper.AccountMapper;
import com.ai.repo.feedbackRepo.FeedbackRepo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class FeedbackService {
    @Resource
    private FeedbackRepo feedbackRepo;

    @Resource
    private AccountMapper accountMapper;

    public List<FeedbackResVO> getFeedback(String uid) {
        return feedbackRepo.findByUidOrderBySeq(uid)
                .stream()
                .map(feedback -> new FeedbackResVO(feedback.getName(), feedback.getSid(),feedback.getInfo(), feedback.getTime(), feedback.isStatus(), feedback.getSeq()))
                .toList();
    }

    public void addFeedback(String sid, FeedbackReqVO feedback) {
        Feedback newFeedback = new Feedback();
        Account student = accountMapper.findByUid(sid);
        Integer seq = feedbackRepo.findMaxSeqByUid(feedback.getUid());
        newFeedback.setUid(feedback.getUid());
        newFeedback.setSid(sid);
        newFeedback.setName(student.getUsername());
        newFeedback.setInfo(feedback.getInfo());
        newFeedback.setTime(new Date());
        newFeedback.setStatus(true);
        newFeedback.setSeq(seq == null ? 1 : seq + 1);

        feedbackRepo.save(newFeedback);

    }

    public String finishFeedback(String uid, Integer seq) {
        boolean existsByUidAndSeq = feedbackRepo.existsByUidAndSeq(uid, seq);
        if (!existsByUidAndSeq) {
            return "该反馈不存在(uid/seq错误)";
        }
        Feedback byUidAndSeq = feedbackRepo.findByUidAndSeq(uid, seq);
        byUidAndSeq.setStatus(false);
        feedbackRepo.save(byUidAndSeq);
        return "成功完成feedback";
    }
}
