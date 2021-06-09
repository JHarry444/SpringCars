drop table if exists car CASCADE;

create table car 
(
	`id` integer AUTO_INCREMENT PRIMARY KEY,
	`colour` varchar(255), make varchar(255),
	`model` varchar(255),
	`garage_id` integer
);
