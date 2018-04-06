package ComfigurationClasses;

/**
 * Created by sombramac-8 on 4/4/18.
 */
public class ContactFormField {

    private String fieldType;
    private String fieldLable;
    private boolean mandatoryState;

    public ContactFormField() {
    }

    public ContactFormField(String fieldType, String fieldLable, boolean mandatoryState) {
        this.fieldType = fieldType;
        this.fieldLable = fieldLable;
        this.mandatoryState = mandatoryState;
    }

    public String getFieldType() {
        return fieldType;
    }

    public String getFieldLable() {
        return fieldLable;
    }

    public boolean getMandatoryState() {
        return mandatoryState;
    }

    public ContactFormField setFieldLable(String fieldLable) {
        this.fieldLable = fieldLable;
        return this;
    }

    public ContactFormField setMandatoryState(boolean mandatory) {
        this.mandatoryState = mandatory;
        return this;
    }

    public ContactFormField setFieldType(String fieldType) {
        this.fieldType = fieldType;
        return this;
    }
}
