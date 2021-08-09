package hibernate.dao.daoImpl;

import java.time.LocalDate;
import java.util.List;

import hibernate.dao.dao.BankTransferDao;
import hibernate.entities.BankTransfer;

public class BankTransferDaoImpl implements BankTransferDao {

	@Override
	public BankTransfer getBankTransferById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankTransfer> getAllBankTransfer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankTransfer> getBankTransferByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankTransfer> getBankTransferByDatePeriod(LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankTransfer> getBankTransferByBank(int bankid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveBankTransfer(BankTransfer transfer) {
		// TODO Auto-generated method stub
		return 0;
	}

}
