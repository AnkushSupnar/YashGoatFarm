package hibernate.service.service;

import java.util.List;

import hibernate.entities.Bank;


public interface BankService {

	public Bank getBankById(int id);
	public Bank getBankByName(String name);
	public List<Bank> getAllBanks();
	public List<String>getAllBankNames();
	public double getBankBalance(int id);
	
	public int saveBank(Bank bank);
	
	public void addBankBalance(int id,double amount);
	public void reduceBankBalance(int id,double amount);
}
