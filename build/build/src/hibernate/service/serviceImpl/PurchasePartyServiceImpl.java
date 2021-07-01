package hibernate.service.serviceImpl;

import java.util.List;

import hibernate.dao.dao.PurchasePartyDao;
import hibernate.dao.daoImpl.PurchasePartyDaoImpl;
import hibernate.entities.PurchaseParty;
import hibernate.service.service.PurchasePartyService;


public class PurchasePartyServiceImpl implements PurchasePartyService {

	private PurchasePartyDao dao;
	public PurchasePartyServiceImpl() {
	this.dao = new PurchasePartyDaoImpl();
	}
	@Override
	public PurchaseParty getPurchasePartyById(int id) {
		return dao.getPurchasePartyById(id);
	}
	@Override
	public PurchaseParty getPurchasePartyByName(String name) {
		return dao.getPurchasePartyByName(name);
	}
	@Override
	public List<PurchaseParty> getAllPurchaseParty() {
		return dao.getAllPurchaseParty();
	}
	@Override
	public List<String> getAllPurchasePartyNames() {
		return dao.getAllPurchasePartyNames();
	}
	@Override
	public int savePurchaseParty(PurchaseParty party) {
		return dao.savePurchaseParty(party);
	}
}
