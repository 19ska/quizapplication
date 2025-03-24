Quiz App Backend
---------------------
🎯 Quiz App Backend (Spring Boot + MySQL)

📌 Overview

This is the backend for the Quiz App, built using Spring Boot and MySQL. It provides admin functionalities to manage quizzes and questions via a secure REST API.

🚀 Features
---------------------------------
✅ Admin Authentication (Login & Registration)
✅ Create, Read, Update, Delete (CRUD) operations for quizzes
✅ CRUD operations for questions within a quiz
✅ MySQL database integration with Hibernate/JPA
✅ REST API with JSON responses
✅ Automated testing with JUnit5 & Mockito
✅ CI/CD with CircleCI
✅ Deployment on AWS EC2


Technologies Used:
---------------------------------
-Java 17+
-Spring Boot 3.4.3
-Spring Data JPA
-MySQL
-Hibernate
-Maven
-Junit5 
-Mockito 
-MockMvc 
-circleCI 
-AWS 
-EC2 
 

📌 Setup Instructions

1️⃣ Clone the Repository

https://github.com/19ska/quizapplication.git
cd quiz-api

2️⃣ Configure MySQL Database

Ensure MySQL is installed and running. Then, create a database:
CREATE DATABASE quizdb;

Update src/main/resources/application.properties with your MySQL credentials:
spring.datasource.url=jdbc:mysql://localhost:3306/quizdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

3️⃣ Build & Run the Application

mvn clean install
mvn spring-boot:run

The app will start on http://localhost:8080 🚀

API Endpoints
---------------------------------
Admin Authentication:
Register Admin: POST /api/admin/register
Login Admin: POST /api/admin/login

Quiz Management:
Create Quiz: POST /api/quiz

Question Management:
Add Question to Quiz: POST /api/quiz/{quizId}/add-question
Get All Questions for a Quiz: GET /api/quiz/{quizId}/questions
Update Question: PUT /api/question/{questionId}
Delete Question: DELETE /api/question/{questionId}

🔄 CI/CD with CircleCI
---------------------------------
This project includes Continuous Integration (CI) via CircleCI to automate builds and tests.

CircleCI Configuration

Located in .circleci/config.yml


🛡️ Testing
---------------------------------
	•	Unit Tests with JUnit5 & Mockito
	•	API Tests using MockMvc
	•	Run tests:
       mvn test
