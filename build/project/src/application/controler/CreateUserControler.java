package application.controler;

import java.net.URL;
import java.util.ResourceBundle;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import hibernate.entities.Login;
import hibernate.service.service.EmployeeService;
import hibernate.service.service.LoginService;
import hibernate.service.serviceImpl.EmployeeServiceImpl;
import hibernate.service.serviceImpl.LoginServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;

public class CreateUserControler implements Initializable {

	@FXML
	private AnchorPane mainPane;
	@FXML
	private ComboBox<String> cmbEmployee;
	@FXML
	private TextField txtUserName;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private PasswordField txtRepassword;
	@FXML
	private Button btnCreate;

	@FXML
	private Button btnCancel;

	private LoginService service;
	private EmployeeService employeeService;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		service = new LoginServiceImpl();
		employeeService = new EmployeeServiceImpl();
		cmbEmployee.getItems().addAll(employeeService.getAllEmployeeNames());

	}
	
    
    @FXML
	void cancel(ActionEvent event) {
    	mainPane.setVisible(false);
	}

	@FXML
	void saveLogin(ActionEvent event) {
		if(validateData()!=1)
		{
			return;
		}
		Login login = new Login();
		login.setEmployee(new EmployeeServiceImpl().getEmployeeByName(cmbEmployee.getValue()));
		login.setPassword(txtPassword.getText());
		login.setStatus("logout");
		login.setUsername(txtUserName.getText());
		int flag=service.saveLogin(login);
		if(flag==1)
		{
			new Alert(AlertType.INFORMATION,"Login User Save Success").showAndWait();
			clear();
		}
	}
	private void clear()
	{
		txtUserName.setText("");
		cmbEmployee.getSelectionModel().clearSelection();
		txtPassword.setText("");
		
		txtRepassword.setText("");
		
	}
	private int validateData()
	{
		try {
			if(cmbEmployee.getValue()==null|| cmbEmployee.getSelectionModel().isEmpty())
			{
				new Alert(AlertType.ERROR,"Select Employe").showAndWait();
				cmbEmployee.requestFocus();
				return 0;
			}
			if(txtUserName.getText().equals(""))
			{
				new Alert(AlertType.ERROR,"Select User Name for Login").showAndWait();
				txtUserName.requestFocus();
				return 0;
			}
			if(service.getLoginByName(txtUserName.getText())!=null)
			{
				new Alert(AlertType.ERROR,"User Name Already Exist Choose Another").showAndWait();
				txtUserName.requestFocus();
				return 0;
			}
			if(txtPassword.getText().equals(""))
			{
				new Alert(AlertType.ERROR,"Enter Password").showAndWait();
				txtPassword.requestFocus();
				txtPassword.setVisible(true);
				return 0;
			}

			if(txtRepassword.getText().equals(""))
			{
				new Alert(AlertType.ERROR,"Enter Re-Password").showAndWait();
				txtRepassword.requestFocus();
				txtRepassword.setVisible(true);
				return 0;
			}
			if(!txtPassword.getText().equals(txtRepassword.getText()))
			{
				new Alert(AlertType.ERROR,"Repassword Not Match With Password!!!").showAndWait();
				txtRepassword.requestFocus();
				txtRepassword.setVisible(true);
				return 0;
			}
			return 1;
		} catch (Exception e) {
			new Alert(AlertType.ERROR,"Error").showAndWait();
			System.out.println(e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}
	

}
