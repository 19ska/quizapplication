Quiz App Backend
---------------------
Overview

This is the backend for the Quiz App, built using Spring Boot and MySQL. The backend allows an admin to manage quizzes and questions through a REST API.

Features:
-Admin authentication (login and registration)
-Create, Read, Update, Delete (CRUD) operations for quizzes
-CRUD operations for questions within a quiz
-REST API with JSON responses
-MySQL database integration with Hibernate/JPA

Technologies Used:
-Java 17+
-Spring Boot 3.4.3
-Spring Data JPA
-MySQL
-Hibernate
-Maven

Setup Instructions
----------------------------------
1. Clone the Repository
2. Make sure you have MySQL installed and running. Then, create a database:
   CREATE DATABASE quizdb;
   Update src/main/resources/application.properties with your database credentials
3. Build and run the application

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
