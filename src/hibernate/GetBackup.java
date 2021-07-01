package hibernate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hibernate.entities.Bank;
import hibernate.entities.BankTransaction;
import hibernate.entities.Bill;
import hibernate.entities.Customer;
import hibernate.entities.Employee;
import hibernate.entities.Item;
import hibernate.entities.Login;
import hibernate.entities.PurchaseParty;
import hibernate.entities.Transaction;
import hibernate.service.service.BankService;
import hibernate.service.service.BankTransactionService;
import hibernate.service.service.BillService;
import hibernate.service.service.CustomerService;
import hibernate.service.service.EmployeeService;
import hibernate.service.service.ItemService;
import hibernate.service.service.LoginService;
import hibernate.service.service.PurchasePartyService;
import hibernate.service.serviceImpl.BankServiceImpl;
import hibernate.service.serviceImpl.BankTransactionServiceImpl;
import hibernate.service.serviceImpl.BillServiceImpl;
import hibernate.service.serviceImpl.CustomerServiceImpl;
import hibernate.service.serviceImpl.EmployeeServiceImpl;
import hibernate.service.serviceImpl.ItemServiceImpl;
import hibernate.service.serviceImpl.LoginServiceImpl;
import hibernate.service.serviceImpl.PurchasePartyServiceImpl;

public class GetBackup {

	public String folder="D:\\YashSoftware\\backup\\";
	List<String> list = new ArrayList<>();
	public GetBackup(String folder)
	{
		this.folder = folder;
		getAllBackup();
	}
	public void writeAllBank()
	{
		try {
			BankService service = new BankServiceImpl();
			list = new ArrayList<>();
			for(Bank bank:service.getAllBanks())
			{
				list.add(bank.toString2());
			}
			writeToFile(new File(folder+"bank.dat"),list);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error = "+e.getMessage());
		}
	}
	public void writeAllBankTransaction()
	{
		try {
			BankTransactionService service = new BankTransactionServiceImpl();
			list = new ArrayList<>();
			for(BankTransaction bank:service.getAllBankTransaction())
			{
				list.add(bank.toString2());
			}
			writeToFile(new File(folder+"banktransaction.dat"),list);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error = "+e.getMessage());
		}
	}
	public void writeAllBills()
	{
		try {
			BillService service = new BillServiceImpl();
			list = new ArrayList<>();
			List<String>listTr = new ArrayList<String>();
			for(Bill bill:service.getAllBills())
			{
				list.add(bill.toString2());
				
				for(Transaction tr:bill.getTransaction())
				{
					listTr.add(tr.toString2());
				}
				writeToFile(new File(folder+"transaction.dat"), listTr);
			}
			writeToFile(new File(folder+"bill.dat"),list);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error = "+e.getMessage());
		}
	}
	public void writeAllCustomer()
	{
		try {
			CustomerService service = new CustomerServiceImpl();
			list = new ArrayList<>();
			for(Customer customer:service.getAllCustomer())
			{
				list.add(customer.toString2());
			}
			writeToFile(new File(folder+"customer.dat"),list);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error = "+e.getMessage());			
		}
	}
	public void writeAllEmployee()
	{
		try {
			EmployeeService service = new EmployeeServiceImpl();
			list = new ArrayList<>();
			for(Employee employee:service.getAllEmployee())
			{
				list.add(employee.toString2());
			}
			writeToFile(new File(folder+"employee.dat"),list);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error = "+e.getMessage());
		}
	}
	public void writeAllItem()
	{
		try {
			ItemService service = new ItemServiceImpl();
			list = new ArrayList<>();
			for(Item item:service.getAllItems())
			{
				list.add(item.toString2());
			}
			writeToFile(new File(folder+"item.dat"),list);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error = "+e.getMessage());
		}
	}
	public void writeAllLogin()
	{
		try {
			LoginService service = new LoginServiceImpl();
			list = new ArrayList<>();
			for(Login login:service.getAllLogin())
			{
				list.add(login.toString2());
			}
			writeToFile(new File(folder+"login.dat"),list);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error = "+e.getMessage());			
		}
	}
	public void writeAllPurchaseParty()
	{
		try {
			PurchasePartyService service = new PurchasePartyServiceImpl();
			list = new ArrayList<>();
			for(PurchaseParty purchaseParty:service.getAllPurchaseParty())
			{
				list.add(purchaseParty.toString2());
			}
			writeToFile(new File(folder+"purchaseparty.dat"),list);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error = "+e.getMessage());
		}
	}
	public void getAllBackup()
	{
		writeAllBills();
		writeAllBank();
		writeAllBankTransaction();
		writeAllCustomer();
		writeAllEmployee();
		writeAllItem();
		writeAllLogin();
		writeAllPurchaseParty();
	}
	public static void main(String[] args) {
		new GetBackup("D:\\YashSoftware\\backup\\");
	}
	private static void writeToFile(File file,List<String> content)
	{
		try (FileWriter fw = new FileWriter(file,false);
				BufferedWriter bw = new BufferedWriter(fw)){
			for(String s:content)
			{
				bw.write(s);
				bw.newLine();
			}
			System.out.println("Write Done in "+file.getName());
		} catch (IOException e) {
			System.out.println("error "+e.getMessage());
			e.printStackTrace();
		}
	}
	private static void appendToFile(File file,String content)
	{
		try (FileWriter fw = new FileWriter(file,true);
				BufferedWriter bw = new BufferedWriter(fw)){
				bw.write(content);
				bw.newLine();
			
			System.out.println("Write Done in "+file.getName());
		} catch (IOException e) {
			System.out.println("error "+e.getMessage());
			e.printStackTrace();
		}
	}

	public void writeBank(Bank bank)
	{
		try {
			appendToFile(new File(folder+"bank.dat"),bank.toString2());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error = "+e.getMessage());
		}
	}
	public void writeBankTransaction(BankTransaction bt)
	{
		try {
			appendToFile(new File(folder+"banktransaction.dat"),bt.toString2());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error = "+e.getMessage());
		}
	}
	public void writeBills(Bill bill)
	{
		try {
			
				for(Transaction tr:bill.getTransaction())
				{
					appendToFile(new File(folder+"transaction.dat"), tr.toString2());
				}
				appendToFile(new File(folder+"bill.dat"),bill.toString2());
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error = "+e.getMessage());
		}
	}
	public void writeCustomer(Customer customer)
	{
		try {
			appendToFile(new File(folder+"customer.dat"),customer.toString2());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error = "+e.getMessage());			
		}
	}
	public void writeEmployee(Employee employee)
	{
		try {
			
			appendToFile(new File(folder+"employee.dat"),employee.toString2());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error = "+e.getMessage());
		}
	}
	public void writeItem(Item item)
	{
		try {			
			appendToFile(new File(folder+"item.dat"),item.toString2());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error = "+e.getMessage());
		}
	}
	public void writeLogin(Login login)
	{
		try {
			appendToFile(new File(folder+"login.dat"),login.toString2());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error = "+e.getMessage());			
		}
	}
	public void writePurchaseParty(PurchaseParty pp)
	{
		try {
			appendToFile(new File(folder+"purchaseparty.dat"),pp.toString2());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error = "+e.getMessage());
		}
	}

	
}
