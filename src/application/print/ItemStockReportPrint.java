package application.print;

import java.io.FileOutputStream;
import java.time.LocalDate;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import hibernate.entities.ItemStock;
import hibernate.service.service.ItemStockService;
import hibernate.service.serviceImpl.ItemStockServiceImpl;

public class ItemStockReportPrint {
	public static String filename = "D:\\Software\\Prints\\ItemStock.pdf";
	 private static Font head = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
	 private static Font subhead = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.NORMAL);
	 float[] columnWidths = new float[]{8f,35f,8f,12f,10f,10f,10f,10f};
	 Document doc;
	 private ItemStockService service;
	public ItemStockReportPrint() {
		try {
			service = new ItemStockServiceImpl();
			float left = 0,right = 0,top = 20,bottom = 0;
	        doc = new Document(PageSize.A4 ,left,right,top,bottom);
			PdfWriter.getInstance(doc, new FileOutputStream(filename));
			doc.open();
			addData();
			doc.close();
			System.out.println("Write Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ItemStockReportPrint();
	}
	private void addData() {
		try {
			PdfPTable table = new PdfPTable(1);
			PdfPCell c1 = new PdfPCell(new Paragraph("Yash Goat Farm & Seeds",head));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBorder(PdfPCell.NO_BORDER);
			table.addCell(c1);
			
			c1 = new PdfPCell(new Paragraph("Item Stock Report",subhead));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 c1.setBorder(PdfPCell.BOTTOM);
			table.addCell(c1);
			
			c1 = new PdfPCell(new Paragraph("Report Date"+LocalDate.now(),subhead));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			 c1.setBorder(PdfPCell.BOTTOM);
			table.addCell(c1);
			
			//Stock Data Heading
			PdfPTable tr = new PdfPTable(5);
			float[] columnWidths = new float[] { 5f, 20f, 5f, 11f, 11f };
			tr.setWidths(columnWidths);
			c1 = new PdfPCell(new Paragraph("Sr.No"));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(PdfPCell.BOX);
			tr.addCell(c1);
			
			c1 = new PdfPCell(new Paragraph("Item Name"));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(PdfPCell.BOX);
			tr.addCell(c1);
			
			c1 = new PdfPCell(new Paragraph("Unit"));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(PdfPCell.BOX);
			tr.addCell(c1);
			
			c1 = new PdfPCell(new Paragraph("Quantity"));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(PdfPCell.BOX);
			tr.addCell(c1);
			
			c1 = new PdfPCell(new Paragraph("Current Stock"));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(PdfPCell.BOX);
			tr.addCell(c1);
			
			for(ItemStock stock:service.getAllItemStock())
			{
				c1 = new PdfPCell(new Paragraph(""+stock.getId()));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				 c1.setBorder(PdfPCell.BOX);
				tr.addCell(c1);
				
				c1 = new PdfPCell(new Paragraph(stock.getItemname()));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				 c1.setBorder(PdfPCell.BOX);
				tr.addCell(c1);
				
				c1 = new PdfPCell(new Paragraph(stock.getUnit()));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				 c1.setBorder(PdfPCell.BOX);
				tr.addCell(c1);
				
				c1 = new PdfPCell(new Paragraph(""+stock.getQuantity()));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				 c1.setBorder(PdfPCell.BOX);
				tr.addCell(c1);
				
				c1 = new PdfPCell(new Paragraph(""));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				 c1.setBorder(PdfPCell.BOX);
				tr.addCell(c1);
			}
			//table.addCell(tr);
			
			PdfPTable outer = new PdfPTable(1);
			c1 = new PdfPCell(table);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBorder(PdfPCell.BOX);
			outer.addCell(c1);
			//outer.addCell(tr);
			doc.add(outer);
			doc.add(tr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
