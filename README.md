# 📚 Book CRUD API

A robust RESTful CRUD application built with Spring Boot for managing books, featuring comprehensive book management capabilities through a REST API.

## ✨ Features

-- Full CRUD operations for books
- Search functionality with multiple parameters
- ISBN-based book retrieval
- Genre and language-based filtering
- Availability status tracking
- RESTful API architecture
- Data persistence with MySQL
- Simple and efficient book management interface

## 🛠️ Technologies

- **Backend:** Spring Boot
- **Database:** MySQL
- **ORM:** Spring Data JPA
- **Build Tool:** Maven
- **Utilities:** Lombok
- **Architecture:** REST API

## 🚀 Installation

### Prerequisites

- JDK 17+
- MySQL
- Maven

### Setup Steps

1.  **Clone the repository**

```
git clone https://github.com/NenadCvitan/BookLibrary-REST-API---A-CRUD-Application-with-Spring-Boot.git
cd BookLibrary-REST-API---A-CRUD-Application-with-Spring-Boot

```

### 2. Database Setup
Create a database for the project:

```sql
CREATE DATABASE book_db;
````


### 3. Configure Database Connection
Open the file `src/main/resources/application.properties` and update the database connection details:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/book_db
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

 ```



### 4. Build and Run the Project
To build and run the project using Maven, execute the following commands:

```bash
mvn clean install
mvn spring-boot:run
```



### API Endpoints
| Method | Endpoint               | Description                       |
|--------|------------------------|-----------------------------------|
| POST   | /books/addBook          | Create a new book                 |
| GET    | /books/{id}             | Retrieve book by ID               |
| GET    | /books/{isbn}           | Retrieve book by ISBN             |
| GET    | /books/getAll           | List all books                    |
| GET    | /books/search           | Search books with parameters      |
| GET    | /books/{genre}          | Filter books by genre             |
| GET    | /books/{language}       | Filter books by language          |
| GET    | /books/{availability}   | Filter books by availability      |
| DELETE | /books/{id}             | Delete a book                     |
| PUT    | /books/{id}             | Update book details               |

### Usage Examples

#### Add New Book
```json
{
  "title": "Java Programming",
  "author": "John Doe",
  "isbn": "978-3-16-148410-0",
  "publisher": "TechBooks Publishing",
  "price": 29.99,
  "genre": "Programming",
  "language": "English",
  "availability": "In Stock"
}
```

#### Update Book
```json
{
  "title": "Advanced Java Programming",
  "author": "Jane Doe",
  "isbn": "978-3-16-148410-0",
  "publisher": "TechBooks Publishing",
  "price": 39.99,
  "genre": "Programming",
  "language": "English",
  "availability": "In Stock"
}
```

### Contributing
Fork the repository  
Create your feature branch: 

```bash
git checkout -b feature/amazing-feature
```

Commit your changes:
```bash
git commit -m 'Add amazing feature'
```

Push to branch
```bash
git push origin feature/amazing-feature
```

### License
This project is licensed under the MIT License - see the LICENSE file for details.

### Best Practices und Codequality

- The code follows the principles of Clean Code to ensure maintainability and extensibility.

- A consistent and clear style is used, adhering to common Java conventions.

- All classes and methods are well-commented to make the code understandable for other developers.

### API testing
To test the API endpoints, you can use a tool like Postman.


### Made with ❤️ by Nenad







