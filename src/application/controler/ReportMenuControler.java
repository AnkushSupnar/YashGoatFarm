package application.controler;

import java.net.URL;
import java.util.ResourceBundle;

import application.ViewUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
public class ReportMenuControler implements Initializable {

	 	@FXML private AnchorPane reportMenuPanel;
		@FXML private Button btnCommisionReport;
	    @FXML private Button btnSalesReport;
	    @FXML private Button btnDailySalesReport;
	    @FXML private Button btnWeeklySalesReport;
	    @FXML private Button btnMonthlySalesReport;
	    @FXML private Button btnPeriodSalesReport;	 
	    @FXML private Button brnYearSalesReport;
	    @FXML private Button btnLabourCharges;
	    

	    @FXML private Button btnCounterStock;

	    
	    @FXML private Button btnCustomerBills;
	    @FXML private Button btnUnpaidBills;
	    @FXML private Button btnViewAllStock;
	    //Bank Report Menu
	    @FXML private Button btnBankStatement;
	    @FXML private Button btnAllUnpaidBills;
	    
	    private BorderPane pane;
		private ViewUtil viewUtil;
		private Pane commisionReport,dailySalesReport,
		monthlySaleReport,periodSaleReport,weeklySaleReport,yearSaleReport,
		employeeSalesReport,customerSaleReport,unpaidBills,viewAllStock,bankStatement,
		allUnpaidBills,labourCharges,salsmanLabourCharges,stickReport,dailyItemSalesReport,
		weeklyItemSaleReport,centerPane;
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
	    	viewUtil = new ViewUtil();
	    	//pane = (BorderPane) reportMenuPanel.getParent();
	    	
		}
	    @FXML
	    void btnCommisionReportAction(ActionEvent event) {
	    	if(ViewUtil.login.getId()!=1)
	    	{
	    		new Alert(AlertType.ERROR,"You are not authorised to view this report!!!").showAndWait();
	    		return;
	    	}
	    	commisionReport = viewUtil.getPage("report/CommisionFrame");
	    	pane = (BorderPane) reportMenuPanel.getParent();
			pane.setCenter(commisionReport);
			commisionReport.setVisible(true);
	    }

	    @FXML
	    void btnSalesReportAction(ActionEvent event) {
	    	employeeSalesReport = viewUtil.getPage("report/SalesmanSalesReport");
	    	pane = (BorderPane) reportMenuPanel.getParent();
	    	pane.setCenter(employeeSalesReport);
	    	
	    }

	    @FXML
	    void btnDailySalesReportAction(ActionEvent event) {
	    	dailySalesReport = viewUtil.getPage("report/DailySaleReport");
	    	pane = (BorderPane) reportMenuPanel.getParent();
	    	pane.setCenter(dailySalesReport);
	    }

	    @FXML
	    void btnMonthlySalesReport(ActionEvent event) {
	    	if(ViewUtil.login.getId()!=1)
	    	{
	    		new Alert(AlertType.ERROR,"You are not authorised to view this report!!!").showAndWait();
	    		return;
	    	}
	    	System.out.println("monthly Sales Report");
	    	monthlySaleReport = viewUtil.getPage("report/MonthlySalesReport");
	    	pane = (BorderPane)reportMenuPanel.getParent();
	    	pane.setCenter(monthlySaleReport);
	    }

	    @FXML
	    void btnPeriodSalesReportAction(ActionEvent event) {
	    	if(ViewUtil.login.getId()!=1)
	    	{
	    		new Alert(AlertType.ERROR,"You are not authorised to view this report!!!").showAndWait();
	    		return;
	    	}
	    	periodSaleReport = viewUtil.getPage("report/PeriodSalesReport");
	    	pane = (BorderPane)reportMenuPanel.getParent();
	    	pane.setCenter(periodSaleReport);
	    }

	    @FXML
	    void btnCustomerBillsAction(ActionEvent event) {
	    	customerSaleReport = viewUtil.getPage("report/CustomerBills");
	    	pane = (BorderPane)reportMenuPanel.getParent();
	    	pane.setCenter(customerSaleReport);
	    }


	    @FXML
	    void btnWeeklySalesReportAction(ActionEvent event) {
	    	if(ViewUtil.login.getId()!=1)
	    	{
	    		new Alert(AlertType.ERROR,"You are not authorised to view this report!!!").showAndWait();
	    		return;
	    	}
	    	weeklySaleReport = viewUtil.getPage("report/WeeklySalesReport");
	    	pane = (BorderPane)reportMenuPanel.getParent();
	    	pane.setCenter(weeklySaleReport);
	    }
	    @FXML
	    void brnYearSalesReportAction(ActionEvent event) {
	    	if(ViewUtil.login.getId()!=1)
	    	{
	    		new Alert(AlertType.ERROR,"You are not authorised to view this report!!!").showAndWait();
	    		return;
	    	}
	    	yearSaleReport = viewUtil.getPage("report/YearSalesReport");
	    	pane = (BorderPane)reportMenuPanel.getParent();
	    	pane.setCenter(yearSaleReport);
	    }
	    @FXML
	    void btnUnpaidBillsAction(ActionEvent event) {
	    	unpaidBills = viewUtil.getPage("report/CustomerUnpaidBills");
	    	pane = (BorderPane)reportMenuPanel.getParent();
	    	pane.setCenter(unpaidBills);
	    }
	    @FXML
	    void btnViewAllStockAction(ActionEvent event) {
	    	if(ViewUtil.login.getId()!=1)
	    	{
	    		new Alert(AlertType.ERROR,"You are not authorised to view this report!!!").showAndWait();
	    		return;
	    	}
	    	viewAllStock = viewUtil.getPage("report/ItemStockReport");
	    	pane = (BorderPane)reportMenuPanel.getParent();
	    	pane.setCenter(viewAllStock);
	    }
	    @FXML
	    void allUnpaidBillAction(ActionEvent event) {
	    	allUnpaidBills = viewUtil.getPage("report/ShowAllUnpaidBills");
	    	pane = (BorderPane)reportMenuPanel.getParent();
	    	pane.setCenter(allUnpaidBills);
	    }
	    @FXML
	    void btnBankStatementAction(ActionEvent event) {
	    	if(ViewUtil.login.getId()!=1)
	    	{
	    		new Alert(AlertType.ERROR,"You are not authorised to view this report!!!").showAndWait();
	    		return;
	    	}
	    	bankStatement = viewUtil.getPage("report/BankStatement");
	    	pane = (BorderPane)reportMenuPanel.getParent();
	    	pane.setCenter(bankStatement);
	    }
	    @FXML
	    void btnLabourChargesAction(ActionEvent event) {
	    	if(ViewUtil.login.getId()!=1)
	    	{
	    		new Alert(AlertType.ERROR,"You are not authorised to view this report!!!").showAndWait();
	    		return;
	    	}
	    	labourCharges = viewUtil.getPage("report/LabourCommision");
	    	pane = (BorderPane)reportMenuPanel.getParent();
	    	pane.setCenter(labourCharges);
	    }
	    @FXML
	    void btnSalesmanLabourChargesAction(ActionEvent event) {
	    	if(ViewUtil.login.getId()!=1)
	    	{
	    		new Alert(AlertType.ERROR,"You are not authorised to view this report!!!").showAndWait();
	    		return;
	    	}
	    	salsmanLabourCharges =  viewUtil.getPage("report/SalesmanLabourChargesReport");
	    	pane = (BorderPane)reportMenuPanel.getParent();
	    	pane.setCenter(salsmanLabourCharges);
	    }
	    @FXML
	    void btnStickReportAction(ActionEvent event) {
	    	if(ViewUtil.login.getId()!=1)
	    	{
	    		new Alert(AlertType.ERROR,"You are not authorised to view this report!!!").showAndWait();
	    		return;
	    	}
	    	stickReport = viewUtil.getPage("report/StickReport");
	    	pane =(BorderPane) reportMenuPanel.getParent();
	    	pane.setCenter(stickReport);
	    }
	    @FXML
	    void btnDailyItemSalesReportAction(ActionEvent event) {
	    	if(ViewUtil.login.getId()!=1)
	    	{
	    		new Alert(AlertType.ERROR,"You are not authorised to view this report!!!").showAndWait();
	    		return;
	    	}
	    	dailyItemSalesReport = viewUtil.getPage("report/DailyItemSalesReport");
	    	pane =(BorderPane) reportMenuPanel.getParent();
	    	pane.setCenter(dailyItemSalesReport);
	    }
	    @FXML
	    void weeklyItemSalesReportAction(ActionEvent event) {
	    	if(ViewUtil.login.getId()!=1)
	    	{
	    		new Alert(AlertType.ERROR,"You are not authorised to view this report!!!").showAndWait();
	    		return;
	    	}
	    	weeklyItemSaleReport = viewUtil.getPage("report/WeeklyItemSalesReport");
	    	pane =(BorderPane) reportMenuPanel.getParent();
	    	pane.setCenter(weeklyItemSaleReport);
	    }

	    @FXML
	    void btnCounterStockAction(ActionEvent event) {
	    	
	    	centerPane = viewUtil.getPage("report/viewcounterstock");
	    	pane =(BorderPane) reportMenuPanel.getParent();
	    	pane.setCenter(centerPane);
	    }
	    @FXML
	    void btnPartyStatementAction(ActionEvent event) {	    	
	    	centerPane = viewUtil.getPage("report/PurchaseStatement");
	    	pane =(BorderPane) reportMenuPanel.getParent();
	    	pane.setCenter(centerPane);
	    }
	    @FXML
	    void btnCashReceiptreportAction(ActionEvent event) {
	    	centerPane = viewUtil.getPage("report/paymentrecieptreport");
	    	pane =(BorderPane) reportMenuPanel.getParent();
	    	pane.setCenter(centerPane);
	    }


}
