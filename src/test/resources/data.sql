INSERT INTO user (user_id, first_name, enabled, last_name, email, password, user_name) VALUES (5, 'Dariusz', false, 'Maupa', 'hiremeplease@gmail.com', '$2a$10$RyY4bXtV3LKkDCutlUTYDOKd2AiJYZGp4Y7MPVdLzWzT1RX.JRZyG', 'testing');
INSERT INTO user (user_id, first_name, enabled, last_name, email, password, user_name) VALUES (6, 'Mariusz', false, 'Gorilla', 'imgonnaworkforfree@gmail.com', '$2a$10$RyY4bXtV3LKkDCutlUTYDOKd2AiJYZGp4Y7MPVdLzWzT1RX.JRZyG', 'testing1');

INSERT INTO role (id, name) VALUES (6, 'ROLE_ADMIN');
INSERT INTO role (id, name) VALUES (7, 'ROLE_MANAGER');
INSERT INTO role (id, name) VALUES (8, 'ROLE_USER');

INSERT INTO users_roles (user_id, role_id) VALUES (5, 6);
INSERT INTO users_roles (user_id, role_id) VALUES (5, 8);
INSERT INTO users_roles (user_id, role_id) VALUES (6, 6);
INSERT INTO users_roles (user_id, role_id) VALUES (6, 8);

-- INSERT INTO password_reset_token (id, expiry_date, token, user_id) VALUES (1, '2017-01-01 00:00:00', 'expired-token', 5);
-- INSERT INTO password_reset_token (id, expiry_date, token, user_id) VALUES (2, '2222-01-01 00:00:00', 'valid-token', 6);
