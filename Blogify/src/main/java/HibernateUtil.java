import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Load environment variables from .env file
			Dotenv dotenv = Dotenv.load();

			// Create a configuration object
			Configuration configuration = new Configuration();

			// Set the Hibernate properties dynamically from .env file
			configuration.setProperty("hibernate.connection.driver_class", dotenv.get("DB_DRIVER"));
			configuration.setProperty("hibernate.connection.url", dotenv.get("DB_URL"));
			configuration.setProperty("hibernate.connection.username", dotenv.get("DB_USERNAME"));
			configuration.setProperty("hibernate.connection.password", dotenv.get("DB_PASSWORD"));

			// Add mappings and other configurations
			configuration.configure("hibernate.cfg.xml"); // Assuming you still have your xml mappings

			// Build the SessionFactory from the configuration
			return configuration.buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}
}
