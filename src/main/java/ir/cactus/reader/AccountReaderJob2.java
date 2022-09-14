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
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AccountReaderJob2 implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        BankAccountFileReader reader=new BankAccountFileReader();
        reader.getAccount();
        reader.getCustomer();
        BankDataValidator validator=new BankDataValidator(reader.getSorted2Account(),reader.getSorted2Customer());
        validator.isDataValidate();
        BankAccountDataBase dataBase=new BankAccountDataBase(validator.getAccounts(), validator.getCustomers());
        System.out.println("adding customer to the database ");
        dataBase.insertCustomerData();
        System.out.println("adding account to the database ");
        dataBase.insertAccountData();
        System.out.println("done");
        System.out.println("validation complete ::::::"+"\n");

    }

}
