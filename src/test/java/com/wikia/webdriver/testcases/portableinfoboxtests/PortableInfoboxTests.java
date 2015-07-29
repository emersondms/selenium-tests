package com.wikia.webdriver.testcases.portableinfoboxtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.PortableInfobox;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.toolbars.CustomizedToolbarComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.wikitextshortcuts.WikiTextShortCutsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInboxPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.category.CategoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.LoginPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWhatLinksHerePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner.SpecialThemeDesignerPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.template.TemplatePageObject;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 * Created by Rodriuki on 12/06/15.
 * Set of Test Cases found on https://one.wikia-inc.com/wiki/Portable_Infoboxes_Test_Plan
 *
 * TC01: Verify elements visibility: infobox title, image, headers, italic, bold, quotation marks, references
 * TC02: Verify correct redirects in mediawiki119.wikia.com/wiki/RodriInfobox01 for:
 * external links, internal links, red links
 * TC03: Verify images used in infoboxes appear in Special:WhatLinksHere page
 * TC04: Verify adding a category to infobox markup and then invoking that template in an article
 * page will display category in categories section at the bottom of the page automatically
 *
 * Created by nikodamn 20/07/15
<<<<<<< HEAD
 * TC05: Verify lightbox opens when clicking infobox image
 * TC06: Verify visibility of tabber and it's images
 * TC07: Verify infobox color has changed after changing colors in wiki Theme Designer
 * TC08: Verify if ordered and unordered lists are parsed correctly after adding them
 * TC13: Verify category links inside infoboxes
 * TC14: Verify if horizontal group font size matches other elements font
 * TC15: Copy syntax from template page to article and verify presence of all new information provided
 */
public class PortableInfoboxTests extends NewTestTemplate {

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_001"})
  public void verifyElementsVisibility() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    PortableInboxPageObject info = article.getInfoboxPage();
    Assertion.assertTrue(info.getBoldElements().size() > 0);
    Assertion.assertTrue(info.getItalicElements().size() > 0);
    Assertion.assertTrue(info.getHeaderElements().size() > 0);
    info.verifyQuotationMarksPresence();
    info.verifyReferencesPresence();
    info.verifyImagePresence();
    info.verifyInfoboxTitlePresence();

  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_002"})
  public void verifyElementsRedirects() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    PortableInboxPageObject info = article.getInfoboxPage();
    //Red link
    info.clickRedLink();
    info.verifyCreateNewArticleModal();
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    article.getInfoboxPage();
    //External Link
    String externalLinkName = info.getExternalLinkRedirectTitle();
    info.clickExternalLink();
    String externalNavigatedURL = info.getCurrentUrl();
    info.compareURLAndExternalLink(externalLinkName, externalNavigatedURL);
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    article.getInfoboxPage();
    //Internal Link
    String internalLinkName = info.getInternalLinkRedirectTitle();
    info.clickInternalLink();
    String internalNavigatedURL = info.getCurrentUrl();
    info.compareURLAndInternalLink(internalLinkName, internalNavigatedURL);
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_003"})
  public void verifyImagesInWhatLinksHerePage() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    PortableInboxPageObject info = article.getInfoboxPage();
    String articleName = article.getArticleName();
    SpecialWhatLinksHerePageObject links = article.openSpecialWhatLinksHere(wikiURL);
    links.clickPageInputField();
    links.typeInfoboxImageName(PageContent.FILE_IMAGE_NAME);
    links.clickShowbutton();
    links.verifyInfoboxArticleInList(articleName);
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_004"})
  public void verifyCategoriesInTemplateInvocation() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    PortableInboxPageObject info = article.getInfoboxPage();
    SourceEditModePageObject src = info.navigateToArticleEditPageSrc(wikiURL, PageContent.PORTABLE_INFOBOX_WEBSITE_TEMPLATE);
    String catName = src.getRandomDigits(9);
    WikiTextShortCutsComponentObject shortcuts = src.clickMore();
    src = shortcuts.clickCategory();
    src.addCategoryToSourceCode(catName);
    TemplatePageObject temp = src.clickPublishButtonInTemplateNamespace();
    temp.verifyCategoryInTemplatePage(catName);
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    info.verifyCategoryInArticlePage(catName);
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_006"})
  public void verifyLightboxVisibilityAfterClickingImage() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    PortableInboxPageObject info = article.getInfoboxPage();
    info.clickImage();
    info.verifyLightboxPresence();
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_008"})
  public void verifyVisibilityOfTabberAndItsImages() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX02);
    PortableInboxPageObject info = article.getInfoboxPage();
    info.verifyTabberPresence();
    info.verifyTabberImagePresence();
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_009"})
  public void verifyInfoboxLayoutChange() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    PortableInboxPageObject info = article.getInfoboxPage();
    CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
    String textThemeDesigner = toolbar.getThemeDesignerText();
    toolbar.clickOnTool(textThemeDesigner);
    SpecialThemeDesignerPageObject special = new SpecialThemeDesignerPageObject(driver);
    special.openSpecialDesignerPage(wikiURL);
    special.selectTheme(1);
    special.submitThemeSelection();
    String oldBackground = info.getBackgroundColor();
    toolbar.clickOnTool(textThemeDesigner);
    special.openSpecialDesignerPage(wikiURL);
    special.selectTheme(4);
    special.submitThemeSelection();
    String newBackground = info.getBackgroundColor();
    info.verifyChangedBackground(oldBackground, newBackground);

  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_012"})
  public void verifyOrderedAndUnorderedLists() {
    ArticlePageObject article = new ArticlePageObject(driver);
    TemplatePageObject template = article.openTemplatePage(wikiURL, "Template");
    SourceEditModePageObject editor = template.clickCreate();
    editor.addContent(PortableInfobox.INFOBOX_TEMPLATE);
    editor.clickPublishButton();
    PortableInboxPageObject info = article.getInfoboxPage();
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    editor = article.editArticleInSrcUsingDropdown();
    editor.addContent(PortableInfobox.INFOBOX_INVOCATION);
    editor.clickPublishButton();
    info.verifyOrderedListPresence();
    info.verifyUnorderedListPresence();
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_013"})
  public void verifyInfoboxCategoryLink() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    PortableInboxPageObject info = article.getInfoboxPage();
    info.clickCategoryLink();
    CategoryPageObject category = new CategoryPageObject(driver);
    String categoryName = category.getCategoryName();
    category.verifyCategoryPageTitle(categoryName);
  }

 @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_014"})
  public void verifyHorizontalGroupFontSize() {
   ArticlePageObject article = new ArticlePageObject(driver);
   article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
   PortableInboxPageObject info = article.getInfoboxPage();
   WebElement horizontalItemLabel = info.getHorizontalItemLabel();
   WebElement itemLabel = info.getItemLabel();
   info.verifyFontSize(horizontalItemLabel, itemLabel);
   WebElement horizontalItemValue = info.getHorizontalItemValue();
   WebElement itemValue = info.getItemValue();
   info.verifyFontSize(horizontalItemValue, itemValue);
 }

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_015"})
  public void verifyCopiedTemplateSyntaxInArticlePresence() {
    ArticlePageObject article = new ArticlePageObject(driver);
    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.logUserIn(Configuration.getCredentials().userName10,
            Configuration.getCredentials().password10);
    TemplatePageObject template = article.openTemplatePage(wikiURL,
            PageContent.PORTABLE_INFOBOX_WEBSITE_TEMPLATE);
    SourceEditModePageObject editor = template.editArticleInSrcUsingDropdown();
    String templateSyntax = editor.copyContent();
    LoginPage newLoginPage = new LoginPage(driver).get();
    newLoginPage.logUserIn(Configuration.getCredentials().userName10,
            Configuration.getCredentials().password10);
    ArticlePageObject randomArticle = article.openArticleByName(wikiURL, "Random" + article.getRandomDigits(5));
    SourceEditModePageObject newEditor = randomArticle.openCurrectArticleSourceMode();
    newEditor.addContent(templateSyntax);
    newEditor.submitArticle();
    PortableInboxPageObject info = randomArticle.getInfoboxPage();
    info.verifyImagePresence();
    info.verifyInfoboxTitlePresence();
  }
}

