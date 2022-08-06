USE Seguridad;

INSERT INTO users(user_name, user_pass) VALUES ('marahaja',MD5('tomcat'));
INSERT INTO user_roles(user_name,role_name) VALUES ('marahaja','user');
INSERT INTO user_roles(user_name,role_name) VALUES ('marahaja','manager-gui');