package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "relationships", uniqueConstraints = @UniqueConstraint(columnNames = {"person1_id", "person2_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "person1_id", nullable = false)
    private Long person1Id;

    @Column(name = "person2_id", nullable = false)
    private Long person2Id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type = Type.PARTNERED;

    @Column(name = "start_date_year")
    private Integer startDateYear;

    @Column(name = "end_date_year")
    private Integer endDateYear;

    @Column(name = "created_at")
    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }

    public enum Type {
        MARRIED, DIVORCED, PARTNERED, SEPARATED, WIDOWED
    }
}

