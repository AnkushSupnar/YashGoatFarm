package application.controler;

import java.net.URL;
import java.time.LocalDate;

import java.util.ResourceBundle;



import hibernate.entities.Bank;
import hibernate.entities.BankTransaction;
import hibernate.service.service.BankService;
import hibernate.service.service.BankTransactionService;
import hibernate.service.serviceImpl.BankServiceImpl;
import hibernate.service.serviceImpl.BankTransactionServiceImpl;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
public class BankStatementControler implements Initializable {
		@FXML
	    private AnchorPane mainFrame;
		@FXML
	    private ComboBox<String> cmbBankName;
	    @FXML
	    private DatePicker dateFrom;
	    @FXML
	    private DatePicker dateTo;
	    @FXML
	    private Button btnShow;
	    @FXML
	    private Button btnReset;
	    @FXML
	    private Button btnClose;
	    @FXML
	    private TableView<BankTransaction> table;
	    @FXML
	    private TableColumn<BankTransaction, Long> colSrNo;
	    @FXML
	    private TableColumn<BankTransaction,String> colDescription;
	    @FXML
	    private TableColumn<BankTransaction,LocalDate> colDate;
	    @FXML
	    private TableColumn<BankTransaction,Double> colDebit;
	    @FXML
	    private TableColumn<BankTransaction,Double> colCredit;
	    
	    @FXML
	    private TextField txtDebit;

	    @FXML
	    private TextField txtCredit;

	    @FXML
	    private TextField txtBalance;

	    
	    private BankService bankService;
	    private BankTransactionService bankTrService;
	    private ObservableList<BankTransaction>list = FXCollections.observableArrayList();
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bankService = new BankServiceImpl();
		bankTrService = new BankTransactionServiceImpl();
		colSrNo.setCellValueFactory(new PropertyValueFactory<>("id"));
		colDescription.setCellValueFactory(new PropertyValueFactory<>("particulars"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		colDebit.setCellValueFactory(new PropertyValueFactory<>("debit"));
		colCredit.setCellValueFactory(new PropertyValueFactory<>("credit"));
		table.setItems(list);
		cmbBankName.getItems().addAll(bankService.getAllBankNames());
	}
	 @FXML
	    void btnCloseAction(ActionEvent event) {
		 mainFrame.setVisible(false);
	    }

	    @FXML
	    void btnResetAction(ActionEvent event) {
	    	list.clear();
	    	txtDebit.setText(""+0.0);
	    	txtCredit.setText(""+0.0);
	    	txtBalance.setText(""+0.0);
	    	cmbBankName.getSelectionModel().clearSelection();
	    	dateFrom.setValue(null);
	    	dateTo.setValue(null);
	    }

	    @FXML
		void btnShowAction(ActionEvent event) {
			if (validateData() != 1) {
				return;
			}
			list.clear();
			txtDebit.setText(""+0.0);
			txtCredit.setText(""+0.0);
			txtBalance.setText(""+0.0);
			Bank bank =bankService.getBankByName( cmbBankName.getValue());
			list.addAll(bankTrService.getPeriodWiseBankTransaction(dateFrom.getValue(), dateTo.getValue(), bank.getId()));
			txtBalance.setText(""+bank.getBalance());
			int sr=0;
			for(int i=0;i<list.size();i++)
			{
				list.get(i).setId(++sr);
				txtDebit.setText(""+
			(Float.parseFloat(txtDebit.getText())+list.get(i).getDebit()));
				txtCredit.setText(""+
						(Float.parseFloat(txtCredit.getText())+list.get(i).getCredit()));
			}
		}
	    private int validateData()
	    {
	    	try {
				if(cmbBankName.getValue()==null)
				{
					new Alert(AlertType.ERROR,"Select Bank Name!!!").showAndWait();
					cmbBankName.requestFocus();
					return 0;
				}
				if(dateFrom.getValue()==null)
				{
					new Alert(AlertType.ERROR,"Select Fram Date!!!").showAndWait();
					dateFrom.requestFocus();
					return 0;
				}
				if(dateTo.getValue()==null)
				{
					new Alert(AlertType.ERROR,"Select To Date!!!").showAndWait();
					dateTo.requestFocus();
					return 0;
				}
				return 1;
			} catch (Exception e) {
				new Alert(AlertType.ERROR,e.getMessage()).showAndWait();
				return 0;
			}
	    }
}
