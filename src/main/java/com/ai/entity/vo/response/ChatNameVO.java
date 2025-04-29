package com.ai.entity.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChatNameVO {
    String uid;
    List<String> name;
}
