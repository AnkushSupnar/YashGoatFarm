package application.controler;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import application.Main;
import application.ViewUtil;
import application.guiUtil.AlertNotification;
import application.print.CouriorReceipt;
import application.print.GenerateBill;
import application.print.PrintFile;
import hibernate.GetBackup;
import hibernate.entities.BankTransaction;
import hibernate.entities.Bill;
import hibernate.entities.CounterStockData;
import hibernate.entities.Customer;
import hibernate.entities.Item;
import hibernate.entities.ItemStock;
import hibernate.entities.Login;
import hibernate.entities.Transaction;
import hibernate.service.service.BankService;
import hibernate.service.service.BankTransactionService;
import hibernate.service.service.BillService;
import hibernate.service.service.CounterStockDataService;
import hibernate.service.service.CustomerService;
import hibernate.service.service.EmployeeService;
import hibernate.service.service.ItemService;
import hibernate.service.service.ItemStockService;
import hibernate.service.serviceImpl.BankServiceImpl;
import hibernate.service.serviceImpl.BankTransactionServiceImpl;
import hibernate.service.serviceImpl.BillServiceImpl;
import hibernate.service.serviceImpl.CounterStockDataServiceImpl;
import hibernate.service.serviceImpl.CustomerServiceImpl;
import hibernate.service.serviceImpl.EmployeeServiceImpl;
import hibernate.service.serviceImpl.ItemServiceImpl;
import hibernate.service.serviceImpl.ItemStockServiceImpl;
import hibernate.util.CommonData;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class BillControler implements Initializable{

	@FXML private BorderPane mainPanel;
	@FXML private TextField txtBillNo;
    @FXML private DatePicker date;
    @FXML private TextField txtCustomerName;
    @FXML private Button btnSearch;
    @FXML private Button btnNew;
    @FXML private TextArea txtCustomerInfo;
    @FXML private ComboBox<String> cmbSalesman;
    @FXML private TextField txtItemName;
    @FXML private TextField txtUnit;
    @FXML private TextField txtQty;
    @FXML private TextField txtRate;
    @FXML private TextField txtAmount;
    @FXML private Button btnAdd;
    @FXML private Button btnClear;
    @FXML private Button btnRemove;
    @FXML private Button btnUpdate;
    @FXML private TableView<Transaction> table;
    @FXML private TableColumn<Transaction, Long> colSrNo;
    @FXML private TableColumn<Transaction, String> colItemName;
    @FXML private TableColumn<Transaction, String> colUnit;
    @FXML private TableColumn<Transaction, Double> colQty;
    @FXML private TableColumn<Transaction, Double> colRate;
    @FXML private TableColumn<Transaction, Double> colAmount;
    @FXML private Button btnSave;
    @FXML private Button btnClearBill;
    @FXML private Button btnExit;
    @FXML private Button btnPrint;
    @FXML private Button btnEditBill;
    @FXML private ComboBox<String> cmbRecievedBy;
    @FXML private ComboBox<String> cmbBankName;
    @FXML private TextField txtReffNo;
    @FXML private TextField txtNetTotal;
    @FXML private TextField txtTransoChrgs;
    @FXML private TextField txtOtherChargs;
    @FXML private TextField txtGrandTotal;
    @FXML private TextField txtReivedAmount;
  
    @FXML private TableView<Bill> tableOldBill;
    @FXML private TableColumn<Bill, Long> colBillNo;
    @FXML private TableColumn<Bill,String> colCustomerName;
    @FXML private TableColumn<Bill,LocalDate> colDate;
    @FXML private TableColumn<Bill,Double> colBillAmount;
    @FXML private TableColumn<Bill, Double> colRecivedAmount;
    @FXML private Button btnTodaysBills;
    @FXML private Button btnThisWeek;
    @FXML private Button btnThisMonth;
    @FXML private Button btnThisYear;
    @FXML private Button btnAllBills;
    @FXML private DatePicker dateSearch;
    @FXML private TextField txtSearchBillNo;
   
    
    private ObservableList<Bill> oldBillList = FXCollections.observableArrayList();
    
    private ObservableList<Transaction>trList = FXCollections.observableArrayList();
    private BillService billService;
    private CustomerService customerService;
    private ItemService itemService;
    private EmployeeService employeeService;
    private BankService bankService;
    private BankTransactionService bankTrService;
    private ItemStockService itemStockService;
    //private ObservableList<String>itemNameList = FXCollections.observableArrayList();
    private SuggestionProvider<String> customerNameProvider;
    private ObservableList<String> customerNameList = FXCollections.observableArrayList();
    private CounterStockDataService counterStockDataService;
    private AlertNotification notification;
	private Login login;
	// private long billNo;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		billService = new BillServiceImpl();
		customerService = new CustomerServiceImpl();
		itemService = new ItemServiceImpl();
		employeeService = new EmployeeServiceImpl();
		bankService = new BankServiceImpl();
		bankTrService = new BankTransactionServiceImpl();
		itemStockService = new ItemStockServiceImpl();
		counterStockDataService = new CounterStockDataServiceImpl();
		notification = new AlertNotification();
		// billNo = 0;
		date.setValue(LocalDate.now());
		txtBillNo.setText("" + billService.getNewBNillNo());
		colSrNo.setCellValueFactory(new PropertyValueFactory<Transaction, Long>("id"));
		colItemName.setCellValueFactory(new PropertyValueFactory<Transaction, String>("itemname"));
		colUnit.setCellValueFactory(new PropertyValueFactory<Transaction, String>("unit"));
		colQty.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("quantity"));
		colRate.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("rate"));
		colAmount.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("amount"));
		table.setItems(trList);

		customerNameList.addAll(customerService.getAllCustomerNames());
		customerNameProvider = SuggestionProvider.create(customerService.getAllCustomerNames());
		new AutoCompletionTextFieldBinding<>(txtCustomerName, customerNameProvider);
		CommonData.setStockItemNames();
		// itemNameList.addAll(CommonData.itemNames);
		// TextFields.bindAutoCompletion(txtCustomerName, customerNameList);
		TextFields.bindAutoCompletion(txtItemName, CommonData.stockItemNames);

		// cmbSalesman.getItems().addAll(employeeService.getAllSalesmanNames());
		login = ViewUtil.login;
		if (login.getId() == 1) {
			cmbSalesman.getItems().addAll(employeeService.getAllSalesmanNames());
		} else {
			cmbSalesman.getItems().add(login.getEmployee().getFname() + " " + login.getEmployee().getMname() + " "
					+ login.getEmployee().getLname());
			// cmbSalesman.getSelectionModel().select(1);
			cmbSalesman.setValue(login.getEmployee().getFname() + " " + login.getEmployee().getMname() + " "
					+ login.getEmployee().getLname());
		}
		cmbRecievedBy.getItems().add("By Hand");
		cmbRecievedBy.getItems().add("By Courier");
		cmbRecievedBy.getItems().add("By Vehicle");

		cmbBankName.getItems().addAll(bankService.getAllBankNames());
		oldBillList.addAll(billService.getDateWiseBill(LocalDate.now()));
		colBillNo.setCellValueFactory(new PropertyValueFactory<Bill, Long>("billno"));
		colCustomerName.setCellValueFactory(new PropertyValueFactory<Bill, String>("recievedby"));
		colDate.setCellValueFactory(new PropertyValueFactory<Bill, LocalDate>("date"));
		colBillAmount.setCellValueFactory(new PropertyValueFactory<Bill, Double>("nettotal"));
		colRecivedAmount.setCellValueFactory(new PropertyValueFactory<Bill, Double>("recivedamount"));
		for (Bill b : oldBillList) {
			b.setNettotal(b.getNettotal() + b.getTransportingchrges() + b.getOtherchargs());
			b.setRecievedby(
					b.getCustomer().getFname() + " " + b.getCustomer().getMname() + " " + b.getCustomer().getLname());
		}
		//salemanWiseOlBillList();
		tableOldBill.setItems(oldBillList);
	}

	@FXML
	void customerNameAction(ActionEvent event) {
		if (!txtCustomerName.getText().equals("") || txtCustomerName.getText() != null) {
			btnSearch.requestFocus();
		}
	}

	@FXML
	void searchCustomer(ActionEvent event) {
		try {
			if (txtCustomerName.getText().equals("") || txtCustomerName.getText() == null) {
				txtCustomerName.requestFocus();
				return;
			}
			Customer customer = customerService.getCustomerByName(txtCustomerName.getText());
			if (customer != null) {
				txtCustomerInfo.setText(customer.getMobileno() + "\n" + customer.getAddress() + " City-"
						+ customer.getCity() + "\nTaluka-" + customer.getTaluka() + " District-"
						+ customer.getDistrict() + " Pin-" + customer.getPin());
				cmbSalesman.requestFocus();
			}
			else
			{
				notification.showErrorMessage("No Customer Found Select Again !!!");
				txtCustomerName.requestFocus();
				txtCustomerInfo.setText("");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			notification.showErrorMessage( e.getMessage());
		}
	}

	@FXML
	void searchItem(ActionEvent event) {
		if (txtItemName.getText().equals("") || txtItemName.getText() == null) {
			txtItemName.requestFocus();
			return;
		}
		if (!CommonData.stockItemNames.contains(txtItemName.getText())) {
			return;
		}
		Item item = itemService.getItemByName(txtItemName.getText());
		if (item != null) {
			txtUnit.setText(item.getUnit());
			txtRate.setText("" + item.getRate());
			txtQty.requestFocus();

		}
	}

	@FXML
	void txtQtyAction(ActionEvent event) {
		try {
			if (txtQty.getText().equals("") || txtQty.getText() == null) {
				return;
			}
			if (txtItemName.getText().equals("") || txtUnit.getText().equals("")) {
				notification.showErrorMessage("Select Item Again!!!");
				txtItemName.requestFocus();
				return;
			}
			if (!isNumber(txtRate.getText())) {
				notification.showErrorMessage("Enter Rate in Digit!!!");
				txtRate.requestFocus();
				return;
			}
			if (!isNumber(txtQty.getText())) {
				notification.showErrorMessage("Enter Quantity in Digit!!!");
				txtQty.requestFocus();
				txtQty.selectAll();
				return;
			}
			txtAmount.setText("" + (Float.parseFloat(txtRate.getText()) * Float.parseFloat(txtQty.getText())));
			btnAdd.requestFocus();
		} catch (Exception e) {
			notification.showErrorMessage("Enter Quantity in Digits!!!");
			txtQty.setText("");
			txtQty.requestFocus();
			return;
		}
	}

	@FXML
	void txtQtyKeyEvent(KeyEvent event) {
		if (txtQty.getText().equals("-")) {
			return;
		}
		if (!isNumber(txtQty.getText())) {
			txtQty.setText("");
		}
	}

	@FXML
	void btnAddAction(ActionEvent event) {
		if (txtNetTotal.getText().equals("") || txtNetTotal.getText() == null) {
			txtNetTotal.setText("" + 0.0);
		}
		if (txtAmount.getText().equals("") || txtItemName.getText().equals("") || txtUnit.getText().equals("")) {
			notification.showErrorMessage("Select Item Again");
			txtItemName.requestFocus();
			return;
		}
		Bill bill = new Bill();
		bill = billService.getBillByBillno(Long.parseLong(txtBillNo.getText()));
		if (bill == null) {
			bill = new Bill();
			bill.setBillno(Long.parseLong(txtBillNo.getText()));
		}
		// bill.setBillno(billNo);
		int index = -1;
		double com=0;
		if(itemService.getItemByName(txtItemName.getText()).getLabourCharges()>0)
		{
			if (txtItemName.getText().equals("Indonesian Baahubali"))
				com =  0.75;
			else if(txtItemName.getText().equals("Bangladesh Hape Rad Napear"))
				com=0.50;
			else
				com = Double.parseDouble(txtRate.getText()) * 0.25;
		}
		
		else
		{
			com = itemService.getItemByName(txtItemName.getText()).getCommision();
		}
		Transaction transaction = new Transaction(
				txtItemName.getText(), 
				txtUnit.getText(),
				Double.parseDouble(txtRate.getText()),
				Double.parseDouble(txtQty.getText()),
				Double.parseDouble(txtAmount.getText()), 
				bill,
				(com * Double.parseDouble(txtQty.getText())));
		for (int i = 0; i < trList.size(); i++) {
			if (trList.get(i).getItemname().equals(transaction.getItemname())
					&& trList.get(i).getRate() == transaction.getRate()) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			// check Stock
//			if (transaction.getQuantity() > itemStockService.getItemStock(transaction.getItemname())) {
//				new Alert(AlertType.ERROR, "Quantity Not Available In Stock\n Please Check Stock\nAvailable Quantity="
//						+ itemStockService.getItemStock(transaction.getItemname())).showAndWait();
//				return;
//			}
			if(transaction.getQuantity()>counterStockDataService.getCounterItemStock(transaction.getItemname()))
			{
				notification.showErrorMessage("Quantity Not Available In Stock\n Please Check Stock\nAvailable Quantity="
						+ counterStockDataService.getCounterItemStock(transaction.getItemname()));
				return;
			}
			transaction.setId(trList.size() + 1);
			trList.add(transaction);
			txtNetTotal.setText("" + (Double.parseDouble(txtNetTotal.getText()) + transaction.getAmount()));
			calculateGrandTotal();

		} else {
			if (transaction.getQuantity() + trList.get(index).getQuantity() >
			counterStockDataService.getCounterItemStock(transaction.getItemname())) {
//				new Alert(AlertType.ERROR, "Quantity Not Available In Stock\n Please Check Stock\nAvailable Quantity="
//						+ itemStockService.getItemStock(transaction.getItemname())).showAndWait();
				notification.showErrorMessage("Quantity Not Available In Stock\n Please Check Stock\nAvailable Quantity="+ 
						counterStockDataService.getCounterItemStock(transaction.getItemname()));
				return;
			}
			txtNetTotal.setText("" + (Double.parseDouble(txtNetTotal.getText()) + transaction.getAmount()));
			transaction.setQuantity(transaction.getQuantity() + trList.get(index).getQuantity());
			transaction.setAmount(transaction.getQuantity() * transaction.getRate());
			//transaction.setCommision(transaction.getQuantity() * itemService.getCommision(txtItemName.getText()));
			transaction.setCommision(transaction.getQuantity() * com);
			trList.remove(index);
			transaction.setId(index + 1);
			trList.add(index, transaction);
			calculateGrandTotal();
		}
		clear();
		txtItemName.requestFocus();
	}

	@FXML
	void btnClearAction(ActionEvent event) {
		clear();
	}

	@FXML
	void btnremoveAction(ActionEvent event) {
		remove(table.getSelectionModel().getSelectedIndex());
	}

	@FXML
	void btnUpdateAction(ActionEvent event) {
		Transaction tr = table.getSelectionModel().getSelectedItem();
		if (tr != null) {
			txtItemName.setText(tr.getItemname());
			txtUnit.setText(tr.getUnit());
			txtRate.setText("" + tr.getRate());
			txtQty.setText("" + tr.getQuantity());
			txtAmount.setText("" + tr.getAmount());
		}
	}

	@FXML
	void cmbPaymentModeAction(ActionEvent event) {

	}

	@FXML
	void txtTranspChrgsAction(ActionEvent event) {
		try {
			calculateGrandTotal();
		} catch (Exception e) {
			return;
		}
	}

	@FXML
	void txtOtherChargesAction(ActionEvent event) {
		calculateGrandTotal();
	}

	@FXML
	void btnSaveAction(ActionEvent event) {
		if (validateData() != 1) {
			return;
		}
		//btnSearch.fire();
		Bill bill = new Bill(customerService.getCustomerByName(txtCustomerName.getText()), date.getValue(),
				Double.parseDouble(txtNetTotal.getText()), Double.parseDouble(txtTransoChrgs.getText()),
				Double.parseDouble(txtOtherChargs.getText()), bankService.getBankByName(cmbBankName.getValue()),
				cmbRecievedBy.getValue(), txtReffNo.getText(),
				employeeService.getEmployeeByName(cmbSalesman.getValue()), null,
				Double.parseDouble(txtReivedAmount.getText()), 0.0f);
		bill.setBillno(Long.parseLong(txtBillNo.getText()));
		for (Transaction tr : trList) {
			tr.setBill(bill);
			tr.setId(0);
		}
		bill.setTransaction(trList);
		Bill oldBill = billService.getBillByBillno(bill.getBillno());
		// Edit Bill Add Stock In StockMaster
		if (oldBill != null) {
			//add Stock in counter
			
			for (Transaction tr : oldBill.getTransaction()) {
				counterStockDataService.saveCounterStockdata(new CounterStockData(tr.getItemname(),tr.getQuantity(),tr.getUnit()));
			}
			
			
//			ItemStock stock;
//			for (Transaction tr : oldBill.getTransaction()) {
//				stock = itemStockService.getItemStockByItemName(tr.getItemname());
//				
//				stock.setQuantity(tr.getQuantity());
//				itemStockService.saveItemStock(stock);
//				System.out.println("Stock Added" + stock.getQuantity());
//				stock = null;
//			}
//			
			
		}
		BankTransaction banktr = new BankTransaction("Add Bill Amount BillNo " + bill.getBillno(), bill.getBillno(),
				bill.getRecivedamount(), 0.0f, bill.getBank().getId(), date.getValue());
		int flag = billService.saveBill(bill);
		if (flag == 1) {
			int f = bankTrService.saveBankTransaction(banktr);
			if (f == 1) {
				bankService.addBankBalance(bill.getBank().getId(), bill.getRecivedamount());
			}
			reduceStock(bill.getTransaction());
			notification.showSuccessMessage( "Bill saved Success");
			showPrintBillConfirmation(bill.getBillno());
			showPrintCouriorConfirmation(bill);
			// new BillPrint(bill.getBillno());
			// new PrintFile("D:\\Software\\Prints\\bill.pdf");

			bill.setNettotal(bill.getNettotal() + bill.getTransportingchrges() + bill.getOtherchargs());
			bill.setRecievedby(bill.getCustomer().getFname() + " " + bill.getCustomer().getMname() + " "
					+ bill.getCustomer().getLname());

			oldBillList.add(bill);
			// btnTodaysBills.fire();
			new hibernate.util.GetBackup();
			clearBill();
		}
		if (flag == 2) {
			if (oldBill != null) {
				BankTransaction bt = new BankTransaction();
				bt.setCredit(oldBill.getRecivedamount());
				bt.setBankid(oldBill.getBank().getId());
				bt.setDebit(0.0f);
				bt.setParticulars("Edit Bill No " + oldBill.getBillno());
				bt.setReffid(oldBill.getBillno());
				bt.setDate(date.getValue());
				int f = bankTrService.saveBankTransaction(bt);
				if (f == 1)
					bankService.reduceBankBalance(oldBill.getBank().getId(), oldBill.getRecivedamount());

			}

			int f = bankTrService.saveBankTransaction(banktr);
			if (f == 1) {
				bankService.addBankBalance(bill.getBank().getId(), bill.getRecivedamount());
			}
			reduceStock(bill.getTransaction());
			showPrintBillConfirmation(bill.getBillno());
			showPrintCouriorConfirmation(bill);
			int index = -1;
			for (int i = 0; i < oldBillList.size(); i++) {
				if (oldBillList.get(i).getBillno() == bill.getBillno()) {
					index = i;
					break;
				}
			}
			// btnTodaysBills.fire();
			if (index != -1) {
				bill.setNettotal(bill.getNettotal() + bill.getTransportingchrges() + bill.getOtherchargs());
				bill.setRecievedby(bill.getCustomer().getFname() + " " + bill.getCustomer().getMname() + " "
						+ bill.getCustomer().getLname());
				oldBillList.remove(index);
				oldBillList.add(index, bill);
			}
			notification.showSuccessMessage("Bill Update Success");

			clearBill();

			new GetBackup("D:\\Software\\Backup\\");
			new hibernate.util.GetBackup();
		}
	}

	private void reduceStock(List<Transaction> list) {
		try {
			for(Transaction tr:list)
			{
				double qty=tr.getQuantity();
				qty*=-1;
				counterStockDataService.saveCounterStockdata(new CounterStockData(tr.getItemname(),qty,tr.getUnit()));
			}
//			ItemStock stock;
//			for (Transaction tr : list) {
//				stock = itemStockService.getItemStockByItemName(tr.getItemname());
//				stock.setQuantity(tr.getQuantity() - (tr.getQuantity() * 2));
//				itemStockService.saveItemStock(stock);

//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void btnClearBillAction(ActionEvent event) {
		clearBill();
	}

	@FXML
	void btnExitAction(ActionEvent event) {
		//System.out.println(mainPanel.getParent());
		mainPanel.setVisible(false);
	}

	@FXML
	void btnNewAction(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(Main.class.getResource("/application/view/create/AddCustomerFrame.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("My modal window");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(((Node) event.getSource()).getScene().getWindow());
		stage.show();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				customerNameList.clear();
				customerNameList.addAll(customerService.getAllCustomerNames());
				customerNameProvider.clearSuggestions();
				customerNameProvider.addPossibleSuggestions(customerNameList);
			}
		});

	}

	@FXML
	void btnPrintAction(ActionEvent event) {
		if (tableOldBill.getSelectionModel().getSelectedItem() != null) {
			new GenerateBill(tableOldBill.getSelectionModel().getSelectedItem().getBillno());
			new PrintFile("D:\\Software\\Prints\\bill.pdf");
		}
	}

	@FXML
	void btnEditBillAction(ActionEvent event) {
		if (tableOldBill.getSelectionModel().isEmpty()) {
			return;
		}
		Bill bill = billService.getBillByBillno(tableOldBill.getSelectionModel().getSelectedItem().getBillno());
		if(login.getEmployee().getId()!=1)
		{
			if(bill.getEmployee().getId()!=login.getEmployee().getId())
			{
				notification.showErrorMessage("You are not Authorized to Edit This Bill !!!");
				return;
			}
		}
		
		if (bill != null) {

			txtBillNo.setText("" + bill.getBillno());
			txtCustomerName.setText(bill.getCustomer().getFname() + " " + bill.getCustomer().getMname() + " "
					+ bill.getCustomer().getLname());
			btnSearch.fire();
			cmbSalesman.setValue(bill.getEmployee().getFname() + " " + bill.getEmployee().getMname() + " "
					+ bill.getEmployee().getLname());
			trList.clear();
			trList.addAll(bill.getTransaction());
			txtNetTotal.setText("" + bill.getNettotal());
			txtTransoChrgs.setText("" + bill.getTransportingchrges());
			txtOtherChargs.setText("" + bill.getOtherchargs());
			txtGrandTotal.setText("" + (bill.getNettotal() + bill.getOtherchargs() + bill.getTransportingchrges()));
			cmbRecievedBy.setValue(bill.getRecievedby());
			txtReffNo.setText(bill.getRecievedreff());
			cmbBankName.setValue(bill.getBank().getBankname());
			txtReivedAmount.setText("" + bill.getRecivedamount());
		}
	}

	@FXML
	void loadAllBills(ActionEvent event) {
		oldBillList.clear();
		oldBillList.addAll(billService.getAllBills());
		for (Bill b : oldBillList) {
			b.setNettotal(b.getNettotal() + b.getTransportingchrges() + b.getOtherchargs());
			b.setRecievedby(
					b.getCustomer().getFname() + " " + b.getCustomer().getMname() + " " + b.getCustomer().getLname());
		}

	}

	@FXML
	void loadThisMonthBills(ActionEvent event) {
		oldBillList.clear();
		oldBillList.addAll(billService.getMonthWiseBill(LocalDate.now()));
		for (Bill b : oldBillList) {
			b.setNettotal(b.getNettotal() + b.getTransportingchrges() + b.getOtherchargs());
			b.setRecievedby(
					b.getCustomer().getFname() + " " + b.getCustomer().getMname() + " " + b.getCustomer().getLname());
		}

	}

	@FXML
	void loadThisWeekBills(ActionEvent event) {
		oldBillList.clear();
		oldBillList.addAll(billService.getThisWeekBill());
		for (Bill b : oldBillList) {
			b.setNettotal(b.getNettotal() + b.getTransportingchrges() + b.getOtherchargs());
			b.setRecievedby(
					b.getCustomer().getFname() + " " + b.getCustomer().getMname() + " " + b.getCustomer().getLname());
		}
	}

	@FXML
	void loadThisYearBills(ActionEvent event) {
		oldBillList.clear();
		oldBillList.addAll(billService.getThisYearBill());
		for (Bill b : oldBillList) {
			b.setNettotal(b.getNettotal() + b.getTransportingchrges() + b.getOtherchargs());
			b.setRecievedby(
					b.getCustomer().getFname() + " " + b.getCustomer().getMname() + " " + b.getCustomer().getLname());
		}
	}

	@FXML
	void loadTodaysBills(ActionEvent event) {
		oldBillList.clear();
		oldBillList.addAll(billService.getDateWiseBill(LocalDate.now()));
		for (Bill b : oldBillList) {
			b.setNettotal(b.getNettotal() + b.getTransportingchrges() + b.getOtherchargs());
			b.setRecievedby(
					b.getCustomer().getFname() + " " + b.getCustomer().getMname() + " " + b.getCustomer().getLname());
		}
	}

	private boolean isNumber(String num) {
		if (num == null) {
			return false;
		}
		try {
			Double.parseDouble(num);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void calculateGrandTotal() {
		try {
			if (!isNumber(txtTransoChrgs.getText())) {
				txtTransoChrgs.setText("" + 0.0);
			}
			if (!isNumber(txtOtherChargs.getText())) {
				txtOtherChargs.setText("" + 0.0);
			}
			txtGrandTotal.setText("" + (Double.parseDouble(txtNetTotal.getText())
					+ Double.parseDouble(txtTransoChrgs.getText()) + Double.parseDouble(txtOtherChargs.getText())));
		} catch (Exception e) {
			notification.showErrorMessage("Error" + e.getMessage());
		}
	}

	private void remove(int selected) {
		Transaction tr = trList.get(selected);
		if (tr != null) {
			int index = -1;
			for (int i = 0; i < trList.size(); i++) {
				if (trList.get(i).getItemname().equals(tr.getItemname()) && trList.get(i).getRate() == tr.getRate()) {
					index = i;
					break;
				}
			}
			if (index != -1) {
				txtNetTotal.setText("" + (Double.parseDouble(txtNetTotal.getText()) - tr.getAmount()));
				trList.remove(index);
				int sr = index;
				for (int i = index; i < trList.size(); i++) {
					trList.get(i).setId(++sr);
				}
			}

		}
	}

	private void clear() {
		txtItemName.setText("");
		txtUnit.setText("");
		txtRate.setText("");
		txtAmount.setText("");
		txtQty.setText("");
	}

	private int validateData() {
		try {
			if (date.getValue() == null) {
				notification.showErrorMessage("Select Billing Date!!!");
				date.requestFocus();
				return 0;
			}
			if(customerService.getCustomerByName(txtCustomerName.getText())==null)
			{
				notification.showErrorMessage("Select Customer!!!");
				txtCustomerName.requestFocus();
				return 0;
			}
			if (txtCustomerInfo.getText().equals("")) {
				notification.showErrorMessage("Select Customer!!!");
				txtCustomerName.requestFocus();
				return 0;
			}
			if (cmbSalesman.getValue() == null) {
				notification.showErrorMessage("Select Salesman!!!");
				cmbSalesman.requestFocus();
				return 0;
			}
			if (trList.size() == 0) {
				notification.showErrorMessage("No Data to save add items!!!");
				txtItemName.requestFocus();
				return 0;
			}
			if (txtGrandTotal.getText().equals("" + 0.0)) {
				notification.showErrorMessage("No Data to save add items!!!");
				txtItemName.requestFocus();
				return 0;
			}
			if (cmbRecievedBy.getValue() == null) {
				notification.showErrorMessage("select Recived By!!!");
				cmbRecievedBy.requestFocus();
				return 0;
			}
			if (txtReffNo.getText().equals("")) {
				notification.showErrorMessage("Enter reference!!!");
				txtReffNo.requestFocus();
				return 0;
			}
			if (cmbBankName.getValue() == null) {
				notification.showErrorMessage("Select Payment recived Bank!!!");
				cmbBankName.requestFocus();
				return 0;
			}
			if (txtReivedAmount.getText().equals("")) {
				notification.showErrorMessage("Enter Recived Amount!!!");
				txtReivedAmount.requestFocus();
				return 0;
			}
			if (Double.parseDouble(txtReivedAmount.getText()) > Double.parseDouble(txtGrandTotal.getText())) {
				notification.showErrorMessage("Recived Amount should be \nless than or equal Grand Total!!!");
				txtReivedAmount.requestFocus();
				return 0;
			}

			return 1;

		} catch (Exception e) {
			notification.showErrorMessage("Error");
			e.printStackTrace();
			return 0;
		}
	}

	private void clearBill() {
		txtBillNo.setText("" + billService.getNewBNillNo());
		date.setValue(LocalDate.now());
		txtCustomerName.setText("");
		txtCustomerInfo.setText("");
		cmbSalesman.getSelectionModel().clearSelection();
		txtItemName.setText("");
		txtQty.setText("");
		txtUnit.setText("");
		txtRate.setText("");
		txtAmount.setText("");
		trList.clear();
		txtNetTotal.setText("" + 0.0f);
		txtTransoChrgs.setText("" + 0.0f);
		txtOtherChargs.setText("" + 0.0f);
		txtGrandTotal.setText("" + 0.0f);
		txtReffNo.setText("");
		txtReivedAmount.setText("");
		cmbRecievedBy.getSelectionModel().clearSelection();
		cmbBankName.getSelectionModel().clearSelection();
	}

	private void showPrintBillConfirmation(long billno) {
		Stage stage = (Stage) mainPanel.getScene().getWindow();
		Alert.AlertType type = Alert.AlertType.CONFIRMATION;
		Alert alert = new Alert(type, "");
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(stage);
		alert.getDialogPane().setContentText("Do You Want Print Bill");
		alert.getDialogPane().setHeaderText("Confirmation");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			// new BillPrint(billno);
			try {
				new GenerateBill(billno);
				new PrintFile("D:\\Software\\Prints\\bill.pdf");
			} catch (Exception e) {
				notification.showErrorMessage( e.getMessage());
			}
		} else if (result.get() == ButtonType.CANCEL) {

		}
	}

	private void showPrintCouriorConfirmation(Bill bill) {
		Stage stage = (Stage) mainPanel.getScene().getWindow();
		Alert.AlertType type = Alert.AlertType.CONFIRMATION;
		Alert alert = new Alert(type, "");
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(stage);
		alert.getDialogPane().setContentText("Do You Want Print Courior Sticker?");
		alert.getDialogPane().setHeaderText("Confirmation");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			new CouriorReceipt(bill);
			new PrintFile("D:\\Software\\Prints\\courior.pdf");
		} else if (result.get() == ButtonType.CANCEL) {
			return;
		}
	}

	@FXML
	void dateSearchAction(ActionEvent event) {
		if (dateSearch.getValue() == null) {
			return;
		}
		oldBillList.clear();

		oldBillList.addAll(billService.getDateWiseBill(dateSearch.getValue()));
		for (Bill b : oldBillList) {
			b.setNettotal(b.getNettotal() + b.getTransportingchrges() + b.getOtherchargs());
			b.setRecievedby(
					b.getCustomer().getFname() + " " + b.getCustomer().getMname() + " " + b.getCustomer().getLname());
		}
		// new Alert(AlertType.INFORMATION,"dateAction").showAndWait();
	}

	@FXML
	void txtSearchBillNo(ActionEvent event) {
		try {
			if (txtSearchBillNo.getText().equals("")) {
				btnTodaysBills.fire();
				return;
			}
			if (!isNumber(txtSearchBillNo.getText())) {
				txtSearchBillNo.setText("");
				return;
			}

			Bill b = billService.getBillByBillno(Long.parseLong(txtSearchBillNo.getText()));
			if (b != null) {
				oldBillList.clear();

				b.setNettotal(b.getNettotal() + b.getTransportingchrges() + b.getOtherchargs());
				b.setRecievedby(b.getCustomer().getFname() + " " + b.getCustomer().getMname() + " "
						+ b.getCustomer().getLname());
				oldBillList.add(b);
			}
		} catch (Exception e) {
			notification.showErrorMessage("Error in Search Bill " + e.getMessage());
		}
	}
	
}
