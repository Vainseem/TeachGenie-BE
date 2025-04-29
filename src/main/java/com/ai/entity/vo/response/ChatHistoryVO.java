package com.ai.entity.vo.response;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChatHistoryVO {
    String uid;
    String chatName;
    List<String> ques;
    List<String> resp;
}
