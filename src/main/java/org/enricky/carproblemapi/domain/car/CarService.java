package org.enricky.carproblemapi.domain.car;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Transactional
    public void saveCar(Car car) {
        carRepository.save(car);
    }

    @Transactional
    public Car getCarById(UUID carId) {
        return carRepository.findById(carId).orElse(null);
    }

    @Transactional
    public Car getCarByName(String name){
        return carRepository.findByName(name);
    }


    // Outros métodos conforme necessário
}
