package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by sombra-15 on 19.09.17.
 */
public class Navigation extends TestBase {

    @Test
    public void checkHeaderFunctionality(){
        startPage
                .goToDashboard()
                .returnHeader()
                .goToClientsSection()
                .returnHeader()
                .goToJobsOverviewSection()
                .returnHeader()
                .goToCalendar()
                .returnHeader()
                .goToPayments()
                .returnHeader()
                .goToSettingsSection()
                .returnHeader();
    }

    @Test (priority = 1)
    public void checkUpcomingNavigationJob(){
        Assert.assertTrue(startPage
                .goToDashboard()
                .checkIfUpcomingSectionWorks()
        );
    }

    @Test (priority = 2)
    public void checkUpcomingDisplaying(){
        Assert.assertTrue(startPage
                .goToDashboard()
                .checkIfElementDisplayedInUpcomingSection()
        );
    }

    @Test (priority = 3)
    public void checkDashboardHeaderNavigation(){
        startPage
                .goToDashboard()
                .checkAllStatesOfHeaderDropBoxes();
    }
}
