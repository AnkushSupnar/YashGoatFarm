package hibernate.util;

import java.util.ArrayList;
import java.util.List;

import hibernate.reportEntity.DailyItemSales;
import hibernate.reportEntity.WeeklyItemSales;
import hibernate.service.service.ItemService;
import hibernate.service.service.ItemStockService;
import hibernate.service.serviceImpl.ItemServiceImpl;
import hibernate.service.serviceImpl.ItemStockServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommonData {
	public static long previewBillNo;
	public static long previewInvoiceno;
	private static ItemService itemService = new ItemServiceImpl();
	private static ItemStockService stockService = new ItemStockServiceImpl();
	public static ObservableList<String>stockItemNames = FXCollections.observableArrayList();
	public static ObservableList<String>itemNames = FXCollections.observableArrayList();
	public static List<DailyItemSales> dailyItemSaleStickList = new ArrayList<>();
	public static List<DailyItemSales> dailyItemSaleList = new ArrayList<>();
	public static List<WeeklyItemSales> weeklyItemSaleStickList = new ArrayList<>();
	 public static List<WeeklyItemSales> weeklyItemSaleList = new ArrayList<>();;
	public static void setStockItemNames()
	{
		stockItemNames.clear();
		stockItemNames.addAll(stockService.getItemNames());
	}
	public static void setItemNames()
	{
		itemNames.clear();
		itemNames.addAll(itemService.getAllItemNames());
		
	}
	
}
