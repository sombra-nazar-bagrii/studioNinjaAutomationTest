package tests.ContactForm;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.TestBase;

/**
 * Created by sombramac-8 on 4/5/18.
 */
public class EmbeddedContactForm extends TestBase {

    @DataProvider
    public Object[][] getContactFormConfigurations(){
        return new Object[][]{
                {"Contact_Form_Case1"},
                {"Contact_Form_Case2"},
                {"Contact_Form_Case3"},
                {"Contact_Form_Case4"},
                {"Contact_Form_Case5"},
                {"Contact_Form_Case6"},
                {"Contact_Form_Case7"},
                {"Contact_Form_Case8"},
                {"Contact_Form_Case9"},
                {"Contact_Form_Case10"},
                {"Contact_Form_Case11"},};
    }

    @Test(dataProvider = "getContactFormConfigurations", enabled = true)
    public void embeddedContactFormTest(String configrations){
        startPage.goToSettingsSection()
                .goToContractFormPage()
                .goToEmbeddedContactForm(configrations);
    }
}
