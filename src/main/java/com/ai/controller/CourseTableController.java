package com.ai.controller;

import com.ai.entity.RestBean;
import com.ai.entity.vo.request.CourseReqVO;
import com.ai.entity.vo.response.CourseResVO;
import com.ai.service.DayService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courseTable")
public class CourseTableController {
    @Resource
    DayService dayService;

    @RequestMapping("/course/{uid}")
    public String getCourseTable(@PathVariable("uid") String uid) {
        CourseReqVO courseTable = dayService.getCourseTable(uid);
        return courseTable == null ?
                RestBean.failure(400, "请先创建课程表").asJsonString()
                : RestBean.success(courseTable).asJsonString();
    }

    @PostMapping("/update/{uid}")
    public String updateCourseTable(
            @PathVariable("uid") String uid,
            @RequestBody CourseResVO courseTable) {
        String[][] course = courseTable.getSchedule();
        int num = courseTable.getScheLen();
        dayService.updateCourseTable(uid, course, num);
        return RestBean.success("成功更新课表").asJsonString();
    }
}
