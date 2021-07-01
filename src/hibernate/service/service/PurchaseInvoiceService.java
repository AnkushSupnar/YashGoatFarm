package hibernate.service.service;

import java.time.LocalDate;
import java.util.List;

import hibernate.dao.dao.PurchasInvoiceDao;
import hibernate.entities.PurchaseInvoice;

public interface PurchaseInvoiceService extends PurchasInvoiceDao{
	public PurchaseInvoice getPurchaseInvoice(long billno);
	public List<PurchaseInvoice>getAllPurchaseInvoice();
	public List<PurchaseInvoice>getDateWisePurchaseInvoice(LocalDate date);
	public List<PurchaseInvoice>getMonthWisePurchaseInvoice(LocalDate date);
	public List<PurchaseInvoice>getThisYearPurchaseInvoice();
	public List<PurchaseInvoice>getThisWeekInvoice();
	public List<PurchaseInvoice>getPartyWiseUnpaidPurchaseInvoice(int partyId);
	public int savePurchaseInvoice(PurchaseInvoice purchaseInvoice);
	public long getNewInvoiceNo();
	public void deleteTransaction(long billno);
}
