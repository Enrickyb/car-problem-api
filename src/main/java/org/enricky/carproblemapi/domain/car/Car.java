package org.enricky.carproblemapi.domain.car;

import jakarta.persistence.*;
import lombok.Data;
import org.enricky.carproblemapi.domain.problem.Problem;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @ManyToMany
    Set<Problem> problems;

    String brand;
    String name;
    String model;
    String year;
    String engine;
    String transmission;
    String fuelType;
    String bodyType;
    String driveType;
    String color;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
