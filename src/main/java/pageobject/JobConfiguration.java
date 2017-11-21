package pageobject;

/**
 * Created by sombra-15 on 15.09.17.
 */
public class JobConfiguration {
    private  String numberOfClients;
    private  String createOrExisted;
    private  String typeOfJobDuration;
    private  String typeOfWorkflow;
    private String CLIENT_NAME = "Test";
    private boolean modalsWithCC = true;

    public JobConfiguration(){
    }

    public String getNumberOfClients() {
        return numberOfClients;
    }

    public String getCreateOrExisted() {
        return createOrExisted;
    }

    public String getTypeOfJobDuration() {
        return typeOfJobDuration;
    }

    public String getTypeOfWorkflow() {
        return typeOfWorkflow;
    }

    public String getCLIENT_NAME() {
        return CLIENT_NAME;
    }

    public boolean isModalsWithCC() {
        return modalsWithCC;
    }

    public JobConfiguration setNumberOfClients(String numberOfClients) {
        this.numberOfClients = numberOfClients;
        return this;
    }

    public JobConfiguration setCreateOrExisted(String createOrExisted) {
        this.createOrExisted = createOrExisted;
        return this;
    }

    public JobConfiguration setTypeOfJobDuration(String typeOfJobDuration) {
        this.typeOfJobDuration = typeOfJobDuration;
        return this;
    }

    public JobConfiguration setTypeOfWorkflow(String typeOfWorkflow) {
        this.typeOfWorkflow = typeOfWorkflow;
        return this;
    }

    public JobConfiguration setCLIENT_NAME(String CLIENT_NAME) {
        this.CLIENT_NAME = CLIENT_NAME;
        return this;
    }

    public JobConfiguration setModalsWithCC(boolean modalsWithCC) {
        this.modalsWithCC = modalsWithCC;
        return this;
    }
}
