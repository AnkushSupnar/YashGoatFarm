package hibernate.util;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import hibernate.entities.*;


public class HibernateUtil {
	private static SessionFactory sessionFactory;

	// for Local instance
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				Properties setting = new Properties();
				setting.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				setting.put(Environment.URL, "jdbc:mysql://localhost:3306/yash");
				setting.put(Environment.USER, "root");
				setting.put(Environment.PASS, "2355");
				setting.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
				setting.put(Environment.SHOW_SQL, "true");
				setting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				setting.put(Environment.HBM2DDL_AUTO, "update");
				

				configuration.setProperties(setting);
				configuration.addAnnotatedClass(Login.class);
				configuration.addAnnotatedClass(Employee.class);
				configuration.addAnnotatedClass(Item.class);
				configuration.addAnnotatedClass(Customer.class);
				configuration.addAnnotatedClass(PurchaseParty.class);
				configuration.addAnnotatedClass(Bank.class);
				configuration.addAnnotatedClass(Transaction.class);
				configuration.addAnnotatedClass(Bill.class);
				configuration.addAnnotatedClass(BankTransaction.class);
				
				

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				System.out.println("java hibernate config service registry created");
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				return sessionFactory;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return sessionFactory;
	}

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			System.out.println("Session Created");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
