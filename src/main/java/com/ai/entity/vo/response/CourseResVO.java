package com.ai.entity.vo.response;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseResVO {
    String uid;
    String[][] schedule;
    int scheLen;
}
