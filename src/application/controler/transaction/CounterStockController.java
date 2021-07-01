package application.controler.transaction;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;
import application.guiUtil.AlertNotification;
import hibernate.entities.CounterStock;
import hibernate.entities.CounterStockTransaction;
import hibernate.service.service.CounterStockDataService;
import hibernate.service.service.CounterStockService;
import hibernate.service.service.ItemService;
import hibernate.service.service.ItemStockService;
import hibernate.service.serviceImpl.CounterStockDataServiceImpl;
import hibernate.service.serviceImpl.CounterStockServiceImpl;
import hibernate.service.serviceImpl.ItemServiceImpl;
import hibernate.service.serviceImpl.ItemStockServiceImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
public class CounterStockController implements Initializable {

	 @FXML private DatePicker date;
	    @FXML private TextField txtItemName;
	    @FXML private TextField txtNewQty;
	    @FXML private Label lblAvailableQty;
	    @FXML private TextField txtOldQty;
	    @FXML private TextField txtTotalQty;
 	    @FXML private Button btnClear;
 	    @FXML private Button btnCancel;
    	@FXML private Button btnAdd;

 	   
 	    @FXML private TableView<CounterStockTransaction> table;
	    @FXML private TableColumn<CounterStockTransaction, Long> colSrNo;
	    @FXML private TableColumn<CounterStockTransaction,String> colItemName;
	    @FXML private TableColumn<CounterStockTransaction,Double> colOldQty;
	    @FXML private TableColumn<CounterStockTransaction,Double> colNewQty;
	    @FXML private TableColumn<CounterStockTransaction,Double> colTotalQty;
	     
	    @FXML private TableView<CounterStock> tblOld;
 	    @FXML private TableColumn<CounterStock,Long> colId;
	    @FXML private TableColumn<CounterStock,LocalDate> colDate;
	    
	    private ItemService itemService;
	    private ItemStockService itemStockService;
	    private CounterStockDataService counterStockDataService; 
	    private CounterStockService counterStockService;
	    private AlertNotification notification;
	    long id;
	    ObservableList<CounterStockTransaction>trList = FXCollections.observableArrayList();
		@Override
		public void initialize(URL location, ResourceBundle resources) {
	    	itemService = new ItemServiceImpl();
	    	itemStockService = new ItemStockServiceImpl();
	    	counterStockDataService = new CounterStockDataServiceImpl();
	    	counterStockService = new CounterStockServiceImpl();
	    	notification = new AlertNotification();
	    	id=0;
	    	colSrNo.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	colItemName.setCellValueFactory(new PropertyValueFactory<>("itemname"));
	    	colOldQty.setCellValueFactory(new PropertyValueFactory<>("oldqty"));
	    	colNewQty.setCellValueFactory(new PropertyValueFactory<>("newqty"));
	    	colTotalQty.setCellValueFactory(new PropertyValueFactory<>("totalqty"));
	    	table.setItems(trList);
	    	
	    	
	    	TextFields.bindAutoCompletion(txtItemName, itemService.getStockableItemNames());
	    	txtItemName.setOnAction(e->{
	    		if(!txtItemName.getText().equals(""))
	    		{
	    			
	    			if(itemStockService.getItemStock(txtItemName.getText())>=0)
	    			{
	    				lblAvailableQty.setText(""+itemStockService.getItemStock(txtItemName.getText()));
	    				txtOldQty.setText(""+counterStockDataService.getCounterItemStock(txtItemName.getText()));
	    				txtNewQty.requestFocus();
	    			}
	    		}
	    	});
	    	txtNewQty.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
						txtNewQty.setText(oldValue);
					}
				}
			});
	    	txtNewQty.setOnAction(e->{
	    		if(!txtNewQty.getText().equals(""))
	    		{
	    			if(Double.parseDouble(txtNewQty.getText())>Double.parseDouble(lblAvailableQty.getText())) {
	    				notification.showErrorMessage("Quantity Not Available in Godown Stock");
	    				return;
	    			}else {
	    			txtTotalQty.setText(""+(
	    					Double.parseDouble(txtNewQty.getText())+
	    					Double.parseDouble(txtOldQty.getText())
	    					));
	    				btnAdd.requestFocus();
	    			}
	    		}
	    	});
		}
		

	    @FXML
	    void btnAddAction(ActionEvent event) {
	    		if(txtItemName.getText().equals(""))
	    		{
	    			notification.showErrorMessage("Select Item Name");
	    			txtItemName.requestFocus();
	    			return;
	    		}
	    		if(Double.parseDouble(txtTotalQty.getText())<=0)
	    		{
	    			notification.showErrorMessage("Unable to add Given Quantity");
	    			txtNewQty.requestFocus();
	    			return;
	    		}
	    		
	    		txtTotalQty.setText(""+(
    					Double.parseDouble(txtNewQty.getText())+
    					Double.parseDouble(txtOldQty.getText())
    					));
	    		//add in trList
	    		if(trList.size()==0)
	    		{
	    			//first Element
	    			trList.add(new CounterStockTransaction(txtItemName.getText(),Double.parseDouble(txtOldQty.getText()),Double.parseDouble(txtNewQty.getText()),Double.parseDouble(txtTotalQty.getText()), null));
	    			validateTrList();
	    			clear();
	    		}else{
	    			int flag=-1;
	    			for(int i=0;i<trList.size();i++)
	    			{
	    				if(trList.get(i).getItemname().equals(txtItemName.getText()))
	    				{
	    					flag=i;
	    					System.out.println("Found");
	    					break;
	    				}
	    			}
	    			if(flag==-1)
	    			{
	    				//new Item
	    				trList.add(new CounterStockTransaction(txtItemName.getText(),Double.parseDouble(txtOldQty.getText()),Double.parseDouble(txtNewQty.getText()),Double.parseDouble(txtTotalQty.getText()), null));
	    				validateTrList();
	    				clear();
	    			}
	    			else
	    			{
	    				
	    				//update getted id
	    				CounterStockTransaction ctr = new CounterStockTransaction(txtItemName.getText(),
	    						trList.get(flag).getOldqty(),
	    						trList.get(flag).getNewqty()+Double.parseDouble(txtNewQty.getText()),
	    						trList.get(flag).getTotalqty()+Double.parseDouble(txtNewQty.getText()),
	    						null);
	    				trList.remove(flag);
	    				trList.add(flag,ctr);	    				
	    				validateTrList();
	    				clear();
	    			}
	    		}	    		
	    }

	    @FXML
	    void btnCancelAction(ActionEvent event) {
	    	cancel();
	    }
	    
		@FXML
	    void btnClearAction(ActionEvent event) {
	    	clear();

	    }

	    private void clear() {
			txtItemName.setText("");
			txtNewQty.setText("");
			txtOldQty.setText(""+0.0);
			txtTotalQty.setText(""+0.0);
			lblAvailableQty.setText("");
		}
		@FXML
	    void btnEditAction(ActionEvent event) {

	    }

	    @FXML
	    void btnHomeAction(ActionEvent event) {
	    	
	    }

	    @FXML
	    void btnPrintAction(ActionEvent event) {

	    }

	    @FXML
	    void btnRemoveAction(ActionEvent event) {
	    	remove();
	    }

	   	@FXML
	    void btnSaveAction(ActionEvent event) {
	   		save();
	    }
		void validateTrList()
	    {
	    	int id=0;
	    	for(int i=0;i<trList.size();i++)
	    	{
	    		trList.get(i).setId(++id);
	    	}
	    }
	    private void cancel() {
	    	trList.clear();
	    	clear();
	    	date.setValue(LocalDate.now());
	    	id=0;
			
		}
	    private void remove() {
	    	try {
				if(table.getSelectionModel().getSelectedItem()!=null)
				{
					trList.remove(table.getSelectionModel().getSelectedIndex());
					validateTrList();
				}
	    		
			} catch (Exception e) {
				notification.showErrorMessage("Error in Removing Item");
			}
			
		}
	    private void save() {
		try {
			if(trList.size()==0)
			{
				notification.showErrorMessage("NO Data to save");
				return;
			}
			if(date.getValue()==null)
			{
				notification.showErrorMessage("Select Date");
				date.requestFocus();
				return;
			}
			CounterStock counterStock = new CounterStock();
			counterStock.setDate(date.getValue());
			counterStock.setId(id);
			trList.forEach(f->f.setCounterstock(counterStock));
			counterStock.setTransaction(trList);
			int flag = counterStockService.saveCounterStock(counterStock);
			if(flag!=0)
			{
				notification.showSuccessMessage("Data Save Success");
				cancel();
			}
			
		} catch (Exception e) {
			notification.showErrorMessage("Error in Saving Data");
		}
			
		}
}
