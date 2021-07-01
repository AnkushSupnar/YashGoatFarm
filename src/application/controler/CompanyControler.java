package application.controler;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import hibernate.entities.CompanyDetails;
import hibernate.service.service.CompanyService;
import hibernate.service.serviceImpl.CompanyServiceImpl;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class CompanyControler implements Initializable {
		@FXML
	    private AnchorPane mainFrame;

		@FXML
	    private TextField txtCompanyName;

	    @FXML
	    private TextField txtAddress;

	    @FXML
	    private TextField txtCity;

	    @FXML
	    private TextField txtTluka;

	    @FXML
	    private TextField txtDistrict;

	    @FXML
	    private TextField txtPin;

	    @FXML
	    private TextField txtContact;

	    @FXML
	    private TextField txtAlterContact;

	    @FXML
	    private TextField txtEmail;

	    @FXML
	    private TextField txtGst;

	    @FXML
	    private Button btnSave;

	    @FXML
	    private Button btnExit;
	    private CompanyService companyService;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		companyService = new CompanyServiceImpl();
		System.out.println(companyService.checkLicense(LocalDate.now()));
   	 if(companyService.checkLicense(LocalDate.now())==0)
   	{
   		new Alert(AlertType.ERROR,"Liscense Invalid").showAndWait();
   		System.exit(0);
   	}
		
		
		CompanyDetails company = companyService.getCompanyDetails(1);
		if(company!=null)
		{
			txtAddress.setText(company.getAddress());
			txtAlterContact.setText(company.getAltercontact());
			txtCity.setText(company.getCity());
			txtCompanyName.setText(company.getName());
			txtContact.setText(company.getContact());
			txtDistrict.setText(company.getDistrict());
			txtEmail.setText(company.getEmail());
			txtGst.setText(company.getGst());
			txtPin.setText(company.getPin());
			txtTluka.setText(company.getTaluka());
		}

	}
	@FXML
    void btnExitAction(ActionEvent event) {
		mainFrame.setVisible(false);
    }

    @FXML
    void btnSaveAction(ActionEvent event) {
    	if(validateData()!=1)
    	{
    		return;
    	}
    	CompanyDetails company = new CompanyDetails();
    	 company = companyService.getCompanyByName(txtCompanyName.getText());
    	
    	 if(company==null)
    	{
    		company = new CompanyDetails();
    	}
    	 company.setAddress(txtAddress.getText());
    	company.setAltercontact(txtAlterContact.getText());
    	company.setCity(txtCity.getText());
    	company.setContact(txtContact.getText());
    	company.setDistrict(txtDistrict.getText());
    	company.setEmail(txtEmail.getText());
    	company.setGst(txtGst.getText());
    	company.setTaluka(txtTluka.getText());
    	company.setPin(txtPin.getText());
    	company.setName(txtCompanyName.getText());
    	int flag = companyService.saveCompany(company);
    	if(flag!=0)
    	{
    		new Alert(AlertType.INFORMATION,"Record Saved Success").showAndWait();
    		if(company.getDate()==null)
    		{
    			new Alert(AlertType.ERROR,"Liscense Not Valid").showAndWait();
    			System.exit(0);
    		}
    	}
    	
    	
    }
    private int validateData()
    {
    	try {
			if(txtCompanyName.getText().equals(""))
			{
				new Alert(AlertType.ERROR,"Enter Company Name").showAndWait();
				txtCompanyName.requestFocus();
				return 0;
			}
			if(txtAddress.getText().equals(""))
			{
				new Alert(AlertType.ERROR,"Enter Company Address Line 1").showAndWait();
				txtAddress.requestFocus();
				return 0;
			}
			if(txtCity .getText().equals(""))
			{
				new Alert(AlertType.ERROR,"Enter Company City/Village").showAndWait();
				txtCity.requestFocus();
				return 0;
			}
			if(txtTluka .getText().equals(""))
			{
				new Alert(AlertType.ERROR,"Enter Company Taluka").showAndWait();
				txtTluka.requestFocus();
				return 0;
			}
			if(txtDistrict .getText().equals(""))
			{
				new Alert(AlertType.ERROR,"Enter Company District").showAndWait();
				txtDistrict.requestFocus();
				return 0;
			}
			if(txtPin .getText().equals(""))
			{
				new Alert(AlertType.ERROR,"Enter Company Pin Code").showAndWait();
				txtPin.requestFocus();
				return 0;
			}
			if(txtContact .getText().equals(""))
			{
				new Alert(AlertType.ERROR,"Enter Company Contact Number").showAndWait();
				txtContact.requestFocus();
				return 0;
			}
			if(txtAlterContact.getText().equals(""))
			{
				//new Alert(AlertType.ERROR,"Enter Company Contact Number").showAndWait();
				txtContact.setText("-");
			}
			if(txtEmail .getText().equals(""))
			{
				new Alert(AlertType.ERROR,"Enter Company Email").showAndWait();
				txtEmail.requestFocus();
				return 0;
			}
			if(txtGst .getText().equals(""))
			{
				new Alert(AlertType.ERROR,"Enter Company Gst No").showAndWait();
				txtGst.requestFocus();
				return 0;
			}
			return 1;
		} catch (Exception e) {
			new Alert(AlertType.ERROR,e.getMessage()).showAndWait();
			return 0;
		}
    }
}
