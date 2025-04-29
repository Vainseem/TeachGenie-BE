package com.ai.entity.dto.day;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "day6")
public class Day6 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String uid;
    String course;
    int seq;

    public Day6(String uid, String course, int seq) {
        this.uid = uid;
        this.course = course;
        this.seq = seq;
    }
}
