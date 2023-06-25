package com.example.backendsvc.data.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WelcomeDTO {
    private String message;
    private String localDateTime;

}
