package com.ai.entity.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class AlertResVO {
    String uid;
    List<String> allInfo;
    List<Date> allDdl;
    List<Boolean> allState;
    List<Integer> allSeq;
}
