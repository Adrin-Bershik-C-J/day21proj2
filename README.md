# Hostel Management System

A comprehensive Spring Boot application for managing hostel operations including student management, room allocation, and administrative functions with role-based access control and JWT authentication.

## 🏗️ Architecture

This application follows a layered architecture with:
- **Controller Layer**: REST API endpoints
- **Service Layer**: Business logic implementation  
- **Repository Layer**: Data access using Spring Data JPA
- **Entity Layer**: JPA entities with relationships
- **Security Layer**: JWT-based authentication with role-based authorization

## 🚀 Features

### Authentication & Authorization
- JWT-based authentication
- Role-based access control (ADMIN, WARDEN, USER)
- Secured endpoints with appropriate role restrictions

### Admin Operations
- Create new students
- Update student information
- Delete students
- Full CRUD operations

### Warden Operations  
- Allocate rooms to students
- Manage room assignments

### Student Operations
- View all students with filtering capabilities
- Filter by: ID, name, age, room number, fee payment status
- Paginated and sorted results
- Advanced search functionality

### Data Management
- Student-Room-Admin relationship management
- Automatic data seeding on application startup
- MapStruct for DTO mapping

## 🛠️ Technologies Used

- **Framework**: Spring Boot 3.x
- **Security**: Spring Security with JWT
- **Database**: JPA/Hibernate with H2 (can be configured for other databases)
- **Documentation**: Swagger/OpenAPI 3
- **Mapping**: MapStruct
- **Build Tool**: Maven
- **Java Version**: 17+

## 📋 API Endpoints

### Authentication
```
POST /api/auth - User login and JWT token generation
```

### Admin Endpoints (Requires ADMIN role)
```
POST /api/admin - Create student
PUT /api/admin/{id} - Update student
DELETE /api/admin/{id} - Delete student
```

### Warden Endpoints (Requires WARDEN role)  
```
PATCH /api/warden/allocate/{id} - Allocate room to student
```

### Student Endpoints (Requires ADMIN, USER, or WARDEN role)
```
GET /api/student - Get students with optional filters
GET /api/student/all - Get paginated and sorted students
```

## 🔐 Default Users

The application comes with pre-configured users:

| Username | Password | Role | Access |
|----------|----------|------|---------|
| admin | admin123 | ADMIN | Full access to all endpoints |
| warden | warden123 | WARDEN | Room allocation and student viewing |
| user | user123 | USER | Student viewing only |

## 📊 Sample Data

The application automatically loads sample data on startup:
- 2 Wardens (John Smith, Emma Davis)
- 3 Rooms (101, 102, 201) 
- 10 Students with various details and room assignments

## 🏃‍♂️ Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Adrin-Bershik-C-J/day21proj2.git
   cd day21proj2
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - Application: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`

## 🔧 Configuration

### Database Configuration
The application uses H2 in-memory database by default. To use a different database, update `application.properties`:

```properties
# For MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/hostel_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

# For PostgreSQL  
spring.datasource.url=jdbc:postgresql://localhost:5432/hostel_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

### JWT Configuration
JWT settings can be modified in `JwtService.java`:
- Token expiration time
- Secret key
- Signing algorithm

## 📖 API Usage Examples

### 1. Login to get JWT token
```bash
curl -X POST http://localhost:8080/api/auth \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

### 2. Create a new student (Admin only)
```bash
curl -X POST http://localhost:8080/api/admin \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "name": "John Doe",
    "age": 20,
    "feePaid": true,
    "room": {
      "roomNumber": 101
    }
  }'
```

### 3. Filter students
```bash
curl -X GET "http://localhost:8080/api/student?age=21&feePaid=true" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### 4. Get paginated students
```bash
curl -X GET "http://localhost:8080/api/student/all?page=0&size=3&sort=name,asc" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## 🏛️ Database Schema

### Entities Relationship
- `Admin` (Warden) → One-to-Many → `Room`
- `Room` → One-to-Many → `Student`  
- `Student` → Many-to-One → `Room`

### Key Fields
- **Student**: id, name, age, feePaid, room_id
- **Room**: id, roomNumber, warden_id  
- **Admin**: id, name

## 🔒 Security Features

- JWT token-based authentication
- Role-based authorization
- Secure password storage  
- CORS configuration
- Request/Response filtering
- Bearer token validation

## 📚 Dependencies

Key dependencies used in this project:
- Spring Boot Starter Web
- Spring Boot Starter Security
- Spring Boot Starter Data JPA
- SpringDoc OpenAPI (Swagger)
- MapStruct
- JJWT (JWT implementation)
- Lombok

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Adrin Bershik C J**
- GitHub: [@Adrin-Bershik-C-J](https://github.com/Adrin-Bershik-C-J)
- Repository: [day21proj2](https://github.com/Adrin-Bershik-C-J/day21proj2)

## 🆘 Support

If you encounter any issues or have questions:
1. Check the existing [Issues](https://github.com/Adrin-Bershik-C-J/day21proj2/issues)
2. Create a new issue with detailed information
3. Provide steps to reproduce the problem

---

⭐ **If you find this project useful, please consider giving it a star!** ⭐
