package ir.cactus.reader;
import ir.cactus.database.BankAccountDataBase;
import ir.cactus.model.Account;
import ir.cactus.model.Customer;
import ir.cactus.utils.AccountType;
import ir.cactus.utils.BankAccountFileReader;
import ir.cactus.utils.BankDataValidator;
import ir.cactus.utils.JsonErrorFileMaker;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.quartz.*;

import java.io.FileReader;
import java.util.ArrayList;

public class AccountReaderJob1 implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        BankAccountFileReader reader=new BankAccountFileReader();
        reader.getAccount();
        reader.getCustomer();
        reader.getSortedAccount();
        BankDataValidator validator=new BankDataValidator(reader.getSortedAccount(),reader.getSortedCustomer());
        validator.isDataValidate();
        BankAccountDataBase dataBase=new BankAccountDataBase(validator.getAccounts(), validator.getCustomers());
        dataBase.insertCustomerData();
        System.out.println("adding account to the database ");
        dataBase.insertAccountData();
        System.out.println("done");
        System.out.println("validation complete ::::::"+"\n");

    }



}
