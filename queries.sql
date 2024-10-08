/*
 * Create Author table
 */
CREATE TABLE authors (
 	id INT PRIMARY KEY NOT NULL,
 	firstName VARCHAR(255),
 	lastName VARCHAR(255),
 	email VARCHAR(255) UNIQUE,
 	birthDate DATE,
 	role ENUM(Contributor, Editor)
 	
);

/*
 * Create Article table
 */

 CREATE TABLE articles (
 	id INT PRIMARY KEY NOT NULL,
 	title VARCHAR(255) UNIQUE,
 	content TEXT,
 	creationDate DATE,
 	publishedDate DATE,
 	articleStatus ENUM(Draft, Published),
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
 	 	creationDate DATE,
 	 	commentStatus ENUM(Approved, Denied),
 	 	article INT,
 	 	 	FOREIGN KEY (article) REFERENCES articles(id)
 );