package hibernate.reportEntity;

public class ItemStockReport {

	long id;
	String itemName;
	double soldQty;
	double qty;
	String unit;
	public ItemStockReport() {
		super();		
	}
	public ItemStockReport(String itemName, double soldQty, double qty, String unit) {
		super();
		this.itemName = itemName;
		this.soldQty = soldQty;
		this.qty = qty;
		this.unit = unit;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getSoldQty() {
		return soldQty;
	}
	public void setSoldQty(double soldQty) {
		this.soldQty = soldQty;
	}
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Override
	public String toString() {
		return "ItemStockReport [id=" + id + ", itemName=" + itemName + ", soldQty=" + soldQty + ", qty=" + qty
				+ ", unit=" + unit + "]";
	}
	
}
