package hibernate.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import hibernate.dao.dao.CommisionDao;
import hibernate.dao.daoImpl.CommiosionDaoImpl;
import hibernate.entities.Commision;
import hibernate.service.service.CommisionService;

public class CommisionServiceImpl implements CommisionService {

	private CommisionDao dao;
	public CommisionServiceImpl() {
	this.dao = new CommiosionDaoImpl();
	}
	@Override
	public List<Commision> getAllCommision() {
		return dao.getAllCommision();
	}
	@Override
	public List<Commision> getDateWiseCommision(LocalDate date) {
		return dao.getDateWiseCommision(date);
	}
	@Override
	public List<Commision> getDatePeriodCommision(LocalDate fromDate, LocalDate toDate) {
		// TODO Auto-generated method stub
		return dao.getDatePeriodCommision(fromDate, toDate);
	}
	@Override
	public List<Commision> getEmployeeAllCommision(int empid) {
		return dao.getEmployeeAllCommision(empid);
	}
	@Override
	public List<Commision> getEmployeeDateWiseCommision(int empid, LocalDate date) {
		return dao.getEmployeeDateWiseCommision(empid, date);
	}
	@Override
	public int saveCommision(Commision commision) {
		return dao.saveCommision(commision);
	}
	@Override
	public long getNewCommisionId() {
		return dao.getNewCommisionId();
	}
	@Override
	public Commision getCommisionById(long id) {
		return dao.getCommisionById(id);
	}
	@Override
	public void deleteTransaction(long id) {
		dao.deleteTransaction(id);
		
	}
	
}
