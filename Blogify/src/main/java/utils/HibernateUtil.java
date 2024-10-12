package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {
	private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Load environment variables from .env file
//			Dotenv dotenv = Dotenv.configure()
//					.directory("/home/exshy/Documents/GitHub/Blogify/Blogify/.env")
//					.load();
//
//			String driver = dotenv.get("DB_DRIVER");
//			String url = dotenv.get("DB_URL");
//			String username = dotenv.get("DB_USERNAME");
//			String password = dotenv.get("DB_PASSWORD");

			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/blogify";
			String username = "root";
			String password = "";

			// Validate database connection properties
			if (driver == null || url == null || username == null || password == null) {
				throw new IllegalArgumentException("Database connection properties must not be null");
			}

			// Create a configuration object
			Configuration configuration = new Configuration();

			// Set Hibernate properties dynamically from .env file
			configuration.setProperty("hibernate.connection.driver_class", driver);
			configuration.setProperty("hibernate.connection.url", url);
			configuration.setProperty("hibernate.connection.username", username);
			configuration.setProperty("hibernate.connection.password", password);

			// Add mappings and other configurations
			configuration.configure("hibernate.cfg.xml"); // Assuming you still have your xml mappings

			// Build the SessionFactory from the configuration
			return configuration.buildSessionFactory();

		} catch (Throwable ex) {
			logger.error("Initial SessionFactory creation failed: ", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
		logger.info("SessionFactory closed successfully.");
	}
}
