📌 To-Do Management System

A simple and efficient To-Do Management Web Application built using Spring Boot.
This project helps users manage daily tasks by adding, updating, viewing, and deleting tasks easily.

🚀 Features
➕ Add new tasks
✏️ Update existing tasks
❌ Delete tasks
📋 View all tasks
✔️ Mark tasks as completed (if implemented)
🗄️ In-memory database support (H2) / JPA integration

🛠️ Tech Stack
Backend: Java 17, Spring Boot
Frameworks: Spring MVC, Spring Data JPA
Database: H2 / MySQL (based on configuration)
Build Tool: Maven
Server: Embedded Tomcat

📁 Project Structure
src/
 ├── main/
 │    ├── java/org/todo/todorails
 │    │      ├── controller
 │    │      ├── service
 │    │      ├── repository
 │    │      ├── model
 │    │      └── config
 │    └── resources/
 │           ├── templates/
 │           ├── static/
 │           └── application.properties
 └── test/
▶️ How to Run the Project

1️⃣ Clone the repository
git clone https://github.com/Suhasinisanjeevkumar/To-Do-management-system.git

2️⃣ Move into project folder
cd To-Do-management-system

3️⃣ Run using Maven Wrapper
./mvnw spring-boot:run

For Windows PowerShell:
mvnw spring-boot:run


🌐 Open in Browser
Once the application starts, open:
http://localhost:8080/

🧪 API Endpoints (if REST is used)
Method	Endpoint	Description
GET	/tasks	Get all tasks
POST	/tasks	Add new task
PUT	/tasks/{id}	Update task
DELETE	/tasks/{id}	Delete task



👨‍💻 Author
Suhasini Sanjeev Kumar
GitHub: To-Do Management System


Priority levels for tasks
UI improvement using React or Thymeleaf
Cloud deployment
