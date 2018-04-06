package ComfigurationClasses;

/**
 * Created by sombramac-8 on 4/4/18.
 */
public class ContactFormFactory {
    public static ContactFormConfiguration getContactFormConfigurations(final String configName){
        if ("Contact_Form_Case1".equals(configName)){
            // only Email and First name fields
        }
        if ("Contact_Form_Case2".equals(configName)){
            return new ContactFormConfiguration()
                    .addMainShootDateField(true);
        }
        if ("Contact_Form_Case3".equals(configName)){
            return new ContactFormConfiguration()
                    .addMainShootDateField(false);
        }
        if ("Contact_Form_Case4".equals(configName)){
            return new ContactFormConfiguration()
                    .addJobTypeField(true);
        }
        if ("Contact_Form_Case5".equals(configName)){
            return new ContactFormConfiguration()
                    .addJobTypeField(false);
        }
        if ("Contact_Form_Case6".equals(configName)){
            return new ContactFormConfiguration()
                    .addJobTypeField(true)
                    .addMainShootDateField(false)
                    .addJobLocationField(false);
        }
        if ("Contact_Form_Case7".equals(configName)){
            return new ContactFormConfiguration()
                    .addJobTypeField(true)
                    .addMainShootDateField(true);
        }
        if ("Contact_Form_Case8".equals(configName)){
            return new ContactFormConfiguration()
                    .addJobTypeField(false)
                    .addMainShootDateField(true)
                    .addLeadSourceField(false);
        }
        if ("Contact_Form_Case9".equals(configName)){
            return new ContactFormConfiguration()
                    .addJobTypeField(false)
                    .addMainShootDateField(false);
        }
        if ("Contact_Form_Case10".equals(configName)){
            return new ContactFormConfiguration()
                    .addJobTypeField(false)
                    .addMainShootDateField(false)
                    .addLeadSourceField(false)
                    .addLastNameField(false)
                    .addJobLocationField(false)
                    .addMessageField(false)
                    .addOtherField(false)
                    .addPhoneField(false);
        }
        if ("Contact_Form_Case11".equals(configName)){
            return new ContactFormConfiguration()
                    .addJobTypeField(true)
                    .addMainShootDateField(true)
                    .addLeadSourceField(true)
                    .addLastNameField(true)
                    .addJobLocationField(true)
                    .addMessageField(true)
                    .addOtherField(true)
                    .addPhoneField(true);
        }
        else return null;
    }
}
