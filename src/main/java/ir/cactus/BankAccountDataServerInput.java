package ir.cactus;


import ir.cactus.reader.AccountMakeErrorFileJob;
import ir.cactus.reader.AccountReaderJob1;
import ir.cactus.reader.AccountReaderJob2;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.util.Date;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;


public class BankAccountDataServerInput {
    private static Logger logger=Logger.getLogger(BankAccountDataServerInput.class);

    public static void main(String[] args) {

        try {
            JobDetail accountJobReader1 = JobBuilder.newJob(AccountReaderJob1.class).withIdentity("first chunk").build();
            JobDetail accountJobReader2 = JobBuilder.newJob(AccountReaderJob2.class).withIdentity("second chunk").build();
            JobDetail accountJobReader3 = JobBuilder.newJob(AccountMakeErrorFileJob.class).withIdentity("third chunk").build();


            Trigger trigger1 = TriggerBuilder.newTrigger()
                    .withIdentity("cronTrigger1", "group1")
                    .withSchedule(CronScheduleBuilder
                            .cronSchedule("0/7 * * * * ?")).build();

            Trigger trigger2 = TriggerBuilder.newTrigger()
                    .withIdentity("cronTrigger2", "group2")
                    .withSchedule(CronScheduleBuilder
                            .cronSchedule("0/7 * * * * ?")).build();

            Trigger trigger3 = TriggerBuilder.newTrigger()
                    .withIdentity("cronTrigger3", "group3")
                    .withSchedule(CronScheduleBuilder
                            .cronSchedule("0/7 * * * * ?")).build();

            Scheduler scheduler =
                    new StdSchedulerFactory().getScheduler();

                scheduler.scheduleJob(accountJobReader1, trigger1);

                scheduler.scheduleJob(accountJobReader2, trigger2);

                scheduler.scheduleJob(accountJobReader3, trigger3);
                scheduler.start();



        }catch (Exception e){
            logger.error(e);
        }



    }
}
