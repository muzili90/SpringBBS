create database myspringbbssampledb;

CREATE USER 'myspringbbs'@'localhost' IDENTIFIED BY 'myspringbbs'; 

GRANT All ON myspringbbssampledb.* TO 'myspringbbs'@'localhost';
