/**
 * @Author Nandang Sopyan
 * @ApplicationName remind.me app
 * @CreatedAt Sept 2022
 * @Description This is a REST API application as mini project task at alterra training academy program
 */
package com.training.alterra.miniproject.remindmeapp.jobs;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class ReminderJob extends QuartzJobBean {

    public static final String ansi_reset = "\u001B[0m";
    private static final String ansi_red = "\u001B[31m";
    private static final String ansi_green = "\u001B[32m";
    private static final String ansi_yellow = "\u001B[33m";

    private static final Logger logger = LoggerFactory.getLogger(ReminderJob.class);
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String reminderUser = jobDataMap.getString("reminderUser");
        String reminderTitle = jobDataMap.getString("reminderTitle");
        String reminderDescription = jobDataMap.getString("reminderDescription");

        logger.info(String.format(
                "\nHello " +ansi_red+ "%s" +ansi_reset+" , I'm" +ansi_yellow+" remindy :)" + ansi_reset+",\n" +
                        "Just reminding you that you have reminder : \n \n" +
                        ansi_green+"Title:"+ansi_reset+ " %s \n" +
                        ansi_green+"Description:"+ansi_reset+" %s \n" +
                        "\n" +
                        "Cheers, \n" +
                        ansi_yellow+"Remindy"+ansi_reset+" from" +ansi_yellow+ " Remind.me" +ansi_reset+
                        " :) \n",
                reminderUser,
                reminderTitle,
                reminderDescription
        ));

    }
}