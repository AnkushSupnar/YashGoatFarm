package application.controler;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import hibernate.entities.Bill;
import hibernate.entities.Item;
import hibernate.entities.Transaction;
import hibernate.reportEntity.DailyItemSales;
import hibernate.service.service.BillService;
import hibernate.service.service.ItemService;
import hibernate.service.serviceImpl.BillServiceImpl;
import hibernate.service.serviceImpl.ItemServiceImpl;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.Alert.AlertType;
public class DailyItemSalesReportController implements Initializable{
		@FXML private DatePicker date;
	    @FXML private Button btnShow;
	    @FXML private TextField txtItemName;
	    @FXML private Button btnShowAll;
	    @FXML private Button btnClear;
	    @FXML private TableView<DailyItemSales> table;
	    @FXML private TableColumn<DailyItemSales,Integer> colSrNo;
 	    @FXML private TableColumn<DailyItemSales,Long>    colBillNo;
	    @FXML private TableColumn<DailyItemSales,String> colItemName;
	    @FXML private TableColumn<DailyItemSales,Float> colQty;
	    @FXML private TableColumn<DailyItemSales,String> colUnit;
	    @FXML private TableColumn<DailyItemSales,Float> colRate;
	    @FXML private TableColumn<DailyItemSales,Float> colAmount;
	    @FXML private Button btnShowChart;

	    private BillService billService;
	    private ItemService itemService;
	    private ObservableList<DailyItemSales>list = FXCollections.observableArrayList();
	    private List<Bill>billList = new ArrayList<Bill>();
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			date.setValue(LocalDate.now());
			billService = new BillServiceImpl();
			itemService = new ItemServiceImpl();
			new AutoCompletionTextFieldBinding<String>(txtItemName, SuggestionProvider.create(itemService.getAllItemNames()));
			colSrNo.setCellValueFactory(new PropertyValueFactory<>("srno"));
			colBillNo.setCellValueFactory(new PropertyValueFactory<>("billNo"));
			colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
			colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
			colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
			colRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
			colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
			table.setItems(list);
			CommonData.dailyItemSaleList.clear();
			
		}
		 @FXML
		    void btnClearAction(ActionEvent event) {
			 date.setValue(null);
			 txtItemName.setText("");
			 list.clear();
			 billList.clear();	
			 CommonData.dailyItemSaleList.clear();
		    }

		    @FXML
		    void btnShowAction(ActionEvent event) {
		    	//show date wise and Item wise Sales Report
		    	if(date.getValue()==null)
		    	{
		    		new Alert(AlertType.ERROR,"Select Date").showAndWait();
		    		date.requestFocus();
		    		return;
		    	}
		    	if(txtItemName.getText().equals(""))
		    	{
		    		new Alert(AlertType.ERROR,"Select Item Name").showAndWait();
		    		txtItemName.requestFocus();
		    		return;
		    	}
		    	billList.clear();
		    	billList.addAll(billService.getDateWiseBill(date.getValue()));
		    	if(billList.isEmpty())
		    	{
		    		new Alert(AlertType.ERROR,"No Data For this date to show").show();
		    		return;
		    	}
		    	 list.clear();
		    	billListToList(txtItemName.getText());
		    }

		    @FXML
		    void btnShowAllAction(ActionEvent event) {
		    	if(date.getValue()==null)
		    	{
		    		new Alert(AlertType.ERROR,"Select Date").showAndWait();
		    		date.requestFocus();
		    		return;
		    	}
		    	billList.clear();
		    	list.clear();
		    	CommonData.dailyItemSaleList.clear();
		    	CommonData.dailyItemSaleStickList.clear();
		    	billList.addAll(billService.getDateWiseBill(date.getValue()));
		    	showAll();
		    }
		    @FXML
		    void btnShowChartAction(ActionEvent event) {
		    	if(CommonData.dailyItemSaleList.isEmpty() &&CommonData.dailyItemSaleStickList.isEmpty())
		    	{
		    		new Alert(AlertType.ERROR,"NO Data to Show in Chart").showAndWait();
		    		return;
		    	}
		    	try {
		    	Stage stage = new Stage();
				Parent root = FXMLLoader.load(Main.class.getResource("/application/view/report/charts/DailyItemSalesChart.fxml"));
				stage.setScene(new Scene(root));
				stage.setTitle("My modal window");
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(((Node) event.getSource()).getScene().getWindow());
				stage.show();
				stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent e) {
						
					}
				});
		    	}catch(Exception e)
		    	{
		    		e.printStackTrace();
		    	}

		    	
		    }

		    void billListToList(String itemName)
		    {
		    	int sr=0;
		    	float qty=0,amount=0;
		    	for(Bill bill:billList)
		    	{
		    		for(Transaction tr:bill.getTransaction())
		    		{
		    			if(tr.getItemname().equals(itemName))
		    			{
		    				System.out.println("Found Item");
		    				list.add(new DailyItemSales(++sr, bill.getBillno(),tr.getItemname(), tr.getQuantity(),tr.getUnit(), tr.getRate(), tr.getAmount()));
		    				qty=qty+tr.getQuantity();
		    				amount=amount+tr.getAmount();
		    			}
		    		}
		    		
		    	}
		    	
		    	list.add(null);
	    		list.add(null);
	    		list.add(new DailyItemSales(0, 0, "Total", qty, "", 0, amount));
		    }
		    private void showAll()
		    {
		    	//all Items List
		    	list.clear();
		    	int sr=0;
		    	
		    	DailyItemSales d = null;
		    	for(Item item:itemService.getAllItems())
		    	{
		    		d=new DailyItemSales(++sr, 0, item.getItemname(), getItemSale(item.getItemname()), item.getUnit(), item.getRate(),item.getRate()*getItemSale(item.getItemname())); 
		    		list.add(d);
		    		if(item.getLabourCharges()>0)
		    		{
		    			//for Stick
		    			CommonData.dailyItemSaleStickList.add(d);
		    		}
		    		else
		    		{
		    			//for other
		    			CommonData.dailyItemSaleList.add(d);
		    		}
		    		d=null;
		    	}
		    	
		    	
		    }
		    private float getItemSale(String name)
		    {
		    	try {
		    		float qty=0;
					for(Bill bill:billList)
					{
						for(Transaction tr:bill.getTransaction())
						{
							if(tr.getItemname().equals(name))
							{
								qty=qty+tr.getQuantity();
							}
						}
					}
					return qty;
				} catch (Exception e) {
					return 0;
				}
		    }
}
