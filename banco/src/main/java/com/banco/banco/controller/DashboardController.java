package com.banco.banco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    // Cuando un cliente entra a su dashboard
    @GetMapping("/cliente/dashboard")
    public String clienteDashboard() {
        // devuelve la vista cliente-dashboard.html ubicada en templates
        return "cliente-dashboard";
    }

    // Cuando un empleado entra a su dashboard
    @GetMapping("/empleado/dashboard")
    public String empleadoDashboard() {
        // devuelve la vista empleado-dashboard.html
        return "empleado-dashboard";
    }

    // Cuando un admin entra a su dashboard
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        // devuelve la vista admin-dashboard.html
        return "admin-dashboard";
    }

    // Cuando alguien entra a la raiz del sitio "/"
    @GetMapping("/")
    public String slachRoot () {
        // devuelve la pagina publica home.html
        return "home";
    }

    // Pagina publica accesible desde /publico
    @GetMapping("/publico")
    public String publicoPage() {
        // tambien devuelve home.html
        return "home";
    }

    // Pagina publica accesible desde /home
    @GetMapping("/home")
    public String home() {
        // otra forma de acceder a la landing page
        return "home";
    }
}

