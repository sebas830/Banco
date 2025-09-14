-- Elimina registros anteriores
DELETE FROM cuentas_bancarias;
DELETE FROM usuarios;

-- Insertar usuarios con contraseña "1234" encriptada con BCrypt
INSERT INTO usuarios (id, username, password, rol) VALUES
(1, 'cliente',  '$2a$10$GedjRiKEobvlWgAii/PiduAZZ7om7W1tpJYKlGd30mg7F7DTh5U9a', 'CLIENTE'),
(2, 'empleado', '$2a$10$GedjRiKEobvlWgAii/PiduAZZ7om7W1tpJYKlGd30mg7F7DTh5U9a', 'EMPLEADO'),
(3, 'admin',    '$2a$10$GedjRiKEobvlWgAii/PiduAZZ7om7W1tpJYKlGd30mg7F7DTh5U9a', 'ADMIN');

-- Insertar cuentas bancarias asociadas
INSERT INTO cuentas_bancarias (numero_cuenta, saldo, usuario_id) VALUES
('1001', 5000000.00, 1),
('2001', 15000000.50, 2),
('3001', 750000.75, 3);
