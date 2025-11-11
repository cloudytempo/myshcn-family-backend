package org.example.dto;

import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationshipResponse {
    private Long id;
    private Long person1Id;
    private Long person2Id;
    private String type;
    private Integer startDateYear;
    private Integer endDateYear;
    private Instant createdAt;
}

