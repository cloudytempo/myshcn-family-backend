package org.example.dto;

import lombok.*;
import org.example.entity.ParentChild;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentChildRequest {
    @NotNull
    private Long parentId;
    @NotNull
    private Long childId;
    private ParentChild.Type type = ParentChild.Type.BIOLOGICAL;
}

