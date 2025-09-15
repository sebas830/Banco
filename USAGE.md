<!-- Descripción del proyecto, cómo ejecutarlo USAGE.md--> 
# 📄 USAGE.md — Guía de Uso del Banco Digital

Este documento proporciona una guía práctica para utilizar el **Banco Digital**, desarrollado como parte del taller 1 de la asignatura **Lenguaje de Programación Avanzado 1**.  
El objetivo del sistema es simular un banco real, permitiendo el registro de usuarios, inicio de sesión seguro y la gestión de operaciones financieras a través de una plataforma web.

---

# 👥 Ejecución por parte del usuario

El usuario puede ejecutar el programa de forma local siguiendo estos pasos:

1. Clona el repositorio:
   ```bash
   git clone https://github.com/sebas830/Banco.git
   ```
2. Abre el proyecto en tu IDE preferido (IntelliJ IDEA o Spring Tools Suite).
3. Compila el proyecto con Maven:
   ```bash
   mvn clean install
   ```
4. Ejecuta la aplicación:
   ```bash
   BancoApplication
   ```
5. Accede al sistema en tu navegador:
   ```
   http://localhost:8080
   ```

---

# 📘 Casos de Uso del Banco Digital

### 🧍‍♀️ Clientes
- Iniciar sesión en el sistema con sus credenciales.
- Consultar su saldo disponible.
- Revisar historial de ingresos y egresos.
- Realizar transferencias nacionales a otras cuentas.

### 🧑‍💼 Empleados
- Acceder al panel de empleados mediante login.
- Visualizar información de clientes.
- Apoyar en la gestión y validación de cuentas.

### 👨‍💻 Administradores
- Monitorear las transacciones realizadas en el sistema.
- Supervisar la seguridad del sistema mediante roles y permisos.

---

# 🙌 Agradecimientos

Este sistema fue desarrollado como parte del taller 1 de la asignatura **Lenguaje de Programación Avanzado 1**, con el propósito de aplicar los conocimientos adquiridos en clase:
- Seguridad con **Spring Security**.
- Persistencia con **Spring Data JPA**.
- Arquitectura **MVC**.

---

# 📬 Contacto

Si tienes preguntas o sugerencias no dudes en escribirnos:

📧 **sebasramirez830@gmail.com**  
🐙 GitHub: [@sebas830](https://github.com/sebas830)
