package application.controler.report;
import java.net.URL;
import java.util.ResourceBundle;

import hibernate.entities.CounterStockData;
import hibernate.service.service.CounterStockDataService;
import hibernate.service.serviceImpl.CounterStockDataServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ViewCounterStockController implements Initializable {
	
		@FXML private AnchorPane mainPane;
		@FXML private Button btnPrint;
 	    @FXML private TableView<CounterStockData> table;
	    @FXML private TableColumn<CounterStockData,Integer> colSrNo;
	    @FXML private TableColumn<CounterStockData,String> colItemName;
 	    @FXML private TableColumn<CounterStockData,Double> colQuantity;
	    @FXML private TableColumn<CounterStockData,String> colUnit;
	    private CounterStockDataService service;
	    private ObservableList<CounterStockData>stockList = FXCollections.observableArrayList();
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			service = new CounterStockDataServiceImpl();
			colSrNo.setCellValueFactory(new PropertyValueFactory<>("id"));
			colItemName.setCellValueFactory(new PropertyValueFactory<>("itemname"));
			colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
			colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
			stockList.addAll(service.getAllCounterStockData());
			table.setItems(stockList);
			
		}
}
