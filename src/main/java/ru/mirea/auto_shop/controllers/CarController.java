package ru.mirea.auto_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.auto_shop.entities.Car;
import ru.mirea.auto_shop.services.CarService;

import java.util.List;

@Controller
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String getAllCars(Model model) {
        List<Car> cars = carService.getAll();
        model.addAttribute("cars", cars);
        return "catalog";
    }

    @GetMapping("/cars/{id}")
    public String getCarPage(@PathVariable Long id, Model model) {
        Car car = carService.getById(id);
        model.addAttribute("car", car);
        return "carPage";
    }
}
