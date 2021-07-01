package hibernate.service.service;

import java.util.List;

import hibernate.entities.Login;

public interface LoginService {
	public Login getLoginById(int id);
	public Login getLoginByName(String name);
	public List<Login> getAllLogin();
	public List<String> getAllUserNames();
	
	public int saveLogin(Login login);
	public int validateLogin(String username,String Password);
	public int changeLoginStatus(String Status,int id);

}
