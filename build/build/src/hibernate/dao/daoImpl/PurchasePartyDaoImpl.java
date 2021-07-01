package hibernate.dao.daoImpl;

import java.util.List;

import org.hibernate.Session;

import hibernate.dao.dao.PurchasePartyDao;
import hibernate.entities.PurchaseParty;
import hibernate.util.HibernateUtil;
public class PurchasePartyDaoImpl implements PurchasePartyDao {

	@Override
	public PurchaseParty getPurchasePartyById(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			PurchaseParty party = session.get(PurchaseParty.class, id);
			return party;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PurchaseParty getPurchasePartyByName(String name) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "from PurchaseParty where name=:n";
			PurchaseParty party = session.createQuery(hql,PurchaseParty.class).setParameter("n",name).uniqueResult();
			return party;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<PurchaseParty> getAllPurchaseParty() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql="from PurchaseParty";
			List<PurchaseParty> list = session.createQuery(hql,PurchaseParty.class).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> getAllPurchasePartyNames() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select name from Purchaseparty";
			@SuppressWarnings("unchecked")
			List<String> list = session.createQuery(hql).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int savePurchaseParty(PurchaseParty party) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			if(party.getId()==0)
			{
				session.save(party);
				session.getTransaction().commit();
				return 1;
			}
			else
			{
				session.update(party);
				session.getTransaction().commit();
				return 2;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
