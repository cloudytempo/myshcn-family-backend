package org.example.dto;

import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentChildResponse {
    private Long id;
    private Long parentId;
    private Long childId;
    private String type;
    private Instant createdAt;
}

