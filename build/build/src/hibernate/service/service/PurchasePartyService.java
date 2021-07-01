package hibernate.service.service;

import java.util.List;

import hibernate.entities.PurchaseParty;

public interface PurchasePartyService {

	public PurchaseParty getPurchasePartyById(int id);
	public PurchaseParty getPurchasePartyByName(String name);
	public List<PurchaseParty> getAllPurchaseParty();
	public List<String> getAllPurchasePartyNames();
	
	public int savePurchaseParty(PurchaseParty party);
}
