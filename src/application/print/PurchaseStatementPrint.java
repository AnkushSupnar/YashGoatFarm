package application.print;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PurchaseStatementPrint {
	public static String filename = "D:\\Software\\Prints\\PurchaseStatement.pdf";

	public PurchaseStatementPrint() {
		try {
			float left = 30;
			float right = 0;
			float top = 20;
			float bottom = 0;
			Document doc = new Document(PageSize.A4, left, right, top, bottom);
			PdfWriter.getInstance(doc, new FileOutputStream(filename));
			doc.open();
			addContent(doc);
			doc.close();
		} catch (Exception e) {

		}
	}

	private void addContent(Document doc) {
		try {
		
		} catch (Exception e) {
			new Alert(AlertType.ERROR,e.getMessage()).showAndWait();
		}
		
	}

	public static void main(String[] args) {

	}

}
