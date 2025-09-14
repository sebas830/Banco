package com.banco.banco.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {

    // Id unico de cada usuario, autogenerado por la base de datos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de usuario unico y obligatorio (para login)
    @Column(nullable = false, unique = true)
    private String username;

    // Contrasena del usuario, obligatoria
    @Column(nullable = false)
    private String password;

    // Rol del usuario: CLIENTE, EMPLEADO, ADMIN
    @Column(nullable = false)
    private String rol;

    // Relacion con las cuentas bancarias de este usuario
    // mappedBy indica que la relacion se maneja desde CuentaBancaria.usuario
    // Cascade ALL: si se borra el usuario, se borran sus cuentas
    // orphanRemoval true: si se quita una cuenta del Set, tambien se borra de la BD
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CuentaBancaria> cuentas = new HashSet<>();

    // Constructor vacio necesario para JPA
    public Usuario() {}

    // Constructor para crear un usuario con datos iniciales
    public Usuario(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    // Getters y setters para acceder y modificar los atributos
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Set<CuentaBancaria> getCuentas() { return cuentas; }
    public void setCuentas(Set<CuentaBancaria> cuentas) { this.cuentas = cuentas; }
}
