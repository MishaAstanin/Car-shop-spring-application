package ru.mirea.auto_shop.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.auto_shop.DTO.OrderDto;
import ru.mirea.auto_shop.entities.Car;
import ru.mirea.auto_shop.entities.Order;
import ru.mirea.auto_shop.entities.User;
import ru.mirea.auto_shop.repositories.CarRepository;
import ru.mirea.auto_shop.repositories.OrderRepository;
import ru.mirea.auto_shop.repositories.UserRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final SimpleDateFormat simpleDateFormat;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, SimpleDateFormat simpleDateFormat, UserRepository userRepository, CarRepository carRepository) {
        this.orderRepository = orderRepository;
        this.simpleDateFormat = simpleDateFormat;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    public Order add(OrderDto orderDto) {
        Order order = new Order();
        order.setName(orderDto.getName());
        order.setSurname(orderDto.getSurname());
        order.setPhone(orderDto.getPhone());
        order.setEmail(orderDto.getEmail());

        Date currentDate = new Date();
        String date = simpleDateFormat.format(currentDate);
        order.setDate(date);

        Car car = carRepository.findCarById(orderDto.getCarId());
        order.setCar(car);

        User user = userRepository.findByName(orderDto.getUserName());
        order.setUser(user);

        return orderRepository.save(order);
    }

    public void delete(Long id) {
        Order order = orderRepository.findOrderById(id);
        User user = order.getUser();
        user.getOrders().remove(order);
        orderRepository.deleteById(id);
    }
}
