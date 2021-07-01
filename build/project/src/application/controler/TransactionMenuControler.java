package application.controler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.ViewUtil;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class TransactionMenuControler implements Initializable {

	@FXML
	private AnchorPane transactionMenuPanel;

	@FXML
	private Button btnBilling;

	@FXML
	private Button btnAllBill;

	@FXML
	private Button btnSearch;

	private BorderPane pane;
	private ViewUtil viewUtil;
	private Pane billing;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		viewUtil = new ViewUtil();
		billing = viewUtil.getPage("transaction/BillingFrame");
		

	}

	@FXML
	void openBilling(ActionEvent event) {
		pane = (BorderPane) transactionMenuPanel.getParent();
		pane.setCenter(billing);
		billing.setVisible(true);
	}

	@FXML
	void openSearchBill(ActionEvent event) {

	}

	@FXML
	void openViewAllBill(ActionEvent event) {

	}

}
