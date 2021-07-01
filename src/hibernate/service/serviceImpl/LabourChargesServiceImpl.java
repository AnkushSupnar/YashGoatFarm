package hibernate.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import hibernate.dao.dao.LabourChargesDao;
import hibernate.dao.daoImpl.LabourChargesDaoImpl;
import hibernate.entities.LabourCharges;
import hibernate.service.service.LabourChargesService;

public class LabourChargesServiceImpl implements LabourChargesService {
	private LabourChargesDao dao;
	public LabourChargesServiceImpl() {
	this.dao = new LabourChargesDaoImpl();
}
	@Override
	public LabourCharges getLabourChargesById(long id) {
		return dao.getLabourChargesById(id);
	}

	@Override
	public List<LabourCharges> getLabourChargesByDate(LocalDate date) {
		return dao.getLabourChargesByDate(date);
	}

	@Override
	public List<LabourCharges> getAllLabourCharges() {
		return dao.getAllLabourCharges();
	}

	@Override
	public List<LabourCharges> getPeriodWiseLabourCharges(LocalDate start, LocalDate end) {
		return  dao.getPeriodWiseLabourCharges(start, end);
	}

	@Override
	public int saveLabourCharges(LabourCharges labourCharges) {
		return dao.saveLabourCharges(labourCharges);
	}

}
