package hibernate.dao.daoImpl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;

import hibernate.dao.dao.LabourChargesDao;
import hibernate.entities.LabourCharges;
import hibernate.util.HibernateUtil;

public class LabourChargesDaoImpl implements LabourChargesDao{

	@Override
	public LabourCharges getLabourChargesById(long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			LabourCharges lc = session.get(LabourCharges.class, id);
			return lc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<LabourCharges> getLabourChargesByDate(LocalDate date) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql="from LabourCHarges where date=:d";
			List<LabourCharges> lc = session.createQuery(hql,LabourCharges.class).
					setParameter("d",date).list();
			return lc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<LabourCharges> getAllLabourCharges() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql="from LabourCharges";
			List<LabourCharges> lc = session.createQuery(hql,LabourCharges.class).list();
			return lc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<LabourCharges> getPeriodWiseLabourCharges(LocalDate start, LocalDate end) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql = "from LabourCharges where date>=:d1 and date<=:d2";
			List<LabourCharges> lc = session.createQuery(hql,LabourCharges.class).
					setParameter("d1", start).setParameter("d2", end).list();
			return lc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int saveLabourCharges(LabourCharges labourCharges) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			if(labourCharges.getId()==0)
			{
				session.save(labourCharges);
				session.getTransaction().commit();
				return 1;
			}
			else
			{
				session.update(labourCharges);
				session.getTransaction().commit();
				return 2;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
}
