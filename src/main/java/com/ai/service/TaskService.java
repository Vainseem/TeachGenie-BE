package com.ai.service;

import com.ai.entity.dto.Task;
import com.ai.entity.vo.request.FinishTaskVO;
import com.ai.entity.vo.request.TaskVO;
import com.ai.entity.vo.response.TaskResVO;
import com.ai.repo.taskRepo.TaskRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.List;

@Service
public class TaskService {
    @Resource
    TaskRepository taskRepository;

    public void addTask(String uid, TaskVO taskVO) {
        Integer seq = taskRepository.findMaxSeqByUid(uid);
        if (seq == null) {
            seq = 0;
        }
        taskRepository.save(taskVO.toTask(uid, seq + 1));
    }

    public String finishTask(String uid, FinishTaskVO finishTaskVO) {
        Task task = taskRepository.findByUidAndTitle(uid, finishTaskVO.getTitle());
        if (task == null) {
            return "该任务本身不存在（你传了个空title或不合法title）";
        }
        task.setState(false);
        taskRepository.save(task);
        return null;
    }

    public TaskResVO getTaskHistory(String uid) {
        if (!taskRepository.existsByUid(uid)) {
            return new TaskResVO(uid, List.of(), List.of(), List.of(), List.of());
        }
        List<Task> tasks = taskRepository.findByUidOrderBySeq(uid);
        List<String> allTitle = tasks.stream().map(Task::getTitle).toList();
        List<String> allInfo = tasks.stream().map(Task::getInfo).toList();
        List<Boolean> allState = tasks.stream().map(Task::isState).toList();
        List<Date> allDdl = tasks.stream().map(Task::getDdl).toList();
        return new TaskResVO(uid, allTitle, allInfo, allDdl, allState);
    }
}
