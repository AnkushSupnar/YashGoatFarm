package application.controler.transaction;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;
import application.guiUtil.AlertNotification;
import hibernate.entities.CounterStock;
import hibernate.entities.CounterStockData;
import hibernate.entities.CounterStockTransaction;
import hibernate.entities.ItemStock;
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
	    private CounterStock oldCounterStock;
	    
	    ObservableList<CounterStockTransaction>trList = FXCollections.observableArrayList();
	    private ObservableList<CounterStock> oldCounterStockList = FXCollections.observableArrayList();
		@Override
		public void initialize(URL location, ResourceBundle resources) {
	    	itemService = new ItemServiceImpl();
	    	itemStockService = new ItemStockServiceImpl();
	    	counterStockDataService = new CounterStockDataServiceImpl();
	    	counterStockService = new CounterStockServiceImpl();
	    	notification = new AlertNotification();
	    	oldCounterStock = null;
	    	date.setValue(LocalDate.now());
	    	id=0;
	    	colSrNo.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	colItemName.setCellValueFactory(new PropertyValueFactory<>("itemname"));
	    	colOldQty.setCellValueFactory(new PropertyValueFactory<>("oldqty"));
	    	colNewQty.setCellValueFactory(new PropertyValueFactory<>("newqty"));
	    	colTotalQty.setCellValueFactory(new PropertyValueFactory<>("totalqty"));
	    	table.setItems(trList);
	    	
	    	colId.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
	    	oldCounterStockList.addAll(counterStockService.getAllCounterStock());
	    	tblOld.setItems(oldCounterStockList);
	    	
	    	TextFields.bindAutoCompletion(txtItemName, itemService.getStockableItemNames());
	    	txtItemName.setOnAction(e->{
	    		if(!txtItemName.getText().equals(""))
	    		{
	    			
	    			if(itemStockService.getItemStock(txtItemName.getText())>=0)
	    			{
	    				if(id==0) {
	    				lblAvailableQty.setText(""+itemStockService.getItemStock(txtItemName.getText()));
	    				txtOldQty.setText(""+counterStockDataService.getCounterItemStock(txtItemName.getText()));
	    				txtNewQty.requestFocus();
	    				}
	    				else {
	    					if(oldCounterStock!=null)
	    					{
	    						double oldstock=0,oldqty=0;
	    						for(CounterStockTransaction tr:oldCounterStock.getTransaction())
	    						{
	    							if(tr.getItemname().equals(txtItemName.getText().trim()))
	    							{
	    								oldqty=tr.getNewqty();	
	    								
	    								break;
	    							}
	    						}
	    						lblAvailableQty.setText(""+(itemStockService.getItemStock(txtItemName.getText())+oldqty));
	    						txtOldQty.setText(""+(counterStockDataService.getCounterItemStock(txtItemName.getText())-oldqty));
	    					}
	    					
	    				}
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
			edit();
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
	    	oldCounterStock=null;
			
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
			CounterStock oldStock = null;
			CounterStock counterStock = new CounterStock();
			counterStock.setDate(date.getValue());
			if(id!=0) {
				counterStock.setId(id);
				oldStock = counterStockService.getCounterStockById(id);
				for(CounterStockTransaction oldTr:oldStock.getTransaction())
				{
					//add in stock
					ItemStock godown= itemStockService.getItemStockByItemName(oldTr.getItemname());
					godown.setQuantity(oldTr.getNewqty());
					itemStockService.saveItemStock(godown);					
					godown=null;
					//
				}
			}
			//counterStock.setId(id);
			for(int i=0;i<trList.size();i++)
			{
				trList.get(i).setCounterstock(counterStock);
				trList.get(i).setId(0);
			}
			counterStock.setTransaction(trList);
			int flag = counterStockService.saveCounterStock(counterStock);
			if(flag==1)
			{
				
				//reduce from godown stock
				for(CounterStockTransaction tr:counterStock.getTransaction())
				{
					itemStockService.reduceItemStock(tr.getItemname(), tr.getNewqty());
					CounterStockData counterStockData = new CounterStockData(
							tr.getItemname(),
							tr.getNewqty(),
							itemService.getItemByName(tr.getItemname()).getUnit());
					counterStockDataService.saveCounterStockdata(counterStockData);
					
					
				}
				notification.showSuccessMessage("Data Save Success");	
				cancel();
			}
			else if(flag==2)
			{
				//reduce from godown stock
				for(CounterStockTransaction tr:counterStock.getTransaction())
				{
					itemStockService.reduceItemStock(tr.getItemname(), tr.getNewqty());
					CounterStockData counterStockData = new CounterStockData(
							tr.getItemname(),
							tr.getNewqty(),
							itemService.getItemByName(tr.getItemname()).getUnit());
					counterStockDataService.saveCounterStockdata(counterStockData);									
				}
				// reduce old Stock from CounterStockData
				for(CounterStockTransaction oldTr:oldStock.getTransaction())
				{
					double nqty = oldTr.getNewqty();
					counterStockDataService.updateQuantity(oldTr.getItemname(),(nqty*=-1));
				}
				notification.showSuccessMessage("Data update Success");
				
				
				
			}
			else
			{
				notification.showErrorMessage("Error in Saving Record");
			}
			
		} catch (Exception e) {
			notification.showErrorMessage("Error in Saving Data");
		}
			
		}
	    private void edit() {
	    	try {
				if(tblOld.getSelectionModel().getSelectedItem()==null)
				{
					return;
				}
				CounterStock stock = tblOld.getSelectionModel().getSelectedItem();
				if(stock==null)
				{
					return;
				}
				date.setValue(stock.getDate());
				trList.clear();
				trList.addAll(stock.getTransaction());
				validateTrList();
				id=stock.getId();
				oldCounterStock = counterStockService.getCounterStockById(id);
			} catch (Exception e) {
				notification.showErrorMessage("Error In Editing Counter Stock "+e.getMessage());
			}
			
		}
}
