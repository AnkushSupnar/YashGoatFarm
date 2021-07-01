package hibernate.dao.dao;

import java.time.LocalDate;
import java.util.List;

import hibernate.entities.SalesmanCuttingCharges;

public interface SalesmanCuttingChargesDao {
	public SalesmanCuttingCharges getSalesmanCuttingChargesById(long id);
	public List<SalesmanCuttingCharges> getSalesmanCuttingChargesBySalesman(String salesman);
	public List<SalesmanCuttingCharges>getAllSalesmanCuttingCharges();
	public List<SalesmanCuttingCharges>getPeriodSalesmanCuttingCharges(LocalDate satrt,LocalDate end);
	public int saveSalesmanCuttingCharges(SalesmanCuttingCharges salemanCuttingCharges);
	public void deleteSalesmanCuttingChargesTransaction(long id);
	public List<SalesmanCuttingCharges>getDateWiseCharges(LocalDate date);
	public int checkPaidCuttingCharges(long orderId);
}
