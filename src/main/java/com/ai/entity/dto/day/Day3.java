package com.ai.entity.dto.day;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "day3")
public class Day3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String uid;
    String course;
    int seq;

    public Day3(String uid, String course, int seq) {
        this.uid = uid;
        this.course = course;
        this.seq = seq;
    }
}
