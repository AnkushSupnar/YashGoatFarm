package hibernate.dao.dao;

import java.time.LocalDate;
import java.util.List;

import hibernate.entities.BankTransfer;

public interface BankTransferDao {

	BankTransfer getBankTransferById(int id);
	List<BankTransfer>getAllBankTransfer();
	List<BankTransfer>getBankTransferByDate(LocalDate date);
	List<BankTransfer>getBankTransferByDatePeriod(LocalDate from,LocalDate to);
	List<BankTransfer>getBankTransferByBank(int bankid);
	int saveBankTransfer(BankTransfer transfer);
	
	
}
