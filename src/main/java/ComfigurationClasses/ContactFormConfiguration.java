package ComfigurationClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sombramac-8 on 4/4/18.
 */
public class ContactFormConfiguration {

    private List<ContactFormField> fields;

    public ContactFormConfiguration() {
        fields = new ArrayList<>();
    }

    public ContactFormConfiguration addLastNameField(boolean mandatory){
        fields.add(new ContactFormField(Page.CF_LAST_NAME, "Your surname", mandatory));
        return this;
    }

    public ContactFormConfiguration addPhoneField(boolean mandatory){
        fields.add(new ContactFormField(Page.CF_PHONE, "Give me your number", mandatory));
        return this;
    }

    public ContactFormConfiguration addMessageField(boolean mandatory){
        fields.add(new ContactFormField(Page.CF_MESSAGE, "Enter here anything", mandatory));
        return this;
    }

    public ContactFormConfiguration addOtherField(boolean mandatory){
        fields.add(new ContactFormField(Page.CF_OTHER, "One more answer?", mandatory));
        return this;
    }

    public ContactFormConfiguration addJobTypeField(boolean mandatory){
        fields.add(new ContactFormField(Page.CF_JOB_TYPE, "type of your event", mandatory));
        return this;
    }

    public ContactFormConfiguration addMainShootDateField(boolean mandatory){
        fields.add(new ContactFormField(Page.CF_JOB_DATE, "When", mandatory));
        return this;
    }

    public ContactFormConfiguration addJobLocationField(boolean mandatory){
        fields.add(new ContactFormField(Page.CF_LOCATION, "Where", mandatory));
        return this;
    }

    public ContactFormConfiguration addLeadSourceField(boolean mandatory){
        fields.add(new ContactFormField(Page.CF_SOURCE, "source", mandatory));
        return this;
    }

    public List<ContactFormField> getFieldsList(){
        return fields;
    }


}
