package application.controler;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import hibernate.entities.Bill;
import hibernate.entities.Item;
import hibernate.entities.Transaction;
import hibernate.reportEntity.WeeklyItemSales;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
public class WeeklyItemSalesReport implements Initializable {
	@FXML private DatePicker date;
    @FXML private TextField txtItemName;
    @FXML private Button btnShow;
    @FXML private Button btnShowAll;
    @FXML private Button btnShowChart;
    @FXML private Button btnClear;
    @FXML private TableView<WeeklyItemSales> table;
    @FXML private TableColumn<WeeklyItemSales,Integer> colSrNo;
    @FXML private TableColumn<WeeklyItemSales,Long>    colDate;
    @FXML private TableColumn<WeeklyItemSales,String>  colBillNo;
    @FXML private TableColumn<WeeklyItemSales,Float>  colItemName;
    @FXML private TableColumn<WeeklyItemSales,String>  colQty;
    @FXML private TableColumn<?, ?> colUnit;
    @FXML private TableColumn<WeeklyItemSales,Float>  colRate;
    @FXML private TableColumn<WeeklyItemSales,Float>  colAmount;
    
    private BillService billService;
    private ItemService itemService;
    private ObservableList<WeeklyItemSales>list = FXCollections.observableArrayList();
    private List<Bill>billList = new ArrayList<Bill>();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		date.setValue(LocalDate.now());
		billService = new BillServiceImpl();
		itemService = new ItemServiceImpl();
		new AutoCompletionTextFieldBinding<String>(txtItemName, SuggestionProvider.create(itemService.getAllItemNames()));
		colSrNo.setCellValueFactory(new PropertyValueFactory<>("srno"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		colBillNo.setCellValueFactory(new PropertyValueFactory<>("billNo"));
		colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
		colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
		colRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
		colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		table.setItems(list);
	}
	 @FXML
	    void btnClearAction(ActionEvent event) {
		 date.setValue(null);
		 txtItemName.setText("");
		 list.clear();
		 billList.clear();
	    }

	    @FXML
	    void btnShowAction(ActionEvent event) {
	    	if(date.getValue()==null)
	    	{
	    		new Alert(AlertType.ERROR,"Select Date").showAndWait();
	    		date.requestFocus();
	    		return;
	    	}
	    	if(txtItemName.getText().equals(""))
	    	{
	    		new Alert(AlertType.ERROR,"Enter Item Name").showAndWait();
	    		txtItemName.requestFocus();
	    		return;
	    	}
	    	list.clear();
	    	billList.clear();
	    	CommonData.weeklyItemSaleStickList.clear();
	    	billList.addAll(billService.getPeriodWiseBills(date.getValue().with(DayOfWeek.MONDAY),date.getValue().with(DayOfWeek.SUNDAY)));
	    	int sr=0;
	    	float qty=0,amount=0;
	    	if(billList.isEmpty())
	    	{
	    		new Alert(AlertType.ERROR,"No Data to show").showAndWait();
	    		return;
	    	}
	    	for(Bill bill:billList)
	    	{
	    		for(Transaction tr:bill.getTransaction())
	    		{
	    			if(tr.getItemname().equals(txtItemName.getText()))
	    			{
	    				list.add(new WeeklyItemSales(++sr, bill.getDate(),bill.getBillno(), tr.getItemname(), tr.getQuantity(), tr.getUnit(),tr.getRate(), tr.getAmount()));
	    				qty+=tr.getQuantity();
	    				amount+=tr.getAmount();
	    			}
	    		}
	    	}
	    	list.add(null);
	    	list.add(null);
	    	list.add(null);
	    	list.add(null);
	    	list.add(new WeeklyItemSales(0, null, 0,"Total", qty, "", 0,amount));
	    }

	    @FXML
	    void btnShowAllAction(ActionEvent event) {
	    	if(date.getValue()==null)
	    	{
	    		new Alert(AlertType.ERROR,"Enter Date").showAndWait();
	    		date.requestFocus();
	    		return;
	    	}
	    	list.clear();
	    	billList.clear();
	    	CommonData.weeklyItemSaleStickList.clear();
	    	billList.addAll(billService.getPeriodWiseBills(date.getValue().with(DayOfWeek.MONDAY),date.getValue().with(DayOfWeek.SUNDAY)));
	    	if(billList.isEmpty())
	    	{
	    		new Alert(AlertType.ERROR,"No Data to show").showAndWait();
	    		return;
	    	}
	    	int sr=0;
	    	WeeklyItemSales week =null;
	    	for(Item item:itemService.getAllItems())
	    	{
	    		week = new WeeklyItemSales(++sr,date.getValue() , 0, item.getItemname(),getItemAllSale(item.getItemname()),item.getUnit(), item.getRate(),item.getRate()*getItemAllSale(item.getItemname()));
	    		
	    		list.add(week);
	    		if(item.getLabourCharges()>0)
	    		{
	    			CommonData.weeklyItemSaleStickList.add(week);
	    		}
	    		else
	    		{
	    			CommonData.weeklyItemSaleList.add(week);
	    		}
	    	}
	    	
	    }

	    @FXML
	    void btnShowChartAction(ActionEvent event) {
	    	if(CommonData.weeklyItemSaleStickList.isEmpty())
	    	{
	    		new Alert(AlertType.ERROR,"NO Data to Show in Chart").showAndWait();
	    		return;
	    	}
	    	try {
	    	Stage stage = new Stage();
			Parent root = FXMLLoader.load(Main.class.getResource("/application/view/report/charts/WeeklyItemSalesReport.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("Weekly Items Sales Report Charts");
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
	    private float getItemAllSale(String itemName)
	    {
	    	try {
	    		float qty=0;
				for(Bill bill:billList)
				{
					for(Transaction tr:bill.getTransaction())
					{
						if(tr.getItemname().equals(itemName))
						{
							qty+=tr.getQuantity();
						}
					}
				}
				return qty;
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
	    }

}
