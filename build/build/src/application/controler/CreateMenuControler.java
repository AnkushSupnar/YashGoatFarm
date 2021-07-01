package application.controler;

import java.net.URL;
import java.util.ResourceBundle;

import application.ViewUtil;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
public class CreateMenuControler implements Initializable {

	 @FXML private AnchorPane transactionMenuPanel;
     @FXML private AnchorPane createMenuPanel;
    
    @FXML private Button btnAddEmployee;
    @FXML private Button btnEditEmployee;
    @FXML private Button btnViewEmployee;
    @FXML private Button btnAddUser;
    @FXML private Button btnEditUser;
    @FXML private Button btnViewUser;
    @FXML private Button btnAddCustomer;
    @FXML private Button btnEditCustomer;
    @FXML private Button btnViewCustomer;
    @FXML private Button btnAddItem;
    @FXML private Button btnEditItem;
    @FXML private Button btnVIewItem;
    @FXML private Button btnAddParty;
    @FXML private Button btnEditParty;
    @FXML private Button btnViewParty;
    @FXML private Button btnAddBank;
    @FXML private Button btnEditBank;
    @FXML private Button btnViewBank;
    private BorderPane pane;
    private ViewUtil viewUtil;
    Pane  PaneaddEmployee,
    PaneaddUser,
    PaneaddBank,
    PaneaddCustomer,
    PaneaddItem,
    PaneaddParty,
    PaneviewBank,
    PaneviewCustomer,
    PaneviewEmployee,
    PaneviewItem,
    PaneviewParty,
    PaneviewUser;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		viewUtil = new ViewUtil();
		
		PaneaddUser = viewUtil.getPage("create/CreateUserFrame");
		PaneaddEmployee =  viewUtil.getPage("create/AddEmployeeFrame");
	    PaneaddBank = viewUtil.getPage("create/AddBankFrame");
	    PaneaddCustomer = viewUtil.getPage("create/AddCustomerFrame");
	    PaneaddItem = viewUtil.getPage("create/AddItemFrame");
	    PaneaddParty = viewUtil.getPage("create/AddPurchasePartyFrame");
	    //PaneviewBank = 
	    //PaneviewCustomer,
	    //PaneviewEmployee,
	    //PaneviewItem,
	    //PaneviewParty,
	    //PaneviewUser;
	    
	}
	@FXML
    void AddUser(ActionEvent event) {
		System.out.println(createMenuPanel.getParent());
		pane =(BorderPane) createMenuPanel.getParent();
		
		pane.setCenter(PaneaddUser);
		PaneaddUser.setVisible(true);
		
    }
   

    @FXML
    void addBank(ActionEvent event) {
    	pane =(BorderPane) createMenuPanel.getParent();
		pane.setCenter(PaneaddBank);
		
		PaneaddBank.setVisible(true);
    }

    @FXML
    void addCustomer(ActionEvent event) {
    	pane =(BorderPane) createMenuPanel.getParent();
		pane.setCenter(PaneaddCustomer);
		
		PaneaddCustomer.setVisible(true);
    }

    @FXML
    void addEmployee(ActionEvent event) {
    	pane =(BorderPane) createMenuPanel.getParent();
		pane.setCenter(PaneaddEmployee);
		
		PaneaddEmployee.setVisible(true);
    }

    @FXML
    void addItem(ActionEvent event) {
    	pane =(BorderPane) createMenuPanel.getParent();
		pane.setCenter(PaneaddItem);
		
		PaneaddItem.setVisible(true);
    }
    @FXML
    void addParty(ActionEvent event) {
    	pane =(BorderPane) createMenuPanel.getParent();
		pane.setCenter(PaneaddParty);
		
		PaneaddParty.setVisible(true);
    }

    @FXML
    void viewBank(ActionEvent event) {

    }

    @FXML
    void viewCustomer(ActionEvent event) {

    }

    @FXML
    void viewEmployee(ActionEvent event) {

    }

    @FXML
    void viewItem(ActionEvent event) {

    }

    @FXML
    void viewParty(ActionEvent event) {

    }

    @FXML
    void viewUser(ActionEvent event) {

    }
    
}
