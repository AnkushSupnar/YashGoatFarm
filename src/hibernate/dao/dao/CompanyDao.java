package hibernate.dao.dao;

import java.time.LocalDate;

import hibernate.entities.CompanyDetails;

public interface CompanyDao {

	public CompanyDetails getCompanyDetails(int id);
	public int saveCompany(CompanyDetails company);
	public int checkLicense(LocalDate date);
	public CompanyDetails getCompanyByName(String name);
}
