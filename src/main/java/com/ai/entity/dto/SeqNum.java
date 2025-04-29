package com.ai.entity.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "seq_num")
public class SeqNum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String uid;
    int seq_num;

    public SeqNum(String uid, int seq_num) {
        this.uid = uid;
        this.seq_num = seq_num;
    }
}
