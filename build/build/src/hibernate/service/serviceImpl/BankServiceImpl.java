package hibernate.service.serviceImpl;

import java.util.List;

import hibernate.dao.dao.BankDao;
import hibernate.dao.daoImpl.BankDaoImpl;
import hibernate.entities.Bank;
import hibernate.service.service.BankService;

public class BankServiceImpl implements BankService {

	private BankDao dao;

	public BankServiceImpl() {
		this.dao = new BankDaoImpl();
	}

	@Override
	public Bank getBankById(int id) {
		return dao.getBankById(id);
	}

	@Override
	public Bank getBankByName(String name) {
		return dao.getBankByName(name);
	}

	@Override
	public List<Bank> getAllBanks() {
		return dao.getAllBanks();
	}

	@Override
	public List<String> getAllBankNames() {
		return dao.getAllBankNames();
	}

	@Override
	public double getBankBalance(int id) {
		return dao.getBankBalance(id);
	}

	@Override
	public int saveBank(Bank bank) {
		return dao.saveBank(bank);
	}

	@Override
	public void addBankBalance(int id, double amount) {
		dao.addBankBalance(id, amount);
	}

	@Override
	public void reduceBankBalance(int id, double amount) {
		dao.reduceBankBalance(id, amount);
	}

}
