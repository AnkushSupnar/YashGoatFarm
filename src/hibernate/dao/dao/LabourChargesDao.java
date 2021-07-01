package hibernate.dao.dao;

import java.time.LocalDate;
import java.util.List;

import hibernate.entities.LabourCharges;

public interface LabourChargesDao {

	public LabourCharges getLabourChargesById(long id);
	public List<LabourCharges>getLabourChargesByDate(LocalDate date);
	public List<LabourCharges>getAllLabourCharges();
	public List<LabourCharges>getPeriodWiseLabourCharges(LocalDate start,LocalDate end);
	public int saveLabourCharges(LabourCharges labourCharges);
	
}
