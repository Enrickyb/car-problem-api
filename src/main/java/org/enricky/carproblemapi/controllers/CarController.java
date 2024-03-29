package org.enricky.carproblemapi.controllers;

import org.enricky.carproblemapi.domain.ProblemCar.ProblemCar;
import org.enricky.carproblemapi.domain.car.Car;
import org.enricky.carproblemapi.domain.car.CarDTO;
import org.enricky.carproblemapi.domain.car.CarService;
import org.enricky.carproblemapi.domain.problem.Problem;
import org.enricky.carproblemapi.domain.problem.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private ProblemService problemService;

    @PostMapping("/create")
    public ResponseEntity<String> createCar(@RequestBody CarDTO carRequestDto) throws Exception {
        try {
            // Criar uma instância de Car a partir do DTO recebido
            Car car = new Car();

            Car carExist = carService.getCarByName(carRequestDto.name());

            if(carExist != null){
                return ResponseEntity.badRequest().body("Car already exists");
            }

            car.setBrand(carRequestDto.brand());
            car.setName(carRequestDto.name());
            car.setModel(carRequestDto.model());
            car.setYear(carRequestDto.year());
            car.setEngine(carRequestDto.engine());
            car.setTransmission(carRequestDto.transmission());
            car.setFuelType(carRequestDto.fuelType());
            car.setBodyType(carRequestDto.bodyType());
            car.setDriveType(carRequestDto.driveType());

            // Salvar o carro no banco de dados
            carService.saveCar(car);

            return ResponseEntity.ok("Carro criado com sucesso." + car);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error on create car");
        }
    }

    @PostMapping("/addproblem")
    public ResponseEntity<String> addProblemInCar(@RequestBody ProblemCar request) {
        try {
            UUID carId = request.getCarId();
            UUID problemId = request.getProblemId();

            Car car = carService.getCarById(carId);
            Problem problem = problemService.getProblemById(problemId);

            if (car == null) {
                return ResponseEntity.badRequest().body("Carro não existe");
            }

            if (problem == null) {
                return ResponseEntity.badRequest().body("Problema não existe");
            }

            // Criar uma instância de Problem ou recuperar do banco de dados
            Set<Problem> problems = new HashSet<>();
            problems.add(problem);

            // Estabelecer a relação many-to-many
            car.setProblems(problems);

            carService.saveCar(car);

            return ResponseEntity.ok("Problema adicionado ao carro com sucesso." + car.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao adicionar problema ao carro");
        }
    }


    @PostMapping("/problem/create")
    public ResponseEntity<String> createWithProblemsCar(@RequestBody CarDTO carRequestDto) throws Exception {
       try{
           // Criar uma instância de Car a partir do DTO recebido
           Car car = new Car();

           Car carExist = carService.getCarByName(carRequestDto.name());

           if(carExist != null){
               return ResponseEntity.badRequest().body("Car already exists");
           }
           car.setBrand(carRequestDto.brand());
           car.setName(carRequestDto.name());
           car.setModel(carRequestDto.model());
           car.setYear(carRequestDto.year());
           car.setEngine(carRequestDto.engine());
           car.setTransmission(carRequestDto.transmission());
           car.setFuelType(carRequestDto.fuelType());
           car.setBodyType(carRequestDto.bodyType());
           car.setDriveType(carRequestDto.driveType());


           // Criar uma instância de Problem ou recuperar do banco de dados
           Set<Problem> problems = new HashSet<>();
           for (UUID problemId : carRequestDto.getProblemIds()) {
               Problem problem = problemService.findById(problemId).orElse(null);
               if (problem != null) {
                   problems.add(problem);
               }
           }

           // Estabelecer a relação many-to-many
           car.setProblems(problems);

           // Salvar o carro no banco de dados
           carService.saveCar(car);

           return ResponseEntity.ok("Carro criado com sucesso." + car);
       } catch (Exception ex) {
           return ResponseEntity.badRequest().body("error on create car");
       }
    }
}