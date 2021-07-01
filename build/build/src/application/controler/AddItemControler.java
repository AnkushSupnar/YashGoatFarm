package application.controler;
import java.net.URL;
import java.util.ResourceBundle;

import hibernate.entities.Item;
import hibernate.service.service.ItemService;
import hibernate.service.serviceImpl.ItemServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
public class AddItemControler implements Initializable {

	@FXML
    private AnchorPane mainPane;

	@FXML
    private TextField txtItemName;
    @FXML
    private ComboBox<String> cmbUnit;
    @FXML
    private TextField txtRate;
    @FXML
    private TextField txtCommision;
    @FXML
    private Button btnSave;
    @FXML
    private TableView<Item> table;
    @FXML
    private TableColumn<Item, Integer> colSrNo;
    @FXML
    private TableColumn<Item, String> colItemName;
    @FXML
    private TableColumn<Item, String> colUnit;
    @FXML
    private TableColumn<Item, Double> colRate;
    @FXML
    private TableColumn<Item, Double> colCommision;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnExit;
    private ItemService service;
    private ObservableList<Item> itemList = FXCollections.observableArrayList();
    private int id;
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new ItemServiceImpl();
		colSrNo.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
		colItemName.setCellValueFactory(new PropertyValueFactory<Item, String>("itemname"));
		colUnit.setCellValueFactory(new PropertyValueFactory<Item, String>("unit"));
		colRate.setCellValueFactory(new PropertyValueFactory<Item, Double>("rate"));
		colCommision.setCellValueFactory(new PropertyValueFactory<Item,Double>("commision"));
		itemList.addAll(service.getAllItems());
		table.setItems(itemList);
		
		cmbUnit.getItems().add("Nos");
		cmbUnit.getItems().add("KG");
	
		txtRate.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (newValue.matches("\\d.*")) return;
		    txtRate.setText(newValue.replaceAll("[^\\d]", ""));
		});
		txtCommision.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (newValue.matches("\\d.*")) return;
		    txtCommision.setText(newValue.replaceAll("[^\\d]", ""));
		});
		
		id=0;
	}
    @FXML
    void clear(ActionEvent event) {
    	clear();
    }

    @FXML
    void exit(ActionEvent event) {
    	mainPane.setVisible(false);
    }

    @FXML
    void save(ActionEvent event) {
    	try {
			if(validateData()!=1)
			{
				return;
			}
			Item item = new Item(txtItemName.getText(), Double.parseDouble(txtRate.getText()), cmbUnit.getValue(), Double.parseDouble(txtCommision.getText()));
			item.setId(id);
			if(item.getId()==0 && service.getItemByName(item.getItemname())!=null)
			{
				new Alert(AlertType.ERROR,"Item Name Already Exist Please Select Another").showAndWait();
				txtItemName.requestFocus();
				return;
			}
			int flag = service.saveItem(item);
			if(flag==1)
			{
				new Alert(AlertType.INFORMATION,"Item Saved Success").showAndWait();
				clear();
				itemList.add(item);
				
			}
			if(flag==2)
			{
				new Alert(AlertType.INFORMATION,"Item Updated Success").showAndWait();
				clear();
				int i=-1;
				for(i=0;i<itemList.size();i++)
				{
					if(itemList.get(i).getItemname().equals(item.getItemname()))
					{
						break;
					}
				}
				itemList.remove(i);
				itemList.add(i, item);
				
			}
		} catch (Exception e) {
			new Alert(AlertType.ERROR,"Error in Saving Item").showAndWait();
			
		}
    }

    @FXML
    void update(ActionEvent event) {
    	Item item = table.getSelectionModel().getSelectedItem();
    	if(item!=null)
    	{
    		id= item.getId();
    		txtItemName.setText(item.getItemname());
    		cmbUnit.setValue(item.getUnit());
    		txtRate.setText(""+item.getRate());
    		txtCommision.setText(""+item.getCommision());
    	}
    
    	
    }
    private int validateData()
    {
    	try {
    		if(txtItemName.getText().equals("")|| txtItemName.getText()==null)
    		{
    			new Alert(AlertType.ERROR,"Enter Item Name !!!").showAndWait();
    			txtItemName.requestFocus();
    			return 0;
    		}
    		if(cmbUnit.getValue()==null)
    		{
    			new Alert(AlertType.ERROR,"Select Item Quantity Unit !!!").showAndWait();
    			cmbUnit.requestFocus();
    			return 0;
    		}
    		if(txtRate.getText().equals("")|| txtRate.getText()==null)
    		{
    			new Alert(AlertType.ERROR,"Enter Item Rate !!!").showAndWait();
    			txtRate.requestFocus();
    			return 0;
    		}
    		if(txtCommision.getText().equals("")|| txtCommision.getText()==null)
    		{
    			new Alert(AlertType.ERROR,"Enter Item Salesman Commision in Rupees !!!").showAndWait();
    			txtCommision.requestFocus();
    			return 0;
    		}
    		return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
    }
    private void clear()
    {
    	txtItemName.setText("");
    	cmbUnit.getSelectionModel().clearSelection();
    	txtRate.setText("");
    	txtCommision.setText("");
    	id=0;
    }
   
}

