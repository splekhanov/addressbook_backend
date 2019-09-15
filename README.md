# ADDRESSBOOK RESTful WEB SERVICE
RESTful CRUD application example created with Spring Boot and Hibernate integration.

<br>Stack:
1. [Java 8](https://java.com/)
1. [Spring Framework](https://spring.io/)
1. [Spring Data](https://spring.io/projects/spring-data/)
1. [Spring Security](https://spring.io/projects/spring-security/)

## API
| Method    | Requested resource              | Action                        |
|-----------|---------------------------------|-------------------------------|
| POST      | /api/v1/registration            | Create a new user             |
| GET       | /api/v1/login                   | Login with credentials        |                                                     
| POST      | /api/v1/contacts                | Add new contact to addressbook| 
| GET       | /api/v1/contacts/{id}           | Get contact by ID             | 
| PUT       | /api/v1/contacts                | Update existing contact       |
| DELETE    | /api/contacts/{id}              | Delete contact by ID          |
