package com.example.demo.Common.uiSelenium;

import org.apache.tomcat.jni.Time;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.apache.tomcat.jni.Time.sleep;

/**
 * @author ccjh1
 * @creat 2020/1/11
 *实现接口方法
 */
public class BasePageImpl implements BasePage{
    protected final WebDriver driver;
    private static final long timeOutInSeconds = 10;

    public BasePageImpl(WebDriver driver, String url) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
        this.driver.get(url);
    }

    BasePageImpl(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
    }

    public WebElement findElement(String loc) {
        try {
            return driver.findElement(LocationUtil.getLocation(loc));
        }catch (Exception e){
            System.out.println("[Exception]==BasePageImpl.findElement,failed to locate element: "+loc);
            return null;
        }
    }

    public void sendKeys(String loc, String var1) {
        findElement(loc).sendKeys(var1);
    }

    public void click(String loc) {
        findElement(loc).click();
    }

    public void submit(String loc) {
        findElement(loc).submit();
    }

    public void switchToFrame(String loc) {
        driver.switchTo().frame(findElement(loc));
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public void closeAlert() {
        driver.switchTo().alert().dismiss();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public void moveToElement(String loc) {
        new Actions(driver).moveToElement(findElement(loc)).perform();
    }

    public void doubleClick(String loc) {
        new Actions(driver).doubleClick(findElement(loc)).perform();
    }

    public void contextClick(String loc) {
        new Actions(driver).contextClick(findElement(loc)).perform();
    }

    public void dragAndDrop(String source, String target) {
        new Actions(driver).dragAndDrop(findElement(source), findElement(target)).perform();
    }

    public void closeBrowser() {
        driver.close();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void maxBrowser() {
        driver.manage().window().maximize();
    }

    public void driverWait(int var){
        try {
            Thread.sleep(var);
        }catch (Exception e){
            System.out.println("[Exception]==BasePageImpl.driverWait=="+ e.getMessage());
        }
    }
    /**
     * 跳转新的url页面
     * @param url
     */
    public void toUrl(String url) {
        driver.get(url);
    }
    public void switchToNewWindow(){
        Set<String> handles = driver.getWindowHandles();
        ArrayList<String> lst = new ArrayList<String>(handles);
        String handle=lst.get(0);
        driver.switchTo().window(handle).close();
        handle=lst.get(lst.size()-1);
        driver.switchTo().window(handle);
    }

    public static void main(String[] args){
    }
}
