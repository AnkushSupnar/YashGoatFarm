package hibernate.service.serviceImpl;

import java.time.LocalDate;

import hibernate.dao.dao.CompanyDao;
import hibernate.dao.daoImpl.CompanyDaoImpl;
import hibernate.entities.CompanyDetails;
import hibernate.service.service.CompanyService;

public class CompanyServiceImpl implements CompanyService {

	private CompanyDao dao;
	public CompanyServiceImpl() {
		dao = new CompanyDaoImpl();
	}
	@Override
	public CompanyDetails getCompanyDetails(int id) {
		return dao.getCompanyDetails(id);
	}

	@Override
	public int saveCompany(CompanyDetails company) {
		return dao.saveCompany(company);
	}

	@Override
	public int checkLicense(LocalDate date) {
		return dao.checkLicense(date);		
	}
	@Override
	public CompanyDetails getCompanyByName(String name) {
		return dao.getCompanyByName(name);
	}

}
