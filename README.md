# Library-Managment-System
A simple GUI library Management system I made during my second year in college

I used all i learned about java to implement this project


## Some of what i Implemented
* Inheritance
* Compostition
* Interface
* Polymorphism
* Overloading and Overriding
* Multi-Threading
* Networking
* JavaFX
* JDBC Database

to use this project you need a MySQL DB
with the following tables


```
CREATE SCHEMA `library` ;

CREATE TABLE `book` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `book_title` varchar(45) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  `publisher` varchar(45) DEFAULT NULL,
  `genre` varchar(45) DEFAULT NULL,
  `year` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
)


CREATE TABLE `admins` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
)


CREATE TABLE `client` (
  `client_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `due_date` varchar(45) NOT NULL,
  `loaned_book` varchar(45) NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `loaned_book_UNIQUE` (`loaned_book`)
)

CREATE TABLE `librarian` (
  `librarian_id` int NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`librarian_id`)
)
```

launch the server jar and login your DB
then launch the client jar
