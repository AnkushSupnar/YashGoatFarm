package hibernate.dao.daoImpl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;

import hibernate.dao.dao.CounterStockDataDao;
import hibernate.entities.CounterStockData;
import hibernate.util.HibernateUtil;

public class CounterStockDataDaoImpl implements CounterStockDataDao {

	@Override
	public float getCounterItemStock(String itemname) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql = "select qty from CounterStockData where itemname=:itemname";
			try {
			return session.createQuery(hql,Float.class).setParameter("itemname", itemname).getSingleResult();
			}catch(NoResultException nor)
			{
				return 0;
			}
		} catch (Exception e ) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<CounterStockData> getAllCounterStockData() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql = "from CounterStockData";
			return session.createQuery(hql,CounterStockData.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int saveCounterStockdata(CounterStockData counterStockData) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			CounterStockData data =getItemNameWiseCounterStockData(counterStockData.getItemname());
			if(data==null)
			{
				session.save(counterStockData);
				session.getTransaction().commit();
				return 1;
			}
			else
			{
				data.setQty(data.getQty()+counterStockData.getQty());
				session.update(data);
				session.getTransaction().commit();
				return 2;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateQuantity(String itemname, float newqty) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			CounterStockData data= getItemNameWiseCounterStockData(itemname);
			if(data!=null)
			{
				data.setQty(data.getQty()+newqty);
				session.update(data);
				session.getTransaction().commit();
				return 1;
			}
			else
			{
				CounterStockData newData = new CounterStockData(
						itemname,
						newqty,
						new ItemDaoImpl().getItemByName(itemname).getUnit());
						newData.setId(0);
				return saveCounterStockdata(newData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public CounterStockData getItemNameWiseCounterStockData(String itemname) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql="from CounterStockData where itemname=:itemname";
			try {
			return session.createQuery(hql,CounterStockData.class).setParameter("itemname", itemname).getSingleResult();
			}catch(NoResultException nor)
			{
				return null;
			}
		} catch (Exception e ) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> getAllCounterItemNames() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql="select itemname from CounterStockData";
			return session.createQuery(hql,String.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
