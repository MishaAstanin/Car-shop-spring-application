package ru.mirea.auto_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.auto_shop.DTO.OrderDto;
import ru.mirea.auto_shop.services.EmailService;
import ru.mirea.auto_shop.services.OrderService;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final EmailService emailService;

    @Autowired
    public OrderController(OrderService orderService, EmailService emailService) {
        this.orderService = orderService;
        this.emailService = emailService;
    }

    @GetMapping("/createOrder")
    public String pageToCreateOrder(Model model, @RequestParam("carId") Long carId) {
        model.addAttribute("carId", carId);
        model.addAttribute("orderDTO", new OrderDto());
        return "createOrder";
    }

    @PostMapping("/createOrder")
    public String createOrder(@ModelAttribute OrderDto orderDto, @AuthenticationPrincipal UserDetails user, @RequestParam("carId") Long carId) {
        orderDto.setUserName(user.getUsername());
        orderDto.setCarId(carId);
        emailService.sendEmail(orderDto.getEmail(), "New order added", "A new order has been added.");
        orderService.add(orderDto);
        return "redirect:/profile";
    }

    @PostMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return "redirect:/profile";
    }
}
