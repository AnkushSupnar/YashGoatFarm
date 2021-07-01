package hibernate.dao.daoImpl;

import java.util.List;

import org.hibernate.Session;

import hibernate.dao.dao.ItemDao;
import hibernate.entities.Item;
import hibernate.util.HibernateUtil;
public class ItemDaoImpl implements ItemDao {

	@Override
	public Item getItemById(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession() ){
			session.beginTransaction();
			Item item = session.get(Item.class, id);
			return item;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Item getItemByName(String name) {
		try (Session session = HibernateUtil.getSessionFactory().openSession() ){
			session.beginTransaction();
			String hql="from Item where itemname=:name";
			Item item = session.createQuery(hql,Item.class).setParameter("name", name).getSingleResult();
			return item;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Item> getAllItems() {
		try (Session session = HibernateUtil.getSessionFactory().openSession() ){
			session.beginTransaction();
			String hql = "from Item";
			List<Item>list = session.createQuery(hql,Item.class).list();
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> getAllItemNames() {
		try (Session session = HibernateUtil.getSessionFactory().openSession() ){
			session.beginTransaction();
			String hql = "select itemname from Item";
			@SuppressWarnings("unchecked")
			List<String> list = session.createNativeQuery(hql).list();
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int saveItem(Item item) {
		try (Session session = HibernateUtil.getSessionFactory().openSession() ){
			session.beginTransaction();
			if(item.getId()==0)
			{
				session.save(item);
				session.getTransaction().commit();
				return 1;
			}
			else
			{
				Item i = getItemById(item.getId());
				i.setItemname(item.getItemname());
				i.setRate(item.getRate());
				i.setUnit(item.getUnit());
				i.setCommision(item.getCommision());
				session.update(i);
				session.getTransaction().commit();
				return 2;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}

}
