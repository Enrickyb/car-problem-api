package org.enricky.carproblemapi.domain.car;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;
import java.util.UUID;

public record CarDTO(
        @NotBlank String brand,
        @NotBlank String name,
        @NotBlank String model,
        @NotBlank String year,
        @NotBlank String engine,
        String transmission,
        String fuelType,
        String bodyType,
        String driveType,
        String color,
        Set<UUID> problemIds){
    public Set<UUID> getProblemIds() {
        return problemIds;
    }
}
