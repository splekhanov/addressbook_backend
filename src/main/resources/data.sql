INSERT INTO role (id, name) VALUES (1, 'admin');
INSERT INTO user (id, name, password, enabled) VALUES (1, 'flash', '$2a$10$BEebJV2WCUPndkLvYiDbB.1MmvsdIvcdQIhD60mj/IXmy9QaUcBWy', true );
INSERT INTO contact (id, first_name, last_name, phone, email, user_id) VALUES (1, 'Brad', 'Pitt', '+79811543120', 'bradpitt@yahoo.com', 1)