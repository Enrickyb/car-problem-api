package org.enricky.carproblemapi.domain.ProblemCar;

import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Table(name = "car_problem")
public class ProblemCar {
    private UUID problemId;
    private UUID carId;

    // getters e setters
}
