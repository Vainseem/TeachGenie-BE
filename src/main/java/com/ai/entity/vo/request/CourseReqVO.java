package com.ai.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseReqVO {
    String uid;
    String[][] schedule;
}
