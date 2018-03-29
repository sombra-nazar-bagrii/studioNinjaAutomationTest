package ComfigurationClasses;

/**
 * Created by sombra-15 on 15.09.17.
 */
public class JobConfigurationFactory {

    /*
     numberOfClients         "with how many clients (client/clients)"_
     createdOrExisted        "client/s should be chooses from existed or create new(new/exist)"_
     typeOfJobDuration       "time of shoot (AllDay/Time/No Date)"_
     typeOfWorkflow          "with workflow (default/created)"

     Using pair wise matrix we have next cases
        Client      New Or Not    Duration    Workflow
     1.    1           new         all day     default
     2.    1          exist         time       created
     3.    1          exist        no date     default
     4.    2          exist        all day     created
     5.    2           new          time       default
     6.    2           new         no date     created
     7.    0           null        no date     created
     8.    1          exist        no date     created      no job name
     9.    1          exist         empty      created
     10.   1          exist   w/o all day box  created
     11.   2        the same       no date     created
     12.   1          exist      time before   created
    */

    public static JobConfiguration getConfiguration(final String nameConfiguration){
        if ("JobConf1".equals(nameConfiguration)){
            return new JobConfiguration()
                    .setNumberOfClients("one")
                    .setCreateOrExisted("new")
                    .setTypeOfJobDuration("all day")
                    .setTypeOfWorkflow("default");
        }
        if ("JobConf2".equals(nameConfiguration)){
            return new JobConfiguration()
                    .setNumberOfClients("one")
                    .setCreateOrExisted("exist")
                    .setTypeOfJobDuration("time")
                    .setTypeOfWorkflow("created");
        }
        if ("JobConf3".equals(nameConfiguration)){
            return new JobConfiguration()
                    .setNumberOfClients("one")
                    .setCreateOrExisted("exist")
                    .setTypeOfJobDuration("no date")
                    .setTypeOfWorkflow("default");
        }
        if ("JobConf4".equals(nameConfiguration)){
            return new JobConfiguration()
                    .setNumberOfClients("two")
                    .setCreateOrExisted("exist")
                    .setTypeOfJobDuration("all day")
                    .setTypeOfWorkflow("created");
        }
        if ("JobConf5".equals(nameConfiguration)){
            return new JobConfiguration()
                    .setNumberOfClients("two")
                    .setCreateOrExisted("new")
                    .setTypeOfJobDuration("time")
                    .setTypeOfWorkflow("default");
        }
        if ("JobConf6".equals(nameConfiguration)){
            return new JobConfiguration()
                    .setNumberOfClients("two")
                    .setCreateOrExisted("new")
                    .setTypeOfJobDuration("no date")
                    .setTypeOfWorkflow("created");
        }
        if ("JobConf7".equals(nameConfiguration)){
            return new JobConfiguration()
                    .setNumberOfClients("null")
                    .setCreateOrExisted("null")
                    .setTypeOfJobDuration("no date")
                    .setTypeOfWorkflow("default");
        }
        if ("JobConf8".equals(nameConfiguration)){
            return new JobConfiguration()
                    .setNumberOfClients("one")
                    .setCreateOrExisted("exist")
                    .setTypeOfJobDuration("no date")
                    .setTypeOfWorkflow("default");
        }
        if ("JobConf9".equals(nameConfiguration)){
            return new JobConfiguration()
                    .setNumberOfClients("one")
                    .setCreateOrExisted("exist")
                    .setTypeOfJobDuration("null")
                    .setTypeOfWorkflow("default");
        }
        if ("JobConf10".equals(nameConfiguration)){
            return new JobConfiguration()
                    .setNumberOfClients("one")
                    .setCreateOrExisted("exist")
                    .setTypeOfJobDuration("no all day box")
                    .setTypeOfWorkflow("created");
        }
        if ("JobConf11".equals(nameConfiguration)){
            return new JobConfiguration()
                    .setNumberOfClients("two the same")
                    .setCreateOrExisted("exist")
                    .setTypeOfJobDuration("no date")
                    .setTypeOfWorkflow("default");
        }
        if ("JobConf12".equals(nameConfiguration)){
            return new JobConfiguration()
                    .setNumberOfClients("one")
                    .setCreateOrExisted("exist")
                    .setTypeOfJobDuration("time before")
                    .setTypeOfWorkflow("default");
        }
        else return null;
    }
}
