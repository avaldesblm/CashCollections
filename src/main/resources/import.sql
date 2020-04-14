INSERT INTO Centros_de_venta (name, host, port, modification_date) VALUES('ceve_1','10.10.10.10',8080,CURRENT_TIMESTAMP);
INSERT INTO Centros_de_venta (name, host, port, modification_date) VALUES('ceve_2','10.10.10.20',8080,CURRENT_TIMESTAMP);
INSERT INTO Centros_de_venta (name, host, port, modification_date) VALUES('ceve_3','10.10.10.30',8080,CURRENT_TIMESTAMP);
INSERT INTO Bitacora (user_name, request_date, validation_pin, ceve, route, bimbo_code, amount, status, ip, method, url, rsp_Description) VALUES('avaldes@bluelabel.mx',CURRENT_TIMESTAMP,'321654987321654987321654987321','ceve_1','321','321654',100.00,200,'10.10.10.10','cancelPin','url','success');
INSERT INTO Bitacora (user_name, request_date, validation_pin, ceve, route, bimbo_code, amount, status, ip, method, url, rsp_Description) VALUES('rdiaz@bluelabel.mx',CURRENT_TIMESTAMP,'321654987321654987321654987322','ceve_2','654','654321',200.00,200,'10.10.10.10','cancelPin','url','success');
INSERT INTO Roles (nombre) values('ROLE_ADMIN')
INSERT INTO Roles (nombre) values('ROLE_AUDIT')
INSERT INTO Usuarios (username, password, nombre, apellido, mail) VALUES('alex', '$2a$10$oAvy3pRsWxe5XbvdVrZKKeyQ6IQMgoGh6cDmO0ERydaD1.i73VTNe', 'Alejandro', 'Valdes', 'avaldes@bluelabel.mx');
INSERT INTO Usuarios (username, password, nombre, apellido, mail) VALUES('raul', '$2a$10$jBi6WN6BeREjtc7/6zRXq.bvKZazc1LfsoWAC2P.Vr8fHhYZfDDp6', 'Raul', 'Diaz', 'rdiaz@bluelabel.mx');
INSERT INTO usuarios_roles (user_id, role_id) values (1,1);
INSERT INTO usuarios_roles (user_id, role_id) values (2,2);