package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by sombra-15 on 14.07.17.
 */

public class CreateUser extends TestBase {
    private static String EMAIL = "taras.makar@sombrainc.com";
    private static String PASSWORD = "testtest";

    @Test (priority = 1, enabled = false)
    public void checkIfErrorMessageValid(){
        Assert.assertTrue(startPage2.checkIfErrorMessageCorrect(EMAIL, PASSWORD));
    }

    @Test (priority = 0, enabled = true)
    public void verifyIfWeCanRegistrationProcess(){

            startPage2.openSingUpPage().singUpNewUser();
            System.out.println("Process of registration of new user completed successfully");
            Assert.fail("Failed registration!!");

    }

    @Test (priority = 0, enabled = false)
    public void logInByExistedUser(){
        try {
            startPage2.enterByExistingUser(EMAIL, PASSWORD);
        } catch (Exception e){
            Assert.fail("Failed login!!");
        }
    }

}
