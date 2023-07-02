package com.example.backendsvc.data.dto;

import com.example.backendsvc.domain.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemoryDTO {
    private String title;
    private String content;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
}
