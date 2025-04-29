package com.ai.service;

import com.ai.entity.dto.Chat;
import com.ai.entity.dto.ChatNameInfo;
import com.ai.entity.vo.request.ChatVO;
import com.ai.entity.vo.response.ChatHistoryVO;
import com.ai.repo.chatRepo.ChatNameInfoRepository;
import com.ai.repo.chatRepo.ChatRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {
    @Resource
    ChatRepository chatRepository;
    @Resource
    ChatNameInfoRepository nameRepository;

    public List<String> getChatNameInfo(String uid) {
        return nameRepository.findNameByUidOrderBySeqAsc(uid)
                .stream()
                .map(ChatNameInfo::getName)
                .toList();
    }

    public ChatHistoryVO getChatHistory(String uid,String name){
        List<Chat> chatList = chatRepository.findByUidAndNameOrderBySeq(uid,name);
        List<String> ques=chatList
                .stream()
                .map(Chat::getQues)
                .toList();
        List<String> resp=chatList
                .stream()
                .map(Chat::getResp)
                .toList();
        return new ChatHistoryVO(uid,name,ques,resp);
    }

    public void updateChat(String uid, ChatVO chatVO){
        if (!nameRepository.existsByUidAndName(uid,chatVO.getName())){
            Integer nameSeq=nameRepository.findMaxSeqByUid(uid);
            if(nameSeq==null){
                nameSeq=0;
            }
            ChatNameInfo chatNameInfo = new ChatNameInfo(uid, chatVO.getName(), nameSeq + 1);
            nameRepository.save(chatNameInfo);
        }

        Integer chatSeq = chatRepository.findMaxSeqByUidAndName(uid,chatVO.getName());
        if(chatSeq==null){
            chatSeq=0;
        }
        Chat chat = new Chat(uid,chatVO.getName(),chatVO.getQues(),chatVO.getResp(),chatSeq+1);
        chatRepository.save(chat);
    }
}
