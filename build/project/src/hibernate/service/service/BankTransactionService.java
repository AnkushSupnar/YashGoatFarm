package hibernate.service.service;

import java.util.List;

import hibernate.entities.BankTransaction;

public interface BankTransactionService {
public BankTransaction getBankTransactionById(long id);
	
	public List<BankTransaction> getAllBankTransaction();
	public List<BankTransaction> getBankTransaction(int bankid);
	public List<BankTransaction>getAllDebitTransaction();
	public List<BankTransaction>getAllCreditTransaction();
	public List<BankTransaction>getAllDebitTransaction(int bankid);
	public List<BankTransaction>getAllCreditTransaction(int bankid);
	
	public int saveBankTransaction(BankTransaction btr);
	
	public void deleteBankTransaction(String particulars);

}
