# E-Lease

A backend application for the lease of electric vehicles.

## General
The **E-Lease** is a simple application for the lease of electric vehicles such as cars, scooters and bicycles.
A user should create an account in order to use the service.
Once you have created an account, you are able to choose from a range of membership options (one day, one month, one year). Some extras are also available, e._g. parking fees inclusion_. 
The moment you have purchased a membership, you are able to choose from a number of vehicles that are added by an admin. 


## The functionality
E-Lease is built on basis of **CRUD operations**.

The application security is based on two roles: **user** and **admin**.

The user can _up- and download files_.

## Built with
The application was built with :

[![My Skills](https://skillicons.dev/icons?i=java)](https://skillicons.dev) framework
 SpringBoot.

The database I have used :

[![My Skills](https://skillicons.dev/icons?i=postgres)](https://skillicons.dev)
PostgreSQL.


## Installation

In order to run the application you need to have:
-[ ] any IDE, I have used IntelliJ IDEA, you may download it here -> https://www.jetbrains.com/idea/ ;
- [ ] a running database application, in my case PostgreSQL, to download -> https://www.postgresql.org/ ; 
- [ ] a development and administration platform for the database, pgAdmin, to download -> https://www.pgadmin.org/ .

Once you have finished the preparations, you would need to download the source code from this page and open it in IntelliJ.

Then you would need to change the application properties according to your own settings: 

spring.datasource.username = your username

spring.datasource.password = your password.

In order to test the application I recommend installing

[![My Skills](https://skillicons.dev/icons?i=postman)](https://skillicons.dev)
Postman API Platform, to download -> https://www.postman.com/ 

The application would require authorization and authentication. You may use the provided roles from the data.sql file, or create your own user.

_P.S. Only existing accounts with admin role may add new admins._


_~This API is created as final assignment for 💫NOVI Hogeschool 2023~_