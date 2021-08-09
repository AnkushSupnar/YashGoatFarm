package application.controler.transaction;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import hibernate.entities.BankTransfer;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class BankMoneyTransferController implements Initializable {

	 	@FXML private AnchorPane mainPane;
	    @FXML private ComboBox<String> cmbFromBank;
	    @FXML private ComboBox<String> cmbToBank;
	    @FXML private TextField txtAmount;
	    @FXML private Button btnTransafer;
	    @FXML private Button btnClear;
	    @FXML private TableView<BankTransfer> table;
	    @FXML private TableColumn<BankTransfer,Integer> coSrNo;
	    @FXML private TableColumn<BankTransfer,LocalDate> colDate;
	    @FXML private TableColumn<BankTransfer,String> colFrom;
	    @FXML private TableColumn<BankTransfer,String> colTo;
	    @FXML private TableColumn<BankTransfer,Float> colAmount;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}

}
