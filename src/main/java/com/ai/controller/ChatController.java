package com.ai.controller;

import com.ai.entity.RestBean;
import com.ai.entity.vo.request.ChatVO;
import com.ai.entity.vo.response.ChatNameVO;
import com.ai.service.ChatService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/chat")
public class ChatController {
    @Resource
    ChatService chatService;

    @GetMapping("nameInfo/{uid}")
    public String nameInfo(@PathVariable("uid") String uid) {
        ChatNameVO chatNameVO = new ChatNameVO(uid, chatService.getChatNameInfo(uid));
        return RestBean.success(chatNameVO).asJsonString();
    }

    @GetMapping("history/{uid}")
    public String historyChat(
            @PathVariable("uid") String uid,
            @RequestParam("name") String name
    ) {
        return RestBean.success(chatService.getChatHistory(uid, name)).asJsonString();
    }

    @PostMapping("update/{uid}")
    public String updateChat(
            @PathVariable("uid") String uid,
            @RequestBody ChatVO chatVO
    ) {
        chatService.updateChat(uid, chatVO);
        return RestBean.success("成功更新聊天记录").asJsonString();
    }
}
