package com.example.backendsvc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMORY_SEQ")
    private Long id;
    private String title;
    private String content;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    @ManyToOne
    private User author;
}
