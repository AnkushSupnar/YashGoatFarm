package hibernate.dao.dao;

import java.time.LocalDate;
import java.util.List;

import hibernate.entities.Commision;

public interface CommisionDao {

	public List<Commision>getAllCommision();
	public List<Commision>getDateWiseCommision(LocalDate date);
	public List<Commision>getDatePeriodCommision(LocalDate fromDate,LocalDate toDate);
	
	public List<Commision>getEmployeeAllCommision(int empid);
	public List<Commision>getEmployeeDateWiseCommision(int empid,LocalDate date);
	
	public int saveCommision(Commision commision);
	
	public long getNewCommisionId();
	public Commision getCommisionById(long id);
	public void deleteTransaction(long id);
}
