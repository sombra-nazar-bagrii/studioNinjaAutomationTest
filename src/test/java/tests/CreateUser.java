package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by sombra-15 on 14.07.17.
 */

public class CreateUser extends TestBase {
    private static String EMAIL = "studioninjatest@gmail.com";
    private static String PASSWORD = "qweqwe";
    private static boolean STAYSIGNED = true;

    @Test (priority = 1, enabled = false)
    public void checkIfErrorMessageValid(){
        Assert.assertTrue(startPage2.checkIfErrorMessageCorrect(EMAIL, PASSWORD));
    }

    @Test (priority = 0, enabled = false)
    public void verifyIfWeCanRegistrationProcess(){
            startPage2.openSingUpPage().singUpNewUser();
            System.out.println("Process of registration of new user completed successfully");
    }

    @Test (priority = 0, enabled = true)
    public void logInByExistedUser(){
        try {
            startPage2.enterByExistingUser(EMAIL, PASSWORD, STAYSIGNED);
        } catch (Exception e){
            Assert.fail("Failed login!!");
        }
    }

}
