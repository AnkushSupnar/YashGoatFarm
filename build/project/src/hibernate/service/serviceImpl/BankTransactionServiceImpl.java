package hibernate.service.serviceImpl;

import java.util.List;

import hibernate.dao.dao.BankTransactionDao;
import hibernate.dao.daoImpl.BankTransactionDaoImpl;
import hibernate.entities.BankTransaction;
import hibernate.service.service.BankTransactionService;


public class BankTransactionServiceImpl implements BankTransactionService {

	private BankTransactionDao dao;
	public BankTransactionServiceImpl() {
		this.dao = new BankTransactionDaoImpl();
	}
	@Override
	public BankTransaction getBankTransactionById(long id) {
		return dao.getBankTransactionById(id);
	}

	@Override
	public List<BankTransaction> getAllBankTransaction() {
		return dao.getAllBankTransaction();
	}

	@Override
	public List<BankTransaction> getBankTransaction(int bankid) {
		return dao.getBankTransaction(bankid);
	}

	@Override
	public List<BankTransaction> getAllDebitTransaction() {
		return dao.getAllDebitTransaction();
	}

	@Override
	public List<BankTransaction> getAllCreditTransaction() {
		return dao.getAllCreditTransaction();
	}

	@Override
	public List<BankTransaction> getAllDebitTransaction(int bankid) {
		return dao.getAllDebitTransaction(bankid);
	}

	@Override
	public List<BankTransaction> getAllCreditTransaction(int bankid) {
		return dao.getAllDebitTransaction(bankid);
	}

	@Override
	public int saveBankTransaction(BankTransaction btr) {
		return dao.saveBankTransaction(btr);
	}
	@Override
	public void deleteBankTransaction(String particulars) {
		BankTransaction tr= dao.getBankTransactionByPartucular(particulars);
		dao.deleteBankTransaction(tr.getId());
	}

}
