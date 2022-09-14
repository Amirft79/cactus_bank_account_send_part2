package ir.cactus.reader;

import ir.cactus.model.Account;
import ir.cactus.model.Customer;
import ir.cactus.utils.BankAccountFileReader;
import ir.cactus.utils.BankDataValidator;
import ir.cactus.utils.JsonErrorFileMaker;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;

public class AccountMakeErrorFileJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ArrayList<Customer>customers=new ArrayList<>();
        ArrayList<Account>accounts=new ArrayList<>();
        BankAccountFileReader reader=new BankAccountFileReader();
        reader.getAccount();
        reader.getCustomer();
        accounts=reader.getAccounts();
        customers=reader.getCustomers();
        BankDataValidator validator=new BankDataValidator(accounts,customers);
        validator.isDataValidate();
        JsonErrorFileMaker jsonErrorFileMaker=new JsonErrorFileMaker(validator.getError_accounts(),validator.getError_customers());
        System.out.println("make customer error file");
        jsonErrorFileMaker.MakeJsonFileOfCustomer();
        System.out.println("make account error file");
        jsonErrorFileMaker.MakeJsonOfAccount();
        System.out.println("make account error file");
        jsonErrorFileMaker.MakeAllErrorJsonFile();
    }
}
