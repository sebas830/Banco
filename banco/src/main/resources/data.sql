-- Elimina registros anteriores
DELETE FROM cuentas_bancarias;
DELETE FROM usuarios;

-- Insertar usuarios con contraseña "1234" encriptada con BCrypt
INSERT INTO usuarios (id, username, password, rol) VALUES
(1, 'cliente',  '$2a$10$WzhrWmIfYTYi3wlsnJrj6e4Jsl4Z0h/kK0n6GZp9Bbfop5M1qXhJ6', 'CLIENTE'),
(2, 'empleado', '$2a$10$WzhrWmIfYTYi3wlsnJrj6e4Jsl4Z0h/kK0n6GZp9Bbfop5M1qXhJ6', 'EMPLEADO'),
(3, 'admin',    '$2a$10$WzhrWmIfYTYi3wlsnJrj6e4Jsl4Z0h/kK0n6GZp9Bbfop5M1qXhJ6', 'ADMIN');

-- Insertar cuentas bancarias asociadas
INSERT INTO cuentas_bancarias (numero_cuenta, saldo, usuario_id) VALUES
('1001', 5000.00, 1),
('2001', 15000.50, 2),
('3001', 75000.75, 3);
