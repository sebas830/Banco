package com.banco.banco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/cliente/dashboard")
    public String clienteDashboard() {
        return "cliente-dashboard"; // templates/cliente-dashboard.html
    }

    @GetMapping("/empleado/dashboard")
    public String empleadoDashboard() {
        return "empleado-dashboard"; // templates/empleado-dashboard.html
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard"; // templates/admin-dashboard.html
    }

    @GetMapping("/")
    public String slachRoot () {
        return "home"; // templates/home.html (landing pública)
    }

    @GetMapping("/publico")
    public String publicoPage() {
        return "home"; // templates/home.html (landing pública)
    }

    @GetMapping("/home")
    public String home() {
        return "home"; // templates/home.html (landing pública)
    }
}

