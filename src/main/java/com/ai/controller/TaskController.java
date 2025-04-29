package com.ai.controller;

import com.ai.entity.RestBean;
import com.ai.entity.vo.request.FinishTaskVO;
import com.ai.entity.vo.request.TaskVO;
import com.ai.service.TaskService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/task")
public class TaskController {
    @Resource
    TaskService taskService;

    @PostMapping("add/{uid}")
    public String addTask(
            @PathVariable("uid") String uid,
            @RequestBody TaskVO taskVO
    ) {
        taskService.addTask(uid, taskVO);
        return RestBean.success("成功添加任务").asJsonString();
    }

    @PostMapping("finish/{uid}")
    public String finishTask(
            @PathVariable("uid") String uid,
            @RequestBody FinishTaskVO finishTaskVO
    ) {
        String message = taskService.finishTask(uid, finishTaskVO);
        return message == null ? RestBean.success("成功完成任务").asJsonString() : RestBean.failure(400, message).asJsonString();
    }

    @GetMapping("history/{uid}")
    public String historyTask(
            @PathVariable("uid") String uid
    ) {
        return RestBean.success(taskService.getTaskHistory(uid)).asJsonString();
    }

}
