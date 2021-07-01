package hibernate.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import hibernate.dao.dao.SalesmanCuttingChargesDao;
import hibernate.dao.daoImpl.SalemanCuttingChargesDaoImpl;
import hibernate.entities.SalesmanCuttingCharges;
import hibernate.service.service.SalesmanCuttingChargesService;

public class SalesmanCuttingChargesServiceImpl implements SalesmanCuttingChargesService {

	private SalesmanCuttingChargesDao dao;
	public SalesmanCuttingChargesServiceImpl() {
		dao = new SalemanCuttingChargesDaoImpl(); 
	}
	@Override
	public SalesmanCuttingCharges getSalesmanCuttingChargesById(long id) {
		return dao.getSalesmanCuttingChargesById(id);
	}

	@Override
	public List<SalesmanCuttingCharges> getSalesmanCuttingChargesBySalesman(String salesman) {
		return dao.getSalesmanCuttingChargesBySalesman(salesman);
	}

	@Override
	public List<SalesmanCuttingCharges> getAllSalesmanCuttingCharges() {
		return dao.getAllSalesmanCuttingCharges();
	}

	@Override
	public List<SalesmanCuttingCharges> getPeriodSalesmanCuttingCharges(LocalDate satrt, LocalDate end) {
		return dao.getPeriodSalesmanCuttingCharges(satrt, end);
	}

	@Override
	public int saveSalesmanCuttingCharges(SalesmanCuttingCharges salemanCuttingCharges) {
		return dao.saveSalesmanCuttingCharges(salemanCuttingCharges);
	}

	@Override
	public void deleteSalesmanCuttingChargesTransaction(long id) {
		dao.deleteSalesmanCuttingChargesTransaction(id);

	}

	@Override
	public List<SalesmanCuttingCharges> getDateWiseCharges(LocalDate date) {
		return dao.getDateWiseCharges(date);
	}
	@Override
	public int checkPaidCuttingCharges(long orderId) {
		return dao.checkPaidCuttingCharges(orderId);
	}

}
