package Factory;

import pageobject.JobConfiguration;

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

    */

    public static JobConfiguration getConfiguration(final String nameConfiguration){
        if ("conf1".equals(nameConfiguration)){
            return new JobConfiguration()
                    .setNumberOfClients("one")
                    .setCreateOrExisted("new")
                    .setTypeOfJobDuration("all day")
                    .setTypeOfWorkflow("default");
        }
        if ("conf2".equals(nameConfiguration)){
            return new JobConfiguration()
                    .setNumberOfClients("one")
                    .setCreateOrExisted("exist")
                    .setTypeOfJobDuration("time")
                    .setTypeOfWorkflow("created");
        }
        if ("conf3".equals(nameConfiguration)){
            return new JobConfiguration()
                    .setNumberOfClients("one")
                    .setCreateOrExisted("exist")
                    .setTypeOfJobDuration("no date")
                    .setTypeOfWorkflow("default");
        }
        if ("conf4".equals(nameConfiguration)){
            return new JobConfiguration()
                    .setNumberOfClients("two")
                    .setCreateOrExisted("exist")
                    .setTypeOfJobDuration("all day")
                    .setTypeOfWorkflow("created");
        }
        if ("conf5".equals(nameConfiguration)){
            return new JobConfiguration()
                    .setNumberOfClients("two")
                    .setCreateOrExisted("new")
                    .setTypeOfJobDuration("time")
                    .setTypeOfWorkflow("default");
        }
        if ("conf6".equals(nameConfiguration)){
            return new JobConfiguration()
                    .setNumberOfClients("two")
                    .setCreateOrExisted("new")
                    .setTypeOfJobDuration("no date")
                    .setTypeOfWorkflow("created");
        }
        else return null;
    }
}
