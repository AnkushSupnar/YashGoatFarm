package application.controler;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import application.print.GenerateBill;
import application.print.PrintFile;
import application.print.SalesmanCuttingChargesPrint;
import hibernate.entities.BankTransaction;
import hibernate.entities.CuttingOrder;
import hibernate.entities.CuttingTransaction;
import hibernate.entities.SalesmanCuttingCharges;
import hibernate.entities.SalesmanCuttingTransaction;
import hibernate.reportEntity.SalesmanCuttingChargesTrPojo;
import hibernate.service.service.BankService;
import hibernate.service.service.CuttingOrderService;
import hibernate.service.service.EmployeeService;
import hibernate.service.service.SalesmanCuttingChargesService;
import hibernate.service.serviceImpl.BankServiceImpl;
import hibernate.service.serviceImpl.BankTransactionServiceImpl;
import hibernate.service.serviceImpl.CuttingOrderServiceImpl;
import hibernate.service.serviceImpl.EmployeeServiceImpl;
import hibernate.service.serviceImpl.SalesmanCuttingChargesServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class SalesmanCuttingChargesController implements Initializable {
	@FXML private AnchorPane mainPanel;
	@FXML private ComboBox<String> cmbSalesmanName;
    @FXML private DatePicker dateStart;
    @FXML private DatePicker dateEnd;
    @FXML private Button btnShow;
    @FXML private Button btnClear;
    
    @FXML private TableView<SalesmanCuttingChargesTrPojo> table;
    @FXML private TableColumn<SalesmanCuttingChargesTrPojo,Long> colId;
    @FXML private TableColumn<SalesmanCuttingChargesTrPojo,LocalDate> colDate;
    @FXML private TableColumn<SalesmanCuttingChargesTrPojo,String> colItemName;
    @FXML private TableColumn<SalesmanCuttingChargesTrPojo,Double> colQty;
    @FXML private TableColumn<SalesmanCuttingChargesTrPojo,Double> colCuttingCharges;
    
    @FXML private TextField txtTotalCharges;
    @FXML private ComboBox<String> cmbBankName;
    @FXML private TextField txtTransaction;
    @FXML private Button btnSave;
    @FXML private Button btnClear2;
    @FXML private Button btnPrint;
    @FXML private Button btnHome;
    
    @FXML private TableView<SalesmanCuttingCharges> tableOld;
    @FXML private TableColumn<SalesmanCuttingCharges,Long> colSrNo;
    @FXML private TableColumn<SalesmanCuttingCharges,LocalDate> colDate2;
    @FXML private TableColumn<SalesmanCuttingCharges,String> colSalesman;
    @FXML private TableColumn<SalesmanCuttingCharges,Double> colPaid;

    private BankService bankService;
    private EmployeeService employeeService;
    private CuttingOrderService orderService;
    private SalesmanCuttingChargesService salesmanCuttingService;
    
    private ObservableList<SalesmanCuttingTransaction>trList = FXCollections.observableArrayList();
    private ObservableList<CuttingOrder>orderList = FXCollections.observableArrayList();
    private ObservableList<SalesmanCuttingChargesTrPojo>salesmanTr = FXCollections.observableArrayList();
	
	private ObservableList<SalesmanCuttingCharges>allCuttingList = FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bankService = new BankServiceImpl();
		//cuttingService = new SalesmanCuttingChargesServiceImpl();
		employeeService = new EmployeeServiceImpl();
		orderService = new CuttingOrderServiceImpl();
		cmbSalesmanName.getItems().addAll(employeeService.getAllSalesmanNames());
		cmbBankName.getItems().addAll(bankService.getAllBankNames());
		salesmanCuttingService = new SalesmanCuttingChargesServiceImpl();
		
		  colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		  colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		  colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		  colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
		  colCuttingCharges.setCellValueFactory(new PropertyValueFactory<>("charges"));
		  table.setItems(salesmanTr);
		  
		 
			colSrNo.setCellValueFactory(new PropertyValueFactory<>("id"));
			colDate2.setCellValueFactory(new PropertyValueFactory<>("date"));
			colSalesman.setCellValueFactory(new PropertyValueFactory<>("saleman"));
			colPaid.setCellValueFactory(new PropertyValueFactory<>("cuttingCharges"));
			allCuttingList.addAll(salesmanCuttingService.getAllSalesmanCuttingCharges());		
			tableOld.setItems(allCuttingList);

	}
	  @FXML 
	    void btnClear2Action(ActionEvent event) {
		  btnClear.fire();
		  cmbBankName.getSelectionModel().clearSelection();
		  txtTotalCharges.setText(""+0.0);
		  txtTransaction.setText("");
	    }

	    @FXML
	    void btnClearAction(ActionEvent event) {
	    	cmbSalesmanName.getSelectionModel().clearSelection();
	    	dateStart.setValue(null);
	    	dateEnd.setValue(null);
	    	salesmanTr.clear();
	    }

	    @FXML
	    void btnHomeAction(ActionEvent event) {
	    	mainPanel.setVisible(false);
	    }

	    @FXML
	    void btnPrintAction(ActionEvent event) {
	    	try {
				if(tableOld.getSelectionModel().getSelectedItem()!=null)
				{
					showPrintBillConfirmation(tableOld.getSelectionModel().getSelectedItem().getId());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }

	    @FXML
	    void btnSaveAction(ActionEvent event) {
	    	if(salesmanTr.size()==0)
	    	{
	    		new Alert(AlertType.ERROR,"NO Data To Save").showAndWait();
	    		return;
	    	}
	    	if(cmbBankName.getSelectionModel().getSelectedItem()==null)
	    	{
	    		new Alert(AlertType.ERROR,"Select Bank Name").showAndWait();
	    		cmbBankName.requestFocus();
	    		return;
	    	}
	    	if(txtTransaction.getText().equals(""))
	    	{
	    		new Alert(AlertType.ERROR,"Enter Bank Transaction No").showAndWait();
	    		txtTransaction.requestFocus();
	    		return;
	    	}
	    	if(Double.parseDouble(txtTotalCharges.getText())<=0)
	    	{
	    		new Alert(AlertType.ERROR,"No Amount to Save").showAndWait();
	    		return;
	    	}
	    	btnShow.fire();
	    	
	    	SalesmanCuttingCharges salemanCharges = new SalesmanCuttingCharges();
	    	salemanCharges.setBank(bankService.getBankByName(cmbBankName.getValue()));
	    	salemanCharges.setCuttingCharges(Float.parseFloat(txtTotalCharges.getText()));
	    	salemanCharges.setDate(LocalDate.now());
	    	salemanCharges.setSaleman(cmbSalesmanName.getValue());
	    	salemanCharges.setBankTransaction(txtTransaction.getText());
	    	for(SalesmanCuttingChargesTrPojo tr:salesmanTr)
	    	{
	    		SalesmanCuttingTransaction str = new SalesmanCuttingTransaction();
	    		str.setCharges(tr.getCharges());
	    		str.setDate(tr.getDate());
	    		str.setItemName(tr.getItemName());
	    		str.setOrder(orderService.getCuttingOrderById(tr.getId()));
	    		str.setQty(tr.getQty());
	    		str.setSalesmanCuttingChargesNo(salemanCharges);
	    		trList.add(str);
	    	}
	    	salemanCharges.setTransaction(trList);
	    	
	    	int flag=salesmanCuttingService.saveSalesmanCuttingCharges(salemanCharges);
	    	if(flag==1)
	    	{
	    		//add money to Bank Account
	    		BankTransaction btr = new BankTransaction("Cutting Charges From Salesman id"+salemanCharges.getId(),
	    				salemanCharges.getId(),
	    				salemanCharges.getCuttingCharges(), 
	    				0,
	    				salemanCharges.getBank().getId(),
	    				salemanCharges.getDate());
	    		int t = new BankTransactionServiceImpl().saveBankTransaction(btr);
	    		if(t==1)
	    		 bankService.addBankBalance(btr.getBankid(), btr.getDebit());
	    		
	    		new Alert(AlertType.INFORMATION,"Record Saved").showAndWait();
	    		showPrintBillConfirmation(salemanCharges.getId());
	    		allCuttingList.add(salemanCharges);
	    		btnClear2.fire();
	    		return;
	    	}
	    	
	    	
	    }

	    @FXML
	    void btnShowAction(ActionEvent event) {
	    	if(cmbSalesmanName.getSelectionModel().getSelectedItem()==null)
	    	{
	    		new Alert(AlertType.ERROR,"Select Salesman Name").showAndWait();
	    		cmbSalesmanName.requestFocus();
	    		return;
	    	}
	    	if(dateStart.getValue()==null)
	    	{
	    		new Alert(AlertType.ERROR,"Select Start Date").showAndWait();
	    		dateStart.requestFocus();
	    		return;
	    	}
	    	if(dateEnd.getValue()==null)
	    	{
	    		new Alert(AlertType.ERROR,"Select End Date").showAndWait();
	    		dateEnd.requestFocus();
	    		return;
	    	}
	    	salesmanTr.clear();
	    	orderList.clear();
	    	orderList.addAll(orderService.getSalesmanPeriodCuttingOrders(
	    			employeeService.getEmployeeByName(cmbSalesmanName.getValue()).getId(),
	    			dateStart.getValue(), dateEnd.getValue()));
	    	
	    	double total=0;
	    	for(CuttingOrder co:orderList)
	    	{
	    		SalesmanCuttingChargesTrPojo spojo = null;
	    		if(salesmanCuttingService.checkPaidCuttingCharges(co.getId())!=1) {
	    		for(CuttingTransaction tr:co.getTransaction())
	    		{
	    			spojo = new SalesmanCuttingChargesTrPojo();
	    			spojo.setId(co.getId());
	    			spojo.setCharges((tr.getItem().getLabourCharges()/2)*tr.getQuantity());
	    			spojo.setItemName(tr.getItem().getItemname());
	    			spojo.setId(co.getId());
	    			spojo.setDate(co.getDate());
	    			spojo.setQty(tr.getQuantity());
	    			total=total+spojo.getCharges();
	    			salesmanTr.add(spojo);
	    		}
	    		}
	    		else
	    			System.out.println("Order paid="+co.getId());
	    		
	    	}
	    	txtTotalCharges.setText(""+total);
	    	
	    }

	    private void showPrintBillConfirmation(long id) {
			Stage stage = (Stage) mainPanel.getScene().getWindow();
			Alert.AlertType type = Alert.AlertType.CONFIRMATION;
			Alert alert = new Alert(type, "");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(stage);
			alert.getDialogPane().setContentText("Do You Want Print Bill");
			alert.getDialogPane().setHeaderText("Confirmation");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				try {
					new SalesmanCuttingChargesPrint(id);
					new PrintFile().openFile("D:\\Software\\Prints\\SalesmanCuttingCharges.pdf");
				} catch (Exception e) {
					new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
				}
			} else if (result.get() == ButtonType.CANCEL) {

			}
		}
}
