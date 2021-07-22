 package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

//For Yash
public class Main extends Application {
	//String pagename="LoginFrame";
	//String pagename="transaction/PurchaseInviceFrame";
	//String pagename="transaction/BillingFrame";
	//String pagename="home/Dashboard";
	//String pagename="home/CompanyDetails";
	//String pagename="transaction/TransactionMenu";
	//String pagename="report/ReportMenu";
	//String pagename="report/DailySaleReport";
	//String pagename="report/WeeklySalesReport";
	//String pagename="report/MonthlySalesReport";
	//String pagename="report/PeriodSalesReport";
	//String pagename="report/YearSalesReport";
	//String pagename="report/SalesmanSalesReport";
	//String pagename="report/CustomerBills";
	//String pagename="report/CustomerUnpaidBills";
	//String pagename="transaction/CustomerPayment";
	//String pagename="report/ViewAllEmployee";
	String pagename="report/BankStatement";
	//String pagename="report/PurchaseStatement";
	//String pagename="report/CommisionFrame";
	//String pagename="transaction/CuttingOrderFrame2";
	//String pagename="report/ItemStockReport";
	//String pagename="report/LabourCommision";
	//String pagename="transaction/counterstock";
	//String pagename="transaction/paymentreciept";
	//String pagename="transaction/advancepayment";
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/view/"+pagename+".fxml"));
	 		Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		//	Image icon = new Image("D:\\Software\\Images\\350.jpg");
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("image.jpg")));
			primaryStage.setScene(scene);
			primaryStage.setTitle("Yash Goat Farm Management System");
			primaryStage.show();


//			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
