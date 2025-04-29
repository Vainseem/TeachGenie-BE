package com.ai.controller;

import com.ai.entity.RestBean;
import com.ai.entity.dto.Feedback;
import com.ai.entity.vo.request.FeedbackReqVO;
import com.ai.entity.vo.response.FeedbackResVO;
import com.ai.service.FeedbackService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Resource
    FeedbackService feedbackService;

    @GetMapping("/fetch/{uid}")
    public String getFeedback(@PathVariable("uid") String uid) {
        List<FeedbackResVO> feedback = feedbackService.getFeedback(uid);
        return RestBean.success(feedback).asJsonString();
    }

    @PostMapping("/add/{sid}")
    public String addFeedback(
            @PathVariable("sid") String sid,
            @RequestBody FeedbackReqVO feedback
    ) {
        feedbackService.addFeedback(sid, feedback);
        return RestBean.success("成功添加feedback").asJsonString();
    }

    @PostMapping("/finish/{uid}")
    public String finishFeedback(
            @PathVariable("uid") String uid,
            @RequestParam Integer seq
    ) {
        String s = feedbackService.finishFeedback(uid, seq);
        return RestBean.success(s).asJsonString();
    }
}
