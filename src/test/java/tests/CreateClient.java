package tests;

import org.testng.annotations.Test;

/**
 * Created by sombra-15 on 14.09.17.
 */
public class CreateClient extends TestBase {

    @Test
    public void createClientFromClients(){
        startPage.goToClientsSection()
                .addNewClient()
                .createNewClient(

                )
                .goToSomeClientPage();
    }

    @Test
    public void createClientAndEdit(){
        startPage.goToClientsSection()
                .addNewClient()
                .createNewClient(

                )
                .goToSomeClientPage()
                .editClientInfo();
    }
}
