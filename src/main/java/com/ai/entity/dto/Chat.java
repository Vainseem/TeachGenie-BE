package com.ai.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "chat")
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    int id;
    String uid;
    String ques;
    String resp;
    @Column(name = "chat_name")
    String name;
    int seq;

    public Chat(String uid, String name, String ques, String resp, int seq) {
        this.uid = uid;
        this.name = name;
        this.ques = ques;
        this.resp = resp;
        this.seq = seq;
    }
}
