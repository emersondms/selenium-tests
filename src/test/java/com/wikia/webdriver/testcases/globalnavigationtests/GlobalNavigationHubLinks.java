package com.wikia.webdriver.testcases.globalnavigationtests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.VenusGlobalNavPageObject;

/**
 * @author Bogna 'bognix' Knychała
 * @ownership Content X-Wing
 */
public class GlobalNavigationHubLinks extends NewTestTemplate {

    @Test(groups = {"TestHubLinksInGlobalNav_001", "GlobalNav"})
    public void TestHubLinksInGlobalNav_001_clickHubsLinks() {
        HomePageObject homePage = new HomePageObject(driver);
        homePage.getUrl(urlBuilder.getUrlForWiki("muppet"));
        VenusGlobalNavPageObject globalNav = homePage.getVenusGlobalNav();
        globalNav.openHubsMenuViaHover();
        Assertion.assertTrue(globalNav.isHubsMenuOpened());

        for (VenusGlobalNavPageObject.Hub hubName : VenusGlobalNavPageObject.Hub.values()) {
            WebElement hub = globalNav.openHub(hubName);
            String link = globalNav.getHubLink(hub);
            hub.click();
            Assertion.assertEquals(driver.getCurrentUrl(), link);
        }
    }
}