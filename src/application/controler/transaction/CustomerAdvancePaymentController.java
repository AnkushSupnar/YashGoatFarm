package application.controler.transaction;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import application.guiUtil.AlertNotification;
import hibernate.entities.CustomerAdvancePayment;
import hibernate.service.service.BankService;
import hibernate.service.service.CustomerAdvancePaymentService;
import hibernate.service.service.CustomerService;
import hibernate.service.serviceImpl.BankServiceImpl;
import hibernate.service.serviceImpl.CustomerAdvancePaymentServiceImpl;
import hibernate.service.serviceImpl.CustomerServiceImpl;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class CustomerAdvancePaymentController implements Initializable {

	@FXML private DatePicker date;
 	@FXML private TextField txtCustomer;
 	@FXML private ComboBox<String> cmbBankName;
	@FXML private TextField txtAmount;
	@FXML private TextField txtReff;
	@FXML private Button btnSave;
 	@FXML private Button btnClear;
	@FXML private Button btnUpdate;
	@FXML private Button btnExit;
	@FXML private TableView<CustomerAdvancePayment> table;
	@FXML private TableColumn<CustomerAdvancePayment,Long> colSrNo;
	@FXML private TableColumn<CustomerAdvancePayment,LocalDate> colDate;
	@FXML private TableColumn<CustomerAdvancePayment,String> colCustomer;
	@FXML private TableColumn<CustomerAdvancePayment,Float> colAmount;
	@FXML private TableColumn<CustomerAdvancePayment,String> colBankName;
	@FXML private TableColumn<CustomerAdvancePayment,String> colReff;

	private CustomerService customerService;
	private CustomerAdvancePaymentService paymentService;
	private AlertNotification notify;
	private BankService bankService;
	private ObservableList<CustomerAdvancePayment> list = FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		customerService = new CustomerServiceImpl();
		paymentService = new CustomerAdvancePaymentServiceImpl();
		notify = new AlertNotification();
		bankService = new BankServiceImpl();
		TextFields.bindAutoCompletion(txtCustomer,customerService.getAllCustomerNames());
		cmbBankName.getItems().addAll(bankService.getAllBankNames());
		date.setValue(LocalDate.now());
		colSrNo.setCellValueFactory(new PropertyValueFactory<>(""));
		colDate.setCellValueFactory(new PropertyValueFactory<>(""));
		colCustomer.setCellValueFactory(new PropertyValueFactory<>("")); 
		colAmount.setCellValueFactory(new PropertyValueFactory<>(""));
		colBankName.setCellValueFactory(new PropertyValueFactory<>(""));
		colReff.setCellValueFactory(new PropertyValueFactory<>(""));
		list.addAll(paymentService.getCustomerAdvanceByDate(LocalDate.now()));
		table.setItems(list);
		
		
	}

	@FXML
	void txtAmountKeyRelease(KeyEvent event) {

	}

}
