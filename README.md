// ...existing code...
# EMS Backend (Employee Management System)

Backend service for the EMS project — a Spring Boot 3.x REST API that manages employee data and authentication using JWT.

## Features
- REST endpoints to manage employees (CRUD)
- Authentication with JWT (login / register)
- Passwords stored encoded with BCrypt
- Spring Data JPA repository for persistence
- Configurable datasource (H2 / PostgreSQL / MySQL)
- Simple role support (ROLE_USER by default)

## Tech stack
- Java 17+
- Spring Boot 3.x (Jakarta packages)
- Spring Security + JWT
- Spring Data JPA
- Maven
- H2 (default for dev) or any relational DB supported by Spring Data JPA

## Quick start (development)
1. Clone repository
   git clone <repo-url>
   cd ems-backend

2. Configure application
   - Edit src/main/resources/application.properties (or use environment variables)
   - Important properties:
     - spring.datasource.url
     - spring.datasource.username
     - spring.datasource.password
     - spring.jpa.hibernate.ddl-auto (e.g., update)
     - app.jwt.secret (or jwt.secret depending on JwtHelper)
     - app.jwt.expiration (seconds)

   Default dev setup uses H2 in-memory DB.

3. Build and run
   mvn -DskipTests spring-boot:run
   or
   mvn -DskipTests package
   java -jar target/*.jar

Server runs on http://localhost:8080 by default.

## Auth endpoints
- POST /api/auth/register
  - Body: { "name": "...", "email": "user@example.com", "password": "Secret123!" }
  - Creates user (password encoded)

- POST /api/auth/login
  - Body: { "email": "user@example.com", "password": "Secret123!" }
  - Response: { "token": "eyJ..." }

Use returned token in Authorization header for protected endpoints:
Authorization: Bearer <token>

## Example curl
Register:
curl -X POST http://localhost:8080/api/auth/register -H "Content-Type: application/json" -d '{"name":"Test","email":"t@x.com","password":"Pass123!"}'

Login:
curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d '{"email":"t@x.com","password":"Pass123!"}'

Access protected endpoint:
curl -H "Authorization: Bearer <token>" http://localhost:8080/api/employees

## Development notes
- Employee entity must expose getEmail() and getPassword() and store encoded password.
- JwtHelper secret/algorithm must match token generation/validation library (jjwt).
- Check jakarta.* vs javax.* imports if migrating between Spring Boot versions.

## Tests
Run unit/integration tests:
mvn test

## Contributing
- Create an issue for major changes
- Fork, branch, add tests, and open a PR

## License
Specify license in LICENSE file (e.g., MIT).
// ...existing code...