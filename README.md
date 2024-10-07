
# Blogify - Web Application for Managing Blog Articles

## Project Description
Blogify is a web application designed to manage blog articles and their associated comments, providing an intuitive and interactive interface. Developed in Java, it uses a relational database to store and manage content.

## General Objective of the Application
The main objective of the application is to provide a more dynamic web solution for content management, integrating advanced features for managing articles, comments, and authors.

## Technologies Used
- **Language**: Java 8
- **Web**: Servlets, JSP, JSTL
- **ORM**: Hibernate and JPA
- **Application Server**: Apache Tomcat
- **Project Management**: Maven for dependencies, JIRA for project management, Git for version control
- **Testing**: JUnit and Mockito
- **Design**: CSS framework, mockups created with FIGMA
- **Architecture**: MVC (Model-View-Controller)

## Project Structure
```
/src
    /main
        /java
            /com
                /example
                    /blog
                        /controller
                        /model
                        /repository
                        /service
                        /util
        /resources
        /webapp
            /WEB-INF
                web.xml
            /views
                article.jsp
                comment.jsp
                author.jsp
```

## Brief Description of the Adopted Architecture
The application follows a layered MVC architecture, separating presentation logic, business logic, and data access. Servlets act as controllers, while JSPs serve as views, and model classes manage the application's data and logic.

## Installation and Usage Instructions

### Prerequisites
- Java 8
- Maven
- Apache Tomcat
- MySQL

### Steps to Set Up the Database
1. Import the SQL script provided in the `/sql` folder to create the database.
2. Adjust the connection parameters in the `hibernate.cfg.xml` file.

### How to Run the Application on Tomcat
1. Build the WAR file with Maven:
   ```bash
   mvn clean package
   ```
2. Deploy the WAR file in the `webapps` folder of Tomcat.
3. Start the Tomcat server.
4. Access the application via `http://localhost:8080/name_of_the_application`.

## Screenshots
*(Add screenshots of the application here to showcase the user interface)*

## Possible Future Improvements
- Adding advanced search features.
- Integrating a notification system for comments.
- Implementing a dashboard for engagement statistics.

## Ideas to Extend or Improve the Project
- Implementing user management functionality with roles and permissions.
- Developing a REST API for the application to facilitate integration with other services.

## Team and Contact
**Members**: M.Bachiri, M.Hachami, A.Benfill 
**Email**: blogify@youcode.com

## Performance Criteria
- The application must implement all specified features for the three main pages.
- The code must demonstrate proper use of Java 8 concepts, including Streams, Lambdas, and the Time API.
- The layered MVC architecture must be correctly implemented and respected.
- The MySQL database must be well designed and efficiently used via JDBC.
- The user interface must be intuitive, responsive, and comply with FIGMA mockups.
- The code must be clean, well-commented, and follow Java naming conventions.
- The Repository and Singleton design patterns must be correctly implemented.
- The application must demonstrate effective management of pagination and searches.
- UML diagrams (use case and class) must be complete and consistent with the implementation.
- The application must integrate an appropriate logging system.
- The WAR file must be deployable without using an IDE.
- Project management with JIRA and Git must reflect effective use of the Scrum method.
- The application must demonstrate correct use of Hibernate and JPA for data persistence.
- Unit tests must be written with JUnit and Mockito, following the TDD approach.
- Git-flow must be correctly applied throughout the project.
