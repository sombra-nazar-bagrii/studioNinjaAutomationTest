package tests;

import org.junit.Assert;
import org.testng.annotations.Test;

/**
 * Created by sombra-15 on 14.07.17.
 */

public class CreateUser extends TestBase {
    private static String EMAIL = "posya.klyueva@gmail.com";
    private static String PASSWORD = "qweqwe";

    @Test (priority = 1, enabled = false)
    public void checkIfErrorMessageValid(){
        Assert.assertTrue(startPage2.checkIfErrorMessageCorrect(EMAIL, PASSWORD));
    }

    @Test (priority = 2, enabled = false)
    public void verifyIfWeCanRegistrationProcess(){
        try{
            startPage2.openSingUpPage().singUpNewUser();
            System.out.println("Process of registration of new user completed successfully");
        }catch (Exception e){
            Assert.fail("Failed registration!!");
        }
    }

    @Test ( enabled = true)
    public void logInByExistedUser(){
        try {
            startPage2.enterByExistingUser(EMAIL, PASSWORD);
        } catch (Exception e){
            Assert.fail("Failed login!!");
        }
    }

}
