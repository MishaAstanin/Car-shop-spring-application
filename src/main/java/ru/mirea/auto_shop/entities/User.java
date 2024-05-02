package ru.mirea.auto_shop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "client")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Order> orders;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn
                    (name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn
                    (name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public User() {

    }

    public User(String name, String password, Collection<Role> roleAdmin) {
        this.name = name;
        this.password = password;
        this.roles = roleAdmin;
    }
}
