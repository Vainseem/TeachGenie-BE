package com.ai.entity.vo.request;

import com.ai.entity.dto.Task;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;


@Data
public class TaskVO {
    String uid;
    String title;
    String info;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date ddl;
    boolean state;

    public Task toTask(String uid, int seq) {
        Task task = new Task();
        task.setUid(uid);
        task.setTitle(title);
        task.setInfo(info);
        task.setDdl(ddl);
        task.setState(state);
        task.setSeq(seq);
        return task;
    }
}
