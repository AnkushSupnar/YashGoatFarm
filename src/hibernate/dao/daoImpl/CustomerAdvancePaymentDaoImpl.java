package hibernate.dao.daoImpl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;

import hibernate.dao.dao.CustomerAdvancePaymentDao;
import hibernate.entities.CustomerAdvancePayment;
import hibernate.util.HibernateUtil;

public class CustomerAdvancePaymentDaoImpl implements CustomerAdvancePaymentDao {

	@Override
	public CustomerAdvancePayment getCustomerAdvanceById(long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			return session.get(CustomerAdvancePayment.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CustomerAdvancePayment> getAllCustomerAdvance() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "from CustomerAdvancePayment";
			return session.createQuery(hql, CustomerAdvancePayment.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CustomerAdvancePayment> getCustomerAdvanceByCustomer(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "from CustomerAdvancePayment where customerid=:id";
			return session.createQuery(hql, CustomerAdvancePayment.class).setParameter("id", id).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CustomerAdvancePayment> getCustomerAdvanceByDate(LocalDate date) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "from CustomerAdvancePayment where date=:date";
			return session.createQuery(hql, CustomerAdvancePayment.class).setParameter("date", date).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CustomerAdvancePayment> getCustomerAdvanceByDatePeriod(LocalDate start, LocalDate end) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "from CustomerAdvancePayment where date between :start and :end";
			return session.createQuery(hql, CustomerAdvancePayment.class).setParameter("start", start)
					.setParameter("end", end).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CustomerAdvancePayment> getCustomerAdvanceByDatePeriodAndCustomer(LocalDate start, LocalDate end,
			int customerid) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "from CustomerAdvancePayment where customerid=:cid and date between ;start and :end";
			return session.createQuery(hql, CustomerAdvancePayment.class).setParameter("cid", customerid)
					.setParameter("start", start).setParameter("end", end).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int saveCustomerAdvance(CustomerAdvancePayment payment) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			if(payment.getId()==0)
			{
				session.save(payment);
				session.getTransaction().commit();
				return 1;
			}
			else
			{
				session.update(payment);
				session.getTransaction().commit();
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public double getCustomerTotalAdvance(int customerId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql="select sum(amount) from CustomerAdvancePayment where customerid=:cid";
			return session.createQuery(hql,Double.class).setParameter("cid", customerId).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
