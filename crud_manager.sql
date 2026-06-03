CREATE DATABASE IF NOT EXISTS crud_manager;

USE crud_manager;

CREATE TABLE IF NOT EXISTS users(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    sexo ENUM('M', 'F'),
    email VARCHAR(150) NOT NULL,
    password VARCHAR(255) NOT NULL;
    
);

CREATE TABLE IF NOT EXISTS posts(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    post_date DATE NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY(user_id) 
    REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS companies (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(128) NOT NULL,
	`role` VARCHAR(128) NOT NULL,
	`start` DATE NOT NULL,
	`end` DATE,
	user_id INT NOT NULL,
	FOREIGN KEY(user_id)
	REFERENCES users(id)
);
CREATE TABLE IF NOT EXISTS  workout (
    id INT PRIMARY KEY AUTO_INCREMENT,
    workout_name VARCHAR(100) NOT NULL,
    duration_minutes INT NOT NULL,
    intensity VARCHAR(30) NOT NULL,
    trainer VARCHAR(100) NOT NULL,
    user_id INT NOT NULL,

    CONSTRAINT fk_workout_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
); 