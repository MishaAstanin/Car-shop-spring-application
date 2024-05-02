package ru.mirea.auto_shop.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.auto_shop.entities.Car;
import ru.mirea.auto_shop.repositories.CarRepository;


import java.util.List;

@Service
@Transactional
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car getById(Long id) {
        return carRepository.findCarById(id);
    }
}
