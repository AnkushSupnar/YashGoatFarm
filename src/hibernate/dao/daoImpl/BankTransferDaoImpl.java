package hibernate.dao.daoImpl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;

import hibernate.dao.dao.BankTransferDao;
import hibernate.entities.BankTransfer;
import hibernate.util.HibernateUtil;

public class BankTransferDaoImpl implements BankTransferDao {

	@Override
	public BankTransfer getBankTransferById(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			return session.get(BankTransfer.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BankTransfer> getAllBankTransfer() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql="from BankTransfer";
			return session.createQuery(hql,BankTransfer.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BankTransfer> getBankTransferByDate(LocalDate date) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql="from BankTransfer where date=:date";
			return session.createQuery(hql,BankTransfer.class).setParameter("date", date).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BankTransfer> getBankTransferByDatePeriod(LocalDate from, LocalDate to) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql="from BankTransfer where date between :fromDate and :toDate";
			return session.createQuery(hql,BankTransfer.class).setParameter("fromDate", from).setParameter("toDate", to).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BankTransfer> getBankTransferByBank(int bankid) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql="from BankTransaction where frombankid:bankid and tobankid=:tobankid";
			return session.createQuery(hql,BankTransfer.class).setParameter("bankid", bankid).setParameter("tobankid", bankid).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int saveBankTransfer(BankTransfer transfer) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			if(transfer.getId()==0)
			{
				session.save(transfer);
				session.getTransaction().commit();
				return 1;
			}
			else
			{
				session.update(transfer);
				session.getTransaction().commit();
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
