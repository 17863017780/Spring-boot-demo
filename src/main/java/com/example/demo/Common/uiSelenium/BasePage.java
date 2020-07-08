package com.example.demo.Common.uiSelenium;

import org.openqa.selenium.WebElement;

/**
 * @author ccjh1
 * @creat 2020/1/11
 */
public interface BasePage {
    WebElement findElement(String loc);

    void sendKeys(String loc, String var1);

    void click(String loc);

    void submit(String loc);

    void switchToFrame(String loc);

    void switchToParentFrame();

    void closeAlert();

    void acceptAlert();

    String getAlertText();

    void moveToElement(String loc);

    void doubleClick(String loc);

    void contextClick(String loc);

    void dragAndDrop(String source, String target);

    void closeBrowser();

    String getTitle();

    String getUrl();

    void maxBrowser();
}
