package hibernate.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import hibernate.dao.dao.BankTransferDao;
import hibernate.dao.daoImpl.BankTransferDaoImpl;
import hibernate.entities.BankTransfer;
import hibernate.service.service.BankTransferService;

public class BankTransferServiceImpl implements BankTransferService {

	private BankTransferDao dao;
	public BankTransferServiceImpl()
	{
		this.dao= new BankTransferDaoImpl();
	}
	@Override
	public BankTransfer getBankTransferById(int id) {
		return dao.getBankTransferById(id);
	}

	@Override
	public List<BankTransfer> getAllBankTransfer() {
		return dao.getAllBankTransfer();
	}

	@Override
	public List<BankTransfer> getBankTransferByDate(LocalDate date) {
		return dao.getBankTransferByDate(date);
	}

	@Override
	public List<BankTransfer> getBankTransferByDatePeriod(LocalDate from, LocalDate to) {
		return dao.getBankTransferByDatePeriod(from, to);
	}

	@Override
	public List<BankTransfer> getBankTransferByBank(int bankid) {
		return dao.getBankTransferByBank(bankid);
	}

	@Override
	public int saveBankTransfer(BankTransfer transfer) {
		return dao.saveBankTransfer(transfer);
	}

}
