package com.example.backendsvc.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Memory {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;
}
