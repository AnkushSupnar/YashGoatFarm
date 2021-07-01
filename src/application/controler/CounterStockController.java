package application.controler;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import hibernate.service.service.ItemService;
import hibernate.service.serviceImpl.ItemServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CounterStockController implements Initializable {

	@FXML private AnchorPane mainPane;
    @FXML private DatePicker date;
    @FXML private TextField txtItemName;
    @FXML private TextField txtNewQty;
    @FXML private TextField PreviousQty;
    @FXML private TextField txtTotalQty;
    @FXML private Button btnAdd;
    @FXML private Button btnClear;
    @FXML private Button btnClear2;
    
    @FXML private TableView<?> table;
    @FXML private TableColumn<?, ?> colSrno;
    @FXML private TableColumn<?, ?> colItemName;
    @FXML private TableColumn<?, ?> colPreviousQty;
    @FXML private TableColumn<?, ?> colNewQty;
    @FXML private TableColumn<?, ?> colTotalQty;

    
    @FXML private TableView<?> tableOld;
    @FXML private TableColumn<?, ?> colSrNo2;
    @FXML private TableColumn<?, ?> colDate;
    
    private ItemService itemService;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	 System.out.println("initialized");
     	itemService = new ItemServiceImpl();
     	TextFields.bindAutoCompletion(txtItemName, itemService.getStockableItemNames());	
		
	}
    @FXML
    void btnAddAction(ActionEvent event) {
   
    }

    @FXML
    void btnClearAction(ActionEvent event) {

    }

    @FXML
    void btnHomeAction(ActionEvent event) {

    }

    @FXML
    void btnRemoceAction(ActionEvent event) {

    }

    @FXML
    void btnSaveAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateAction(ActionEvent event) {

    }

	

}
