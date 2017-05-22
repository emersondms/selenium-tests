package com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.SearchPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GlobalNavigation extends BasePageObject {

  @FindBy(css = "#searchInput")
  private WebElement searchInput;

  @FindBy(css = ".wds-global-navigation__logo")
  private WebElement fandomLogo;

  @FindBy(css = ".wds-global-navigation__user-menu")
  private WebElement userAvatar;

  @FindBy(css = ".wds-global-navigation__user-menu .wds-global-navigation__dropdown-content")
  private WebElement userMenu;

  @FindBy(css =
      ".wds-global-navigation__user-menu .wds-global-navigation__dropdown-content li:first-child")
  private WebElement viewProfile;

  @FindBy(css = ".wds-sign-out__button")
  private WebElement signOutButton;

  @FindBy(css = ".wds-is-games")
  private WebElement gamesHubLink;

  @FindBy(css = ".wds-is-movies")
  private WebElement moviesHubLink;

  @FindBy(css = ".wds-is-tv")
  private WebElement tvHubLink;

  @FindBy(css = ".wds-global-navigation__wikis-menu")
  private WebElement wikisMenu;

  @FindBy(css = ".wds-global-navigation__wikis-menu .wds-dropdown__content")
  private WebElement wikisMenuContent;

  @FindBy(css = ".wds-global-navigation__notifications-menu")
  private WebElement notificationsIcon;

  @FindBy(css = ".wds-global-navigation__account-menu")
  private WebElement accountMenu;

  @FindBy(css = ".wds-global-navigation__start-a-wiki ")
  private WebElement startWikiButton;

  @FindBy(css = "a[data-tracking-label=\"link.community-central\"]")
  private WebElement communityCentralLink;

  @FindBy(css = "a[data-tracking-label=\"link.explore\"]")
  private WebElement exploreWikisLink;

  @FindBy(css = "a[data-tracking-label=\"link.fandom-university\"]")
  private WebElement fandomUniversityLink;

  public HomePage clickFandomLogo() {
    wait.forElementClickable(fandomLogo).click();
    PageObjectLogging.log("clickFandomLogo", "clicked on fandom logo in global nav bar", true);

    return new HomePage();
  }

  public HomePage clickGamesHubLink() {
    wait.forElementClickable(gamesHubLink).click();
    PageObjectLogging.log("clickGamesHubLink", "clicked on games hub link in global nav bar", true);

    return new HomePage();
  }

  public HomePage clickMoviesHubLink() {
    wait.forElementClickable(moviesHubLink).click();
    PageObjectLogging.log("clickMoviesHubLink", "clicked on movies hub link in global nav bar", true);

    return new HomePage();
  }

  public HomePage clickTVHubLink() {
    wait.forElementClickable(tvHubLink).click();
    PageObjectLogging.log("clickTVHubLink", "clicked on tv hub link in global nav bar", true);

    return new HomePage();
  }

  public GlobalNavigation openWikisMenu() {
    wait.forElementClickable(wikisMenu).click();
    PageObjectLogging.log("openWikisMenu", "clicked on wikis menu in global nav bar", true);

    return this;
  }

  public HomePage clickCommunityCentralLink() {
    wait.forElementClickable(communityCentralLink).click();
    PageObjectLogging.log("clickCommunityCentralLink", "clicked on community central link in global nav bar", true);

    return new HomePage();
  }

  public HomePage clickExploreWikisLink() {
    wait.forElementClickable(exploreWikisLink).click();
    PageObjectLogging.logInfo("clicked on explore wikis link in global nav bar");

    return new HomePage();
  }

  public HomePage clickFandomUniversityLink() {
    wait.forElementClickable(fandomUniversityLink).click();
    PageObjectLogging.logInfo("clicked on fandom university link in global nav bar");

    return new HomePage();
  }

  public SearchPageObject search(String query) {
    searchInput.sendKeys(query);
    searchInput.submit();

    PageObjectLogging.logInfo("search query typed and submitted");
    return new SearchPageObject(driver);
  }

  public GlobalNavigation clickUserAvatar() {
    wait.forElementClickable(userAvatar).click();
    PageObjectLogging.logInfo("clicked on user avatar in global nav bar");

    return this;
  }

  public void clickSignOut() {
    clickUserAvatar();
    wait.forElementClickable(signOutButton).click();
    PageObjectLogging.logInfo("link to sign out clicked");
  }

  public boolean isUserMenuOpened() {
    return isElementDisplayed(userMenu);
  }

  public void clickViewProfile() {
    viewProfile.click();
  }

  public boolean isFandomLogoVisible() {
    return isElementDisplayed(fandomLogo);
  }

  public boolean isUserLoggedOut() {
    return driver.findElements(By.cssSelector(".wds-global-navigation__account-menu")).size() > 0;
  }

  public boolean isGamesHubVisible() {
    return isElementDisplayed(gamesHubLink);
  }

  public boolean isMoviesHubVisible() {
    return isElementDisplayed(moviesHubLink);
  }

  public boolean isTVHubVisible() {
    return isElementDisplayed(tvHubLink);
  }

  public boolean isWikisMenuVisible() {
    return isElementDisplayed(wikisMenu);
  }

  public boolean isSearchInputVisible() {
    return isElementDisplayed(searchInput);
  }

  public boolean isUserAvatarVisible() {
    return isElementDisplayed(userAvatar);
  }

  public boolean isNotificationsIconVisible() {
    return isElementDisplayed(notificationsIcon);
  }

  public boolean isAccountMenuVisible() {
    return isElementDisplayed(accountMenu);
  }

  public boolean isStartWikiButtonVisible() {
    return isElementDisplayed(startWikiButton);
  }

  public boolean isCommunityCentralLinkVisible() {
    return isElementDisplayed(communityCentralLink);
  }
}
