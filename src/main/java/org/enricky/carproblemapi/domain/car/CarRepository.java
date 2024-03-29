package org.enricky.carproblemapi.domain.car;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {

    public Car findByName(String name);
}
