package application.controler;

import java.net.URL;
import java.util.ResourceBundle;

import application.ViewUtil;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
public class DashboardController implements Initializable {

	
	@FXML
    private BorderPane mainPane;
	@FXML
	private Label txtTitle;
	@FXML
	public Label txtWindowTitle;
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
    private Pane createPane,transactionPane,reportPane;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		viewUtil = new ViewUtil();	
		txtWindowTitle.setText("Home Page");
		transactionPane = viewUtil.getPage("transaction/TransactionMenu");
		createPane = viewUtil.getPage("home/CreateMenuFrame");
		reportPane = viewUtil.getPage("report/ReportMenu");
		txtTitle.setText(ViewUtil.login.getUsername());
		if(ViewUtil.login.getId()!=1)
		{
			btnCreate.setVisible(false);
			//btnReport.setVisible(false);
			btnSettings.setVisible(false);
			
		}
	}
    @FXML
    void logout(ActionEvent event) {
    }
    @FXML
    void openCreate(ActionEvent event) {
    	txtWindowTitle.setText("Create Master");
    	//createPane = viewUtil.getPage("home/CreateMenuFrame");
    	mainPane.setCenter(createPane);
    }
    @FXML
    void openReport(ActionEvent event) {
    	txtWindowTitle.setText("Report");
    	mainPane.setCenter(reportPane);
    }
    @FXML
    void openSettings(ActionEvent event) {
    }
    @FXML
    void openTransaction(ActionEvent event) {
    	txtWindowTitle.setText("Transaction");
    	mainPane.setCenter(transactionPane);
    }
}
