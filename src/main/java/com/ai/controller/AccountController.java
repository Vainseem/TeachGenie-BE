package com.ai.controller;

import com.ai.entity.RestBean;
import com.ai.entity.dto.Account;
import com.ai.entity.vo.response.TeacherResVO;
import com.ai.mapper.AccountMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Resource
    AccountMapper accountMapper;
    @GetMapping("/allTeacher")
    public String allTeacher() {
        List<TeacherResVO>allTeacher=accountMapper.findAllByRole("teacher")
                .stream()
                .map(account->new TeacherResVO(account.getUid(),account.getUsername()))
                .toList();
        return RestBean.success(allTeacher).asJsonString();
    }
}
