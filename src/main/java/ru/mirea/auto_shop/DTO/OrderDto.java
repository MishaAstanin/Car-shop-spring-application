package ru.mirea.auto_shop.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String userName;
    private Long carId;
}
