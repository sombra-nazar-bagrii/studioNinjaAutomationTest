package ComfigurationClasses;

import java.util.*;

/**
 * Created by sombra-15 on 18.09.17.
 */
public class ClientConfiguration {

    private static final List<String> firstName = Arrays.asList("Test Nazar", "Test Oleh", " Test Tania", "Test Andrew", "Test Vlad", "Test Roksolana", "Test Olia");
    private static final List<String> lastName = Arrays.asList("Smit", "Ivanov", "Papka", "Batia", "Petrov", "Jones");
    private static final List<String> phone = Arrays.asList("02589462", "5463156", "8746513", "6987446");
    private static final List<String> street = Arrays.asList("Pid Dubom", "Zamarstynivska", "Ugorska", "Gorodotska");
    private static final List<String> town = Arrays.asList("Lviv", "Ivano-F", "Kiev", "London");
    private static final List<String> postcode = Arrays.asList("87125", "32136");
    private static final List<String> state = Arrays.asList("Some", "I don't know");
    private static final List<String> country = Arrays.asList("Ukraine", "Au", "Poland");
    private static final List<String> notes = Arrays.asList("bla bla bla", "Some notes");

    static Random rand = new Random();

    public ClientConfiguration() {
    }

    public static String getFirstName() {
        return firstName.get(rand.nextInt(firstName.size()));
    }

    public static String getLastName() {
        return lastName.get(rand.nextInt(lastName.size()));
    }

    public static String getPhone() {
        return phone.get(rand.nextInt(phone.size()));
    }

    public static String getStreet() {
        return street.get(rand.nextInt(street.size()));
    }

    public static String getPostcode() {
        return postcode.get(rand.nextInt(postcode.size()));
    }

    public static String getTown() {
        return town.get(rand.nextInt(town.size()));
    }

    public static String getNotes() {
        return notes.get(rand.nextInt(notes.size()));
    }

    public static String getCountry() {
        return country.get(rand.nextInt(country.size()));
    }

    public static String getState() {
        return state.get(rand.nextInt(state.size()));
    }

    public static List<String> getAllValues() {
        return Arrays.asList(
                getFirstName(),
                getLastName(),
                getPhone(),
                getStreet(),
                getTown(),
                getPostcode(),
                getState(),
                getCountry(),
                getNotes());
    }
}
