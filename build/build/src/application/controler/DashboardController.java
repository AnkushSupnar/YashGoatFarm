package application.controler;

import java.net.URL;
import java.util.ResourceBundle;

import application.ViewUtil;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
public class DashboardController implements Initializable {

	
	@FXML
    private BorderPane mainPane;
	@FXML
	private Label txtTitle;
    @FXML
    private Button btnTransaction;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnReport;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnLogout;
    

    private ViewUtil viewUtil;
    private Pane createPane,transactionPane;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		viewUtil = new ViewUtil();
		createPane = viewUtil.getPage("home/CreateMenuFrame");
		transactionPane = viewUtil.getPage("transaction/TransactionMenu");
		
		//txtTitle.setText(ViewUtil.login.getUsername());
	}
    @FXML
    void logout(ActionEvent event) {
    }
    @FXML
    void openCreate(ActionEvent event) {
    	System.out.println("Create clicked");
    	
    	
    	mainPane.setCenter(createPane);
    	
    }
    @FXML
    void openReport(ActionEvent event) {
    }
    @FXML
    void openSettings(ActionEvent event) {
    }
    @FXML
    void openTransaction(ActionEvent event) {
    	
    	mainPane.setCenter(transactionPane);
    }
	

}
