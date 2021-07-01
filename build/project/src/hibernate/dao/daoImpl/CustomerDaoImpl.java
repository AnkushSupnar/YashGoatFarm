package hibernate.dao.daoImpl;

import java.util.List;

import org.hibernate.Session;

import hibernate.dao.dao.CustomerDao;
import hibernate.entities.Customer;
import hibernate.util.HibernateUtil;


public class CustomerDaoImpl implements CustomerDao {

	@Override
	public Customer getCustomerbyId(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Customer customer = session.get(Customer.class, id);
			return customer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Customer getCustomerByName(String fname, String mname, String lname) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "from Customer where fname=:f and mname=:m and lname=:l";
			Customer customer = session.createQuery(hql, Customer.class).setParameter("f", fname)
					.setParameter("m", mname).setParameter("l", lname).getSingleResult();
			return customer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Customer> getAllCustomer() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "from Customer";
			List<Customer> list = session.createQuery(hql, Customer.class).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<String> getAllCustomerNames() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select concat(fname,' ',mname,' ',lname) from Customer";
			@SuppressWarnings("unchecked")
			List<String> list = session.createNativeQuery(hql).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int saveCustomer(Customer customer) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			if(customer.getId()==0)
			{
				session.save(customer);
				session.getTransaction().commit();
				return 1;
			}
			else
			{
				session.update(customer);
				session.getTransaction().commit();
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
}
