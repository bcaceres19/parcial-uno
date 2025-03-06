Aquí tienes un **README.md** detallado para tu proyecto **Spring Boot** con configuración en **Docker Compose** y **MySQL**. 🚀

---

## **README.md**

# **Spring Boot Project - Partial Management System**

## 📌 **Project Description**
This project is a **Spring Boot REST API** designed for managing categories, customers, orders, products, and order details. It uses **MySQL** as the database and provides endpoints for CRUD operations.

The application is **containerized with Docker Compose**, making it easy to deploy and manage.

---

## 📦 **Technologies Used**
- **Java 17** (Spring Boot)
- **Spring Data JPA**
- **Spring Web**
- **MySQL 8**
- **Docker & Docker Compose**
- **Adminer** (Database GUI)
- **Hibernate ORM**
- **MapStruct** (DTO Mapping)
- **Lombok**

---

## 🚀 **Project Setup**

### **1️⃣ Prerequisites**
Ensure you have installed:
- **Java 17** (or compatible JDK)
- **Docker & Docker Compose**
- **Maven**

---

## 🛠️ **How to Run the Project**
### **1️⃣ Clone the repository**
```bash
git clone https://github.com/bcaceres19/parcial-uno.git
cd primero
```

### **2️⃣ Start the Database with Docker**
Run the following command to start **MySQL** and **Adminer** using Docker Compose:
```bash
docker-compose up -d
```
This will:
- Start a **MySQL** database on port `5500`
- Start **Adminer** on port `8000` (accessible via browser)

### **3️⃣ Configure Database Connection**
Make sure the **application.properties** file has the correct database configuration:
```properties
spring.datasource.url=jdbc:mysql://localhost:5500/parcial?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=rootpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### **4️⃣ Run the Spring Boot Application**
Use Maven to build and run the project:
```bash
mvn spring-boot:run
```
Alternatively, if using an IDE:
- Open the project in **IntelliJ IDEA / VS Code / Eclipse**
- Run the **PrimeroApplication.java** file

---

## 🐳 **Docker Configuration**
The project includes a **Docker Compose** configuration to manage the database. Below is the content of `docker-compose.yml`:

```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    container_name: mysql_container
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: parcial
      MYSQL_USER: usuario
      MYSQL_PASSWORD: usuario123
    ports:
      - "5500:3306"
    volumes:
      - mysql_data:/var/lib/mysql

  adminer:
    image: adminer
    container_name: adminer_container
    restart: always
    ports:
      - "8000:8080"
    environment:
      ADMINER_DEFAULT_SERVER: mysql

volumes:
  mysql_data:
    driver: local
```

### **How to Stop Containers**
To stop and remove the database containers, run:
```bash
docker-compose down
```

---

## 🔗 **Accessing Adminer**
Adminer provides an interface to interact with the MySQL database.

1. Open **http://localhost:8000** in your browser.
2. Use the following credentials to log in:
    - **System:** MySQL
    - **Server:** mysql
    - **Username:** usuario
    - **Password:** usuario123
    - **Database:** parcial

---

## 🛠 **Endpoints & API Documentation**
### **📌 Category API (`/api/categories`)**
| Method | Endpoint            | Description             |
|--------|---------------------|-------------------------|
| `POST` | `/api/categories`   | Create a new category  |
| `PUT`  | `/api/categories`   | Update an existing category |
| `GET`  | `/api/categories/{id}` | Retrieve a category by ID |
| `DELETE` | `/api/categories/{id}` | Delete a category by ID |

### **📌 Customer API (`/api/customers`)**
| Method | Endpoint          | Description            |
|--------|-------------------|------------------------|
| `POST` | `/api/customers`  | Create a new customer |
| `PUT`  | `/api/customers`  | Update an existing customer |
| `GET`  | `/api/customers/{id}` | Retrieve a customer by ID |
| `DELETE` | `/api/customers/{id}` | Delete a customer by ID |

### **📌 Product API (`/api/products`)**
| Method | Endpoint         | Description          |
|--------|-----------------|----------------------|
| `POST` | `/api/products`  | Create a new product |
| `PUT`  | `/api/products`  | Update an existing product |
| `GET`  | `/api/products/{id}` | Retrieve a product by ID |
| `DELETE` | `/api/products/{id}` | Delete a product by ID |

### **📌 Order API (`/api/orders`)**
| Method | Endpoint       | Description        |
|--------|---------------|--------------------|
| `POST` | `/api/orders` | Create a new order |
| `PUT`  | `/api/orders` | Update an existing order |
| `GET`  | `/api/orders/{id}` | Retrieve an order by ID |
| `DELETE` | `/api/orders/{id}` | Delete an order by ID |

### **📌 Order Details API (`/api/order-details`)**
| Method | Endpoint          | Description          |
|--------|------------------|----------------------|
| `POST` | `/api/order-details` | Create a new order detail |
| `PUT`  | `/api/order-details` | Update an existing order detail |
| `GET`  | `/api/order-details/{id}` | Retrieve an order detail by ID |
| `DELETE` | `/api/order-details/{id}` | Delete an order detail by ID |

---

## 🔍 **Database Schema**
The project uses **MySQL** as the relational database. Below are the tables created:

- `category`
- `customer`
- `product`
- `order`
- `order_details`

Each table is mapped using **Spring Data JPA** with appropriate relationships.

---

## ✅ **Testing the API**
You can use **Postman** or **cURL** to test the API.

Example request to create a **category**:
```bash
curl -X POST http://localhost:4500/api/categories \
     -H "Content-Type: application/json" \
     -d '{
           "id": null,
           "name": "Electronics"
         }'
```

---

## 📜 **Logging Configuration**
Logging is configured in `application.properties`:
```properties
# Enable SQL query logs
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.springframework.web=INFO
```
This helps in debugging SQL queries and application logs.

---

## ❓ **Troubleshooting**
| Issue | Solution |
|--------|----------|
| MySQL is not starting | Run `docker-compose up -d` again |
| Cannot connect to database | Check `application.properties` for correct credentials |
| Spring Boot application does not start | Run `mvn clean package` and try again |

---

## 🎯 **Contributing**
Contributions are welcome! Feel free to **fork** the repo and submit a **pull request**.

---

## 📄 **License**
This project is licensed under the **MIT License**.

---

## 🎉 **Authors**
👤 **Brahian Alexander Caceres Guevara**  
🔗 GitHub: [bcaceres](https://github.com/bcaceres19/parcial-uno.git)

---

Let me know if you need **further refinements**! 🚀😃