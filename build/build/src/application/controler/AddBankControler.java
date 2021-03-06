package application.controler;

import java.net.URL;
import java.util.ResourceBundle;

import hibernate.entities.Bank;
import hibernate.service.service.BankService;
import hibernate.service.serviceImpl.BankServiceImpl;
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

public class AddBankControler implements Initializable {

	@FXML
	private AnchorPane mainPane;

	@FXML
	private TextField txtBalance;

	@FXML
	private TextField txtBankName;

	@FXML
	private TextField txtIfsc;

	@FXML
	private TextField txtAccountno;

	@FXML
	private TextField txtBanch;

	@FXML
	private Button btnSave;

	@FXML
	private TableView<Bank> table;

	@FXML
	private TableColumn<Bank, Integer> colSrno;

	@FXML
	private TableColumn<Bank, String> colBankname;

	@FXML
	private TableColumn<Bank, String> colIfsc;

	@FXML
	private TableColumn<Bank, String> colAccountno;
	@FXML
	private TableColumn<Bank, String> colAccountType;
	@FXML
	private TableColumn<Bank, String> colBranch;

	@FXML
	private TableColumn<Bank, Double> colBalance;

	@FXML
	private ComboBox<String> cmbAcountType;

	@FXML
	private Button btnClear;

	@FXML
	private Button btnEdit;

	@FXML
	private Button btnExit;
	private int id;
	private BankService service;
	private ObservableList<Bank> bankList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cmbAcountType.getItems().add("Saving");
		cmbAcountType.getItems().add("Current");
		cmbAcountType.getItems().add("Other");

		colSrno.setCellValueFactory(new PropertyValueFactory<Bank, Integer>("id"));
		colBankname.setCellValueFactory(new PropertyValueFactory<Bank, String>("bankname"));
		colAccountType.setCellValueFactory(new PropertyValueFactory<Bank, String>("accounttype"));
		colIfsc.setCellValueFactory(new PropertyValueFactory<Bank, String>("ifsc"));
		colBranch.setCellValueFactory(new PropertyValueFactory<Bank, String>("branch"));
		colAccountno.setCellValueFactory(new PropertyValueFactory<Bank, String>("accountno"));
		colBalance.setCellValueFactory(new PropertyValueFactory<Bank, Double>("balance"));

		service = new BankServiceImpl();
		bankList.addAll(service.getAllBanks());
		table.setItems(bankList);
		
		id= 0;
	}

	@FXML
	void clear(ActionEvent event) {
		clear();
	}

	@FXML
	void edit(ActionEvent event) {
		Bank bank = service.getBankById(table.getSelectionModel().getSelectedItem().getId());
		if(bank!=null)
		{
			txtAccountno.setText(bank.getAccountno());
			txtBalance.setText(""+bank.getBalance());
			txtBanch.setText(bank.getBranch());
			txtBankName.setText(bank.getBankname());
			txtIfsc.setText(bank.getIfsc());
			cmbAcountType.setValue(bank.getAccounttype());
			id=bank.getId();
		}
	}

	@FXML
	void exit(ActionEvent event) {
		mainPane.setVisible(false);
	}

	@FXML
	void save(ActionEvent event) {
		try {
			if (validateData() != 1) {
				return;
			}
			Bank bank = new Bank(txtBankName.getText(), cmbAcountType.getValue(), txtIfsc.getText(), txtBanch.getText(),
					txtAccountno.getText(), Double.parseDouble(txtBalance.getText()));
			bank.setId(id);
			
			int flag=service.saveBank(bank);
			if(flag==1)
			{
				new Alert(AlertType.INFORMATION,"Record Save Success!!!").showAndWait();
				bankList.add(bank);
				clear();
			}
			if(flag==2)
			{
				new Alert(AlertType.INFORMATION,"Record Update Success!!!").showAndWait();
				int index=-1;
				for(int i=0;i<bankList.size();i++)
				{
					if(bankList.get(i).getId()==bank.getId())
					{
						index=i;
						break;
					}
				}
				bankList.remove(index);
				bankList.add(index, bank);
				clear();
			}
		} catch (Exception e) {
			new Alert(AlertType.ERROR, "Error in Saving Record").showAndWait();
		}
	}

	private int validateData() {
		try {
			if (txtBankName.getText().equals("") || txtBankName.getText() == null) {
				new Alert(AlertType.ERROR, "Enter Bank Name !!!").showAndWait();
				txtBankName.requestFocus();
				return 0;
			}
			if (cmbAcountType.getValue() == null) {
				new Alert(AlertType.ERROR, "Select Account Type !!!").showAndWait();
				cmbAcountType.requestFocus();
				return 0;
			}
			if (txtIfsc.getText().equals("") || txtIfsc.getText() == null) {
				new Alert(AlertType.ERROR, "Enter Bank IFSC Code !!!").showAndWait();
				txtIfsc.requestFocus();
				return 0;
			}
			if (txtAccountno.getText().equals("") || txtAccountno.getText() == null) {
				new Alert(AlertType.ERROR, "Enter Bank Account No !!!").showAndWait();
				txtAccountno.requestFocus();
				return 0;
			}
			if (txtBanch.getText().equals("") || txtBanch.getText() == null) {
				new Alert(AlertType.ERROR, "Enter Bank Branch Name !!!").showAndWait();
				txtBanch.requestFocus();
				return 0;
			}
			if (txtBalance.getText().equals("") || txtBalance.getText() == null) {
				new Alert(AlertType.ERROR, "Enter Bank Present Balance !!!").showAndWait();
				txtBalance.requestFocus();
				return 0;
			}
			return 1;

		} catch (Exception e) {
			new Alert(AlertType.ERROR, "Error !!!").showAndWait();
			return 0;
		}
	}
	private void clear()
	{
		txtAccountno.setText("");
		txtBalance.setText("");
		txtBanch.setText("");
		txtBankName.setText("");
		cmbAcountType.getSelectionModel().clearSelection();
		id=0;
	}
}
