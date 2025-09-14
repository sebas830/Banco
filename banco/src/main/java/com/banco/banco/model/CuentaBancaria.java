package com.banco.banco.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cuentas_bancarias")
public class CuentaBancaria {

    // Id unico de cada cuenta, autogenerado por la base de datos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Numero de cuenta unico y obligatorio
    @Column(nullable = false, unique = true)
    private String numeroCuenta;

    // Saldo actual de la cuenta, obligatorio
    @Column(nullable = false)
    private Double saldo;

    // Relacion con el usuario dueño de la cuenta
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Constructor vacio necesario para JPA
    public CuentaBancaria() {}

    // Constructor para crear cuentas con datos iniciales
    public CuentaBancaria(String numeroCuenta, Double saldo, Usuario usuario) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.usuario = usuario;
    }

    // Getters y setters para acceder y modificar los atributos
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public Double getSaldo() { return saldo; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
