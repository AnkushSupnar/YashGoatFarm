package hibernate.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import hibernate.dao.daoImpl.PurchaseInvoiceDaoImpl;
import hibernate.entities.PurchaseInvoice;
import hibernate.service.service.PurchaseInvoiceService;

public class PurchaseInvoiceServiceImpl implements PurchaseInvoiceService {

	private PurchaseInvoiceDaoImpl dao;
	public PurchaseInvoiceServiceImpl()
	{
		this.dao = new PurchaseInvoiceDaoImpl();
	}
	@Override
	public PurchaseInvoice getPurchaseInvoice(long billno) {
		return dao.getPurchaseInvoice(billno);
	}

	@Override
	public List<PurchaseInvoice> getAllPurchaseInvoice() {
		return dao.getAllPurchaseInvoice();
	}

	@Override
	public List<PurchaseInvoice> getDateWisePurchaseInvoice(LocalDate date) {
		return dao.getDateWisePurchaseInvoice(date);
	}

	@Override
	public List<PurchaseInvoice> getMonthWisePurchaseInvoice(LocalDate date) {
		return dao.getMonthWisePurchaseInvoice(date);
	}

	@Override
	public List<PurchaseInvoice> getThisYearPurchaseInvoice() {
		return dao.getThisYearPurchaseInvoice();
	}

	@Override
	public List<PurchaseInvoice> getThisWeekInvoice() {
		return dao.getThisWeekInvoice();
	}

	@Override
	public int savePurchaseInvoice(PurchaseInvoice purchaseInvoice) {
		return dao.savePurchaseInvoice(purchaseInvoice);
	}

	@Override
	public long getNewInvoiceNo() {
		return dao.getNewInvoiceNo();
	}

	@Override
	public void deleteTransaction(long billno) {
		dao.deleteTransaction(billno);	
	}
	@Override
	public List<PurchaseInvoice> getPartyWiseUnpaidPurchaseInvoice(int partyId) {
		return dao.getPartyWiseUnpaidPurchaseInvoice(partyId);
	}
	@Override
	public int updatePaidAmount(long billno, float amount) {
		return dao.updatePaidAmount(billno, amount);
	}
	@Override
	public List<PurchaseInvoice> getPurchaseInvoicePartyWise(LocalDate date, int partyid) {
		return dao.getPurchaseInvoicePartyWise(date, partyid);
	}

}
