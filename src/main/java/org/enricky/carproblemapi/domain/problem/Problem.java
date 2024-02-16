package org.enricky.carproblemapi.domain.problem;

import jakarta.persistence.*;
import org.enricky.carproblemapi.domain.car.Car;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
public class Problem {
    @Id
    UUID id;

    @ManyToMany(mappedBy = "problems")  // Specify the mapped by attribute to point back to the owning side
    Set<Car> cars;

    String name;
    String description;
    String severity;
    int recall;
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }


}
