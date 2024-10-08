/*
 * Create Author table
 */
CREATE TABLE authors (
 	id INT PRIMARY KEY NOT NULL,
 	first_name VARCHAR(255),
 	last_name VARCHAR(255),
 	email VARCHAR(255) UNIQUE,
 	birth_date DATE,
 	role ENUM('Contributor', 'Editor')
 	
);

/*
 * Create Article table
 */

 CREATE TABLE articles (
 	id INT PRIMARY KEY NOT NULL,
 	title VARCHAR(255) UNIQUE,
 	content TEXT,
 	creation_date DATE,
 	published_date DATE,
 	article_status ENUM('Draft', 'Published'),
 	author INT,
 	picture VARCHAR(255),
 	FOREIGN KEY (author) REFERENCES authors(id)
 	
 );
 
 /*
  * Create Comment table
  */
 
 CREATE TABLE comments (
 	 	id INT PRIMARY KEY NOT NULL,
 	 	content TEXT,
 	 	creation_date DATE,
 	 	comment_status ENUM('Approved', 'Denied'),
 	 	article INT,
 	 	 	FOREIGN KEY (article) REFERENCES articles(id)
 );