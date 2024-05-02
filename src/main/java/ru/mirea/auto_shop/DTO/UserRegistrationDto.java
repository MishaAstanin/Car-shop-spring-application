package ru.mirea.auto_shop.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {
    private String name;
    private String password;

    public UserRegistrationDto() {

    }
}
