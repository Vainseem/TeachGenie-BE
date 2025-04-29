package com.ai.entity.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class TaskResVO {
    String uid;
    List<String> allTitle;
    List<String> allInfo;
    List<Date> allDdl;
    List<Boolean> allState;
}
