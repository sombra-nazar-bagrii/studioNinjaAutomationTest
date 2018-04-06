package ComfigurationClasses;

/**
 * Created by sombramac-8 on 12/25/17.
 */
public enum Messages {

    JOB_CREATED("Mission accomplished!", "A new job has been created.", "success"),
    NO_CLIENTS_SELECTED("Measure twice, cut once!", "Please select or create a primary contact for this job.", "error"),
    NO_JOB_NAME_ENTERED("Houston, we have a problem!", "Only add letters into the Job Name field, no spaces or strange symbols allowed.", "error"),
    NO_DURATION_SELECTED("What's going on here!", "Please enter a start and end time or tick 'all day' if this is an all day job.", "error"),
    TWO_THE_SAME_CLIENTS("Oops!", "The secondary client needs to be different from the primary client.", "error"),
    INVALID_TIME("Jetlagged!", "The start time must be before the end date.", "error");

	// TODO make final
    private String title;
    private String message;
    private String type;

    Messages(String title, String message, String type) {
        this.title = title;
        this.message = message;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }


}
