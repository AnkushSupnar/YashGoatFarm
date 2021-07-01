package hibernate.service.serviceImpl;

import java.util.List;

import hibernate.dao.dao.LoginDao;
import hibernate.dao.daoImpl.LoginDaoImpl;
import hibernate.entities.Login;
import hibernate.service.service.LoginService;

public class LoginServiceImpl implements LoginService {

	LoginDao dao;
	public LoginServiceImpl() {
		this.dao = new LoginDaoImpl();
	}
	@Override
	public Login getLoginById(int id) {
		return dao.getLoginById(id);
	}

	@Override
	public Login getLoginByName(String name) {
		return dao.getLoginByName(name);
	}

	@Override
	public List<Login> getAllLogin() {
		return dao.getAllLogin();
	}

	@Override
	public List<String> getAllUserNames() {
		return dao.getAllUserNames();
	}

	@Override
	public int saveLogin(Login login) {
		return dao.saveLogin(login);
	}

	@Override
	public int validateLogin(String username, String Password) {
		return dao.validateLogin(username, Password);
	}

	@Override
	public int changeLoginStatus(String Status, int id) {
		return dao.changeLoginStatus(Status, id);
	}

}
