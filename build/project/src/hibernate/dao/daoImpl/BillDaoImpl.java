package hibernate.dao.daoImpl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;

import hibernate.dao.dao.BillDao;
import hibernate.entities.Bill;
import hibernate.entities.Transaction;
import hibernate.util.HibernateUtil;




public class BillDaoImpl implements BillDao {

	@Override
	public Bill getBillByBillno(long billno) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			Bill bill = session.get(Bill.class, billno);
			return bill;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Bill> getAllBills() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql = "from Bill";
			List<Bill> list = session.createQuery(hql,Bill.class).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Bill> getDateWiseBill(LocalDate date) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql = "from Bill where date=:d";
			List<Bill> list = session.createQuery(hql,Bill.class).setParameter("d",date).list();
			
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Bill> getMonthWiseBill(LocalDate date) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql = "from Bill where MONTH(date)=MONTH(:d)";
			List<Bill> list = session.createQuery(hql,Bill.class).setParameter("d",date).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<Bill> getThisWeekBill() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql = "from Bill where date between DATE_FORMAT(CURDATE(),'%Y-01-01') AND CURDATE()";
			List<Bill> list = session.createQuery(hql,Bill.class).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<Bill> getThisYearBill() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql = "from Bill where WEEKOFYEAR(date)=WEEKOFYEAR(CURDATE())";
			List<Bill> list = session.createQuery(hql,Bill.class).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Transaction> getBillTransactions(int billno) {
	try(Session session = HibernateUtil.getSessionFactory().openSession()) {
		session.beginTransaction();
		String hql="select Transaction Bill where billno=:no";
		List<Transaction>list = session.createQuery(hql,Transaction.class).setParameter("no", billno).list();
		return list;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	}

	@Override
	public int saveBill(Bill bill) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			Bill b =getBillByBillno(bill.getBillno()); 
			if(b==null)
			{
				session.save(bill);
				session.getTransaction().commit();
				return 1;
			}
			else
			{
				deleteTransactions(bill.getBillno());
				session.update(bill);
				session.getTransaction().commit();
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long getNewBNillNo() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql = "select MAX(billno) from Bill";
			if(session.createQuery(hql).uniqueResult()==null)
			{
				return 1;
			}
			long billno = (long) session.createQuery(hql).uniqueResult();
			return ++billno;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void deleteTransactions(long l) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			Bill bill = getBillByBillno(l);
			if(bill!=null)
			{
				for(Transaction tr:bill.getTransaction())
				{
					System.out.println(tr.getId());
					session.delete(tr);
				}
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	

	
	

}
