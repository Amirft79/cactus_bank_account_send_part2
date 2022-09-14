package ir.cactus.utils;

import ir.cactus.model.Account;
import ir.cactus.model.Customer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.util.ArrayList;

public class BankAccountFileReader {

   private  ArrayList<Account>accounts;
   private  ArrayList<Customer>customers;

    public BankAccountFileReader(){
        this.accounts=new ArrayList<>();
        this.customers=new ArrayList<>();
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void getAccount(){
        ArrayList<Account> accounts=new ArrayList<>();

     try( CSVParser Account_Csv_Parser=new CSVParser(new FileReader("Accounts.csv"), CSVFormat.EXCEL
             .withFirstRecordAsHeader())){
            for (CSVRecord record:Account_Csv_Parser){
                    accounts.add(new Account(Integer.parseInt(record.get("Record_Number")),record.get("Account_Number"),AccountType.valueOf(record.get("Account_Type")),
                            Integer.parseInt(record.get("Account_Customer_Id")),Integer.parseInt(record.get("Account_Limit")),record.get("Account_Open_Date"),
                            Integer.parseInt(record.get("Account_Balance"))));

            }

     }catch (Exception e){
         e.printStackTrace();
     }

        this.accounts=accounts;
    }

    public void getCustomer(){
        ArrayList<Customer>customers=new ArrayList<>();


        try(CSVParser Customer_Csv_Parser=new CSVParser(new FileReader("Customer.csv"),CSVFormat.EXCEL.withFirstRecordAsHeader())){
            for (CSVRecord record:Customer_Csv_Parser){
                    customers.add(new Customer(Integer.parseInt(record.get("Record_Number")), Integer.parseInt(record.get("Customer_Id")), record.get("Customer_Name"), record.get("Customer_SurName"),
                            record.get("Customer_Address"), Integer.parseInt(record.get("Customer_Zip_Code")), record.get("Customer_National_Id"), record.get("Customer_Birth_Date")));

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        this.customers=customers;
    }
    public ArrayList<Customer> getSortedCustomer(){
        ArrayList<Customer>soreted1=new ArrayList<>();
        for (int i=0;i<25;i++){
            soreted1.add(this.customers.get(i));
        }
        return soreted1;
    }

    public ArrayList<Customer> getSorted2Customer(){
        ArrayList<Customer>soreted2=new ArrayList<>();
        for (int i=25;i<51;i++){
            soreted2.add(this.customers.get(i));
        }
        return soreted2;
    }

    public ArrayList<Account> getSortedAccount(){
        ArrayList<Account>soreted1=new ArrayList<>();
        for (int i=0;i<25;i++){
            soreted1.add(this.accounts.get(i));
        }
        return soreted1;
    }

    public ArrayList<Account> getSorted2Account(){
        ArrayList<Account>soreted2=new ArrayList<>();
        for (int i=25;i<51;i++){
            soreted2.add(this.accounts.get(i));
        }
        return soreted2;
    }



}
