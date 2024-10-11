/*
 * Create Author table
 */
CREATE TABLE users (
 	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
 	first_name VARCHAR(255),
 	last_name VARCHAR(255),
 	email VARCHAR(255) UNIQUE,
 	password VARCHAR(255),
 	birth_date DATE,
 	role ENUM('CONTRIBUTOR', 'EDITOR', 'ADMIN')
 	
);

/*
 * Create Article table
 */

 CREATE TABLE articles (
 	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
 	title VARCHAR(255) UNIQUE,
 	content TEXT,
 	creation_date DATE,
 	published_date DATE,
 	article_status ENUM('DRAFT', 'PUBLISHED'),
 	user_id INT,
 	picture VARCHAR(255),
 	FOREIGN KEY (user_id) REFERENCES users(id)
 	
 );
 
 /*
  * Create Comment table
  */
 
 CREATE TABLE comments (
 	 	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
 	 	content TEXT,
 	 	creation_date DATE,
 	 	comment_status ENUM('APPROVED', 'DENIED', 'PENDING') DEFAULT 'PENDING',
 	 	article INT,
 	 	user_id INT,
 	 	FOREIGN KEY (user_id) REFERENCES users(id),
 	 	FOREIGN KEY (article) REFERENCES articles(id)
 );