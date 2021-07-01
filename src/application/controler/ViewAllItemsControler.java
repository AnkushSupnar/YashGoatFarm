package application.controler;

import java.net.URL;
import java.util.ResourceBundle;

import hibernate.entities.Item;
import hibernate.service.service.ItemService;
import hibernate.service.serviceImpl.ItemServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
public class ViewAllItemsControler implements Initializable {

	 @FXML
	    private TableView<Item> table;
	    @FXML
	    private TableColumn<Item,Integer> colSrNo;
	    @FXML
	    private TableColumn<Item,String> colName;
	    @FXML
	    private TableColumn<Item,String> colHsn;
	    @FXML
	    private TableColumn<Item,String> colUnit;
	    @FXML
	    private TableColumn<Item,Double> colRate;
	    @FXML
	    private TableColumn<Item,Double> colCommision;
	    
	private ItemService service;
	private ObservableList<Item>list = FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		service = new ItemServiceImpl();
		list.addAll(service.getAllItems());
		colSrNo.setCellValueFactory(new PropertyValueFactory<>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<>("itemname"));
		colHsn.setCellValueFactory(new PropertyValueFactory<>("hsn"));
		colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
		colRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
		colCommision.setCellValueFactory(new PropertyValueFactory<>("commision"));
		table.setItems(list);

	}

}
