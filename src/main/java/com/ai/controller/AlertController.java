package com.ai.controller;

import com.ai.entity.RestBean;
import com.ai.entity.vo.request.AlertReqVO;
import com.ai.entity.vo.request.FinishTaskVO;
import com.ai.service.AlertService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/alert")
public class AlertController {
    @Resource
    AlertService alertService;

    @PostMapping("add/{uid}")
    public String addAlert(
            @PathVariable("uid") String uid,
            @RequestBody AlertReqVO alertReqVO
    ) {
        alertService.addAlert(uid, alertReqVO);
        return RestBean.success("成功添加提醒").asJsonString();
    }

    @PostMapping("finish/{uid}")
    public String finishAlert(
            @PathVariable("uid") String uid,
            @RequestParam("seq") int seq
    ) {
        String message = alertService.finishAlert(uid, seq);
        return message == null ? RestBean.success("成功完成提醒").asJsonString() : RestBean.failure(400, message).asJsonString();
    }

    @GetMapping("history/{uid}")
    public String historyAlert(
            @PathVariable("uid") String uid
    ) {
        return RestBean.success(alertService.getAlertHistory(uid)).asJsonString();
    }

}
