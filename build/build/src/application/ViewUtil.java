package application;

import java.io.IOException;
import java.net.URL;

import hibernate.entities.Login;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewUtil 
{
	private Pane view;
	public static Login login; 

	public void changeWindow(ActionEvent event, String filename) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("/application/view/" + filename + ".fxml"));
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(new Scene(parent));
	}

	public Pane getPage(String fileName)
	{
		System.out.println("got to open "+fileName);
		try {                                  
			URL fileUrl = Main.class.getResource("/application/view/" + fileName + ".fxml");
			
			if(fileUrl==null)
			{
				throw new java.io.FileNotFoundException("File Not Found!!!");
			}
			new FXMLLoader();
			view = FXMLLoader.load(fileUrl);
			
		} catch (Exception e) {
			System.out.println("No Page "+fileName+" please Check fxmlLoader");
			new Alert(AlertType.ERROR,"No Page "+fileName+" please Check fxmlLoader").showAndWait();
			e.printStackTrace();
		}
		return view;
	} 

}
