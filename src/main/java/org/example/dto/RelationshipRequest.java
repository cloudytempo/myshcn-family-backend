package org.example.dto;

import lombok.*;
import org.example.entity.Relationship;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationshipRequest {
    @NotNull
    private Long person1Id;
    @NotNull
    private Long person2Id;
    private Relationship.Type type = Relationship.Type.PARTNERED;
    private Integer startDateYear;
    private Integer endDateYear;
}

