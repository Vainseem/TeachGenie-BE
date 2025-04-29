package com.ai.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat_name_info")
public class ChatNameInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    int id;
    String uid;
    @Column(name = "chat_name")
    String name;
    @Column(name = "name_seq")
    int seq;

    public ChatNameInfo(String uid, String name, int seq) {
        this.uid = uid;
        this.name = name;
        this.seq = seq;
    }
}
