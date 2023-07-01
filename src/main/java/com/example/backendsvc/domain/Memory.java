package com.example.backendsvc.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Memory {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;
}
